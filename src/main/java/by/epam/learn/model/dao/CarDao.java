package by.epam.learn.model.dao;

import java.util.Optional;

import by.epam.learn.entity.Car;
import by.epam.learn.exception.DaoException;

public interface CarDao extends BaseDao<Integer, Car> {
	
	boolean addCar(Car car) throws DaoException;

	Optional<Car> findCarsByUser(long userId) throws DaoException;

}
