package by.epam.learn.model.dao;

import by.epam.learn.entity.Invoice;
import by.epam.learn.exception.DaoException;

/**
 * The {@code InvoiceDao} interface for working with database table invoices
 * 
 * @author Ihar Klepcha
 * @see BaseDao
 */
public interface InvoiceDao extends BaseDao<Long, Invoice> {
	
	/**
	 * Creates a new record in the corresponding database table
	 * 
	 * @param clientId long user id
	 * @param priceId long price id
	 * @return boolean true if the record has been added, else false
	 * @throws DaoException if {@link SQLException} occur
	 */
	boolean addInvoice(long clientId, long priceId) throws DaoException;
}
