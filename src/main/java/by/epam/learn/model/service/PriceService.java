package by.epam.learn.model.service;

import java.util.List;
import java.util.Map;

import by.epam.learn.entity.Order;
import by.epam.learn.entity.Price;
import by.epam.learn.exception.ServiceException;

/**
 * The {@code PriceService} interface for operations with the price
 * 
 * @author Ihar Klepcha
 */
public interface PriceService {
	
	/**
	 * Adds the price in the data base
	 * 
	 * @param priceData {@link Map} of {@link String} and {@link String} price data values
	 * @return boolean true if the price has been added, else false
	 * @throws ServiceException if {@link DaoException} occurs
	 */
	boolean addPrice(Map<String, String> priceData) throws ServiceException;
	
	/**
	 * Finds all price
	 * 
	 * @return {@link List} of {@link Price} received from database if price are found, else empty list
	 * @throws ServiceException if {@link DaoException} occurs
	 */
	List<Price> findAll() throws ServiceException;
	
	/**
	 * Finds price by id
	 * 
	 * @param priceIdValue {@link String} value price id
	 * @return {@link Price} received from database if price are found, else empty entity
	 * @throws ServiceException if {@link DaoException} occurs
	 */
	Price findPriceById(String priceIdValue) throws ServiceException;
	
	/**
	 * Finds price by type of work
	 * 
	 * @param order {@link Order} order
	 * @return {@link List} of {@link Price} received from database if price are found, else empty list
	 * @throws ServiceException if {@link DaoException} occurs
	 */
	List<Price> findPriceByWorkType(Order order) throws ServiceException;
	
	/**
	 * Finds price by operation
	 * 
	 * @param operationValue {@link String} operation values
	 * @return {@link List} of {@link Price} received from database if price are found, else empty list
	 * @throws ServiceException if {@link DaoException} occurs
	 */
	List<Price> findPriceByOperation(String operationValue) throws ServiceException;

	/**
	 * Updates the price in the data base
	 * 
	 * @param priceData {@link Map} of {@link String} and {@link String} price data values
	 * @return boolean true if the price has been updated, else false
	 * @throws ServiceException if {@link DaoException} occurs
	 */	
	boolean updatePrice(Map<String, String> priceData) throws ServiceException;
	
	/**
	 * Deletes the price in the data base
	 * 
	 * @param priceIdValue {@link String} value price id
	 * @return boolean true if the price has been deleted, else false
	 * @throws ServiceException if {@link DaoException} occurs
	 */
	boolean delete(String priceIdValue) throws ServiceException;
}
