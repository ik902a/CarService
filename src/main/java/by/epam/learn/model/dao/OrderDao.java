package by.epam.learn.model.dao;

import java.util.Optional;

import by.epam.learn.entity.Order;
import by.epam.learn.exception.DaoException;

public interface OrderDao extends BaseDao<Integer, Order> {

	Optional<Order> findOrderByUser(long userId) throws DaoException;

}
