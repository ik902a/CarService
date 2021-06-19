package by.epam.learn.model.service;

import java.util.Enumeration;

import by.epam.learn.entity.Order;
import by.epam.learn.exception.ServiceException;

public interface InvoiceService {//TODO
	
	/**
	 * Adds the invoice in the data base and sends an email
	 * 
	 * @param operation {@link Enumeration} of {@link String} list of operations
	 * @param order {@link Order} order
	 * @return boolean true if the order has been added, else false
	 * @throws ServiceException if {@link DaoException} occurs
	 */
	boolean addInvoice(String[] operations, Order order) throws ServiceException;
}
