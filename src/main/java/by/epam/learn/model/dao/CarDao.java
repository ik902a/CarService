package by.epam.learn.model.dao;

import java.util.List;

import by.epam.learn.entity.Car;
import by.epam.learn.exception.DaoException;

/**
 * The {@code CarDao} interface for working with database table cars
 * 
 * @author Ihar Klepcha
 * @see BaseDao
 */
public interface CarDao extends BaseDao<Long, Car> {

	/**
	 * Looks for a car where the user is owner
	 * 
	 * @param userId long user id
	 * @return {@link List} of {@link Car} car received from database
	 * @throws DaoException if {@link SQLException} occur
	 */
	List<Car> findCarsByUser(long userId) throws DaoException;
}
