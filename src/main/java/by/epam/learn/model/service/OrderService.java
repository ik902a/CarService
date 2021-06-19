package by.epam.learn.model.service;

import java.util.List;
import java.util.Map;

import by.epam.learn.entity.Order;
import by.epam.learn.entity.User;
import by.epam.learn.exception.ServiceException;

/**
 * The {@code OrderService} interface for operations with the order
 * 
 * @author Ihar Klepcha
 */
public interface OrderService {
	
	/**
	 * Adds the order in the data base
	 * 
	 * @param orderData {@link Map} of {@link String} and {@link String} order data values
	 * @return boolean true if the order has been added, else false
	 * @throws ServiceException if {@link DaoException} occurs
	 */
	boolean addOrder(Map<String, String> orderData) throws ServiceException;
	
	/**
	 * Finds all orders
	 * 
	 * @return {@link List} of {@link Order} received from database if orders are found, else empty list
	 * @throws ServiceException if {@link DaoException} occurs
	 */
	List<Order> findAllOrder() throws ServiceException;
	
	/**
	 * Finds order by mechanic
	 * 
	 * @param mechanic {@link User} mechanic
	 * @return {@link List} of {@link Order} received from database if orders are found, else empty list
	 * @throws ServiceException if {@link DaoException} occurs
	 */
	List<Order> findOrderByMechanic(User mechanic) throws ServiceException;

	/**
	 * Updates the order's status in the data base to active and adds mechanic to this order
	 * 
	 * @param order {@link Order} order
	 * @param mechanicIdValue {@link String} value mechanic id
	 * @return boolean true if the order has been updated, else false
	 * @throws ServiceException if {@link DaoException} occurs
	 */
	boolean updateActiveStatus(Order order, String mechanicIdValue) throws ServiceException;

	/**
	 * Updates the order's status in the data base to ready
	 * 
	 * @param order {@link Order} order
	 * @return boolean true if the order has been updated, else false
	 * @throws ServiceException if {@link DaoException} occurs
	 */
	boolean updateReadyStatus(Order order) throws ServiceException;
	
	/**
	 * Updates the order's status in the data base to completed
	 * 
	 * @param order {@link Order} order
	 * @return boolean true if the order has been updated, else false
	 * @throws ServiceException if {@link DaoException} occurs
	 */
	boolean updateCompletedStatus(Order order) throws ServiceException;
}
