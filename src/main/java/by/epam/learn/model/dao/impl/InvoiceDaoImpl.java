package by.epam.learn.model.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.learn.entity.Invoice;
import by.epam.learn.exception.ConnectionPoolException;
import by.epam.learn.exception.DaoException;
import by.epam.learn.model.dao.InvoiceDao;
import by.epam.learn.model.pool.ConnectionPool;

/**
 * The {@code InvoiceDaoImpl} class works with database table invoices
 * 
 * @author Ihar Klepcha
 * @see InvoiceDao
 */
public class InvoiceDaoImpl implements InvoiceDao {
	public static Logger log = LogManager.getLogger();
	private static final InvoiceDao instance = new InvoiceDaoImpl();
	private static final String SQL_ADD_INVOICE = "INSERT INTO invoices(user_id, price_id, date) "
			+ "VALUES (?, ?, ?)";
	
	private InvoiceDaoImpl() {
	}

	public static InvoiceDao getInstance() {
		return instance;
	}
	
	@Override
	public boolean addInvoice(long clientId, long priceId) throws DaoException {
		boolean isAdded;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_ADD_INVOICE)) {
			LocalDate date = LocalDate.now();
			statement.setLong(1, clientId);
			statement.setLong(2, priceId);
			statement.setObject(3, Date.valueOf(date));
			int rowCount = statement.executeUpdate();
			isAdded = (rowCount != 0);
		} catch (ConnectionPoolException | SQLException e) {
			log.error("exception while adding new invoice", e);
			throw new DaoException("database error", e);
		}
		return isAdded;
	}
	
	@Override
	public List<Invoice> findAll() throws DaoException {
		throw new UnsupportedOperationException("operation not supported for class " + this.getClass().getName());
	}

	@Override
	public Invoice findEntityById(Long id) throws DaoException {
		throw new UnsupportedOperationException("operation not supported for class " + this.getClass().getName());
	}

	@Override
	public boolean delete(Long id) throws DaoException {
		throw new UnsupportedOperationException("operation not supported for class " + this.getClass().getName());
	}

	@Override
	public boolean update(Invoice t) throws DaoException {
		throw new UnsupportedOperationException("operation not supported for class " + this.getClass().getName());
	}

	@Override
	public boolean create(Invoice t) throws DaoException {
		throw new UnsupportedOperationException("operation not supported for class " + this.getClass().getName());
	}
}
