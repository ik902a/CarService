package by.epam.learn.model.service;

import java.util.Map;

import by.epam.learn.entity.User;
import by.epam.learn.exception.ServiceException;

/**
 * The {@code CarService} interface for operations with the car
 * 
 * @author Ihar Klepcha
 */
public interface CarService {
	
	/**
	 * Adds the car in the data base
	 * 
	 * @param carData {@link Map} of {@link String} and {@link String} car data values
	 * @return boolean true if the car has been added, else false
	 * @throws ServiceException if {@link DaoException} occurs
	 */
	boolean addCar(Map<String, String> carData, User user) throws ServiceException;
	
	/**
	 * Finds car by client
	 * 
	 * @param client {@link User} client
	 * @return {@link Map} of {@link Long} and {@link String} received from database if cars are found, 
	 * else empty map
	 * @throws ServiceException if {@link DaoException} occurs
	 */
	Map<Long, String> findCar(User client) throws ServiceException;
}
