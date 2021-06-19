package by.epam.learn.model.dao;

import java.util.List;

import by.epam.learn.entity.Order;
import by.epam.learn.exception.DaoException;

/**
 * The {@code OrderDao} interface for working with database table orders
 * 
 * @author Ihar Klepcha
 * @see BaseDao
 */
public interface OrderDao extends BaseDao<Long, Order> {

	/**
	 * Looks for an order for the mechanic
	 * 
	 * @param mechanicId long user id
	 * @return {@link List} of {@link Order} order received from database
	 * @throws DaoException if {@link SQLException} occur
	 */
	List<Order> findOrderByMechanic(long mechanicId) throws DaoException;
}
