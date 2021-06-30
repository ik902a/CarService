package by.epam.learn.model.pool;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.learn.exception.ConnectionPoolException;

/**
 * The {@code ConnectionPool} class is pool of connections used while the system is running
 * 
 * @author Ihar Klepcha
 */
public class ConnectionPool {
	public static Logger log = LogManager.getLogger();
	private static final int DEFAULT_POOL_SIZE = 8;
	private static ConnectionPool instance;
	private static AtomicBoolean isCreated = new AtomicBoolean();
	private static Lock locker = new ReentrantLock(true);
	private BlockingQueue<ProxyConnection> freeConnections;
	private BlockingQueue<ProxyConnection> givenAwayConnections;
	
	/**
	 * Gets instance of this class
	 * 
	 * @return {@link ConnectionPool} instance
	 */
	public static ConnectionPool getInstance() {
		if (!isCreated.get()) {
			locker.lock();
			try {
				if (instance == null) {
					instance = new ConnectionPool();
					isCreated.set(true);
				}
			} finally {
				locker.unlock();
			}
		}
		log.debug("created instanse " + instance);
		return instance;
	}
	
	/**
	 * Constructs connection pool
	 * 
	 */
	private ConnectionPool() {
		freeConnections = new LinkedBlockingDeque<ProxyConnection>(DEFAULT_POOL_SIZE);
		givenAwayConnections = new LinkedBlockingDeque<ProxyConnection>(DEFAULT_POOL_SIZE);
		for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
			try {
				Connection connection = ConnectionFactory.createConnection();
				ProxyConnection proxyConnection = new ProxyConnection(connection);
				freeConnections.add(proxyConnection);
			} catch (SQLException e) {
				log.error("error database access, connection not received", e);
				throw new RuntimeException("database access error", e);
			}
		}
		if (freeConnections.isEmpty()) {
			log.error("error Pool size is null");
			throw new RuntimeException("error Pool size is null");
		}
	}
	
	/**
	 * Gets a connection from the connection pool
	 * 
	 * @return {@link Connection} connection to the database
	 * @throws ConnectionPoolException if {@link InterruptedException} occurs
	 */
    public Connection getConnection() throws ConnectionPoolException {
        ProxyConnection connection;
        try {
            connection = freeConnections.take();
            givenAwayConnections.put(connection);
        } catch (InterruptedException e) {
        	Thread.currentThread().interrupt();
            log.error("error by get connections", e);
            throw new ConnectionPoolException("error by get connections", e);
        }
        return connection;
    }
	
	/**
	 * Returns the connection to the connection pool
	 * 
	 * @param connection {@link Connection} connection to the database
	 * @throws ConnectionPoolException if {@link InterruptedException} occurs
	 */
	void releaseConnection(Connection connection) {
		if (connection.getClass() != ProxyConnection.class) {
			log.error("unreleasable connection {}", connection);
		} else {
			ProxyConnection proxyConnection = (ProxyConnection) connection;
			givenAwayConnections.remove(proxyConnection);
			try {
				freeConnections.put(proxyConnection);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				log.error("error by release connections", e);
			}
		}
	}
    
	/**
	 * Destroys connection pool
	 * 
	 * @throws ConnectionPoolException if {@link InterruptedException} or {@link SQLException} occurs
	 */
    public void destroyPool() throws ConnectionPoolException {
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                freeConnections.take().completelyClose();
            } catch (SQLException e) {
                throw new ConnectionPoolException("error by closing free connections", e);
            } catch (InterruptedException ex) {
            	Thread.currentThread().interrupt();
                log.error("thread was interrupted while taking a free connection", ex);
                throw new ConnectionPoolException("error by closing free connections", ex);
            }
        }
        deregisterDrivers();
    }
	
	/**
	 * Unregisters drivers
	 * 
	 * @throws ConnectionPoolException if {@link SQLException} occurs
	 */
    private void deregisterDrivers() throws ConnectionPoolException {
        try {
            while (DriverManager.getDrivers().hasMoreElements()) {
                Driver driver = DriverManager.getDrivers().nextElement();
                DriverManager.deregisterDriver(driver);
            }
        } catch (SQLException e) {
            log.error("error while deregistering drivers", e);
            throw new ConnectionPoolException("error while deregistering drivers", e);
        }
    }
}
