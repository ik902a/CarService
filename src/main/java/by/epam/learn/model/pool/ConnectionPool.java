package by.epam.learn.model.pool;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.learn.exception.ConnectionPoolException;

public class ConnectionPool {//TODO
	public static Logger log = LogManager.getLogger();
	private static final int DEFAULT_POOL_SIZE = 8;
	private static ConnectionPool instance;
	private static AtomicBoolean isCreated = new AtomicBoolean();
	private static Lock locker = new ReentrantLock(true);
	private BlockingQueue<ProxyConnection> freeConnections;
	private Queue<ProxyConnection> givenAwayConnections;
	
	public static ConnectionPool getInstance() {
		if (!isCreated.get()) {
			locker.lock();
			if (instance == null) {
				instance = new ConnectionPool();
				isCreated.set(true);
			}
			locker.unlock();
		}
		log.debug("created instanse" + instance);
		return instance;
	}
	
	private ConnectionPool() {
		freeConnections = new LinkedBlockingDeque<ProxyConnection>(DEFAULT_POOL_SIZE);
		givenAwayConnections = new ArrayDeque<ProxyConnection>(DEFAULT_POOL_SIZE);
		for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
			try {

				Connection connection = ConnectionFactory.createConnection();
				ProxyConnection proxyConnection = new ProxyConnection(connection);
				freeConnections.add(proxyConnection);

			} catch (SQLException e) {
				log.error("ERROR database access, connection not received", e); // TODO
				throw new RuntimeException("database access error", e);
			}
		}
		if (freeConnections.isEmpty()) {
			log.error("ERROR database access"); // TODO
			throw new RuntimeException("database access error");
		}
	}
	
    public Connection getConnection(){
        ProxyConnection connection = null;
        try {
            connection = freeConnections.take();
            givenAwayConnections.offer(connection);
        } catch (InterruptedException e) {
            log.error("Thread was interrupted while taking new free connection", e);//TODO
            Thread.currentThread().interrupt();
        }
        return connection;
    }
	
    void releaseConnection(Connection connection) {
        if (connection.getClass() != ProxyConnection.class) {
            log.error("Unreleasable connection {}", connection);
        } else {
            ProxyConnection proxyConnection = (ProxyConnection) connection;
            givenAwayConnections.remove(proxyConnection);
            boolean added = freeConnections.offer(proxyConnection);
            if (!added) {
                log.error("Connection {} wasn't added to the deque of free connections", proxyConnection);
            }
        }
    }
    
    public void destroyPool() throws ConnectionPoolException {
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                freeConnections.take().completelyClose();
            } catch (SQLException e) {
                throw new ConnectionPoolException("Exception by closing free connections", e);
            } catch (InterruptedException ex) {
                log.error("Thread was interrupted while taking a free connection", ex);
                Thread.currentThread().interrupt();
            }
        }
        deregisterDrivers();
    }
	
    private void deregisterDrivers() throws ConnectionPoolException {
        try {
            while (DriverManager.getDrivers().hasMoreElements()) {
                Driver driver = DriverManager.getDrivers().nextElement();
                DriverManager.deregisterDriver(driver);
            }
        } catch (SQLException e) {
            log.error("ERROR while deregistering drivers", e);
            throw new ConnectionPoolException(e);
        }
    }
}
