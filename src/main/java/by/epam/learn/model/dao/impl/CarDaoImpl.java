package by.epam.learn.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.learn.entity.Car;
import by.epam.learn.exception.ConnectionPoolException;
import by.epam.learn.exception.DaoException;
import by.epam.learn.model.dao.CarDao;
import by.epam.learn.model.pool.ConnectionPool;

/**
 * The {@code CarDaoImpl} class works with database table cars
 * 
 * @author Ihar Klepcha
 * @see CarDao
 */
public class CarDaoImpl implements CarDao {
	public static Logger log = LogManager.getLogger();
	private static final CarDao instance = new CarDaoImpl();
	private static final String SQL_ADD_CAR = "INSERT INTO cars(user_id, vin, brand, model, year, fuel_type, "
			+ "volume, transmission) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";	
	private static final String SQL_FIND_CAR_BY_CLIENT = "SELECT idcar, vin, brand, model, year, "
			+ "fuel_type, volume, transmission, users.iduser, users.login, users.name, users.email, "
			+ "users.phone, users.role, users.status FROM cars "
			+ "JOIN users ON cars.user_id=users.iduser "
			+ "WHERE cars.user_id=?";
	
	private CarDaoImpl() {
	}

	public static CarDao getInstance() {
		return instance;
	}
	
	@Override
	public boolean create(Car car) throws DaoException {
		boolean isAdded;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_ADD_CAR)) {
			long userId = car.getUser().getUserId();
			String vin = car.getVin();
			String brand = car.getBrand();
			String model = car.getModel();
			String year = car.getYearProduction();
			String fuel = car.getFuelType();
			String volume = car.getVolumeEngine();
			String transmission = car.getTransmisionType();
			statement.setLong(1, userId);
			statement.setString(2, vin);
			statement.setString(3, brand);
			statement.setString(4, model);
			statement.setString(5, year);
			statement.setString(6, fuel);
			statement.setString(7, volume);
			statement.setString(8, transmission);
			int rowCount = statement.executeUpdate();
			isAdded = (rowCount != 0);
		} catch (ConnectionPoolException | SQLException e) {
			log.error("exception while adding new car", e);
			throw new DaoException("database error", e);
		}
		return isAdded;
	}
	
	@Override
	public List<Car> findCarsByUser(long userId) throws DaoException {
		List<Car> cars = new ArrayList<>();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_CAR_BY_CLIENT)) {
			statement.setLong(1, userId);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Car car = DaoEntityBuilder.buildCar(resultSet);
				cars.add(car);
			}
		} catch (ConnectionPoolException | SQLException e) {
			log.error("exception while finding a car", e);
			throw new DaoException("database error", e);
		}
		return cars;
	}
	
	@Override
	public List<Car> findAll() throws DaoException {
		throw new UnsupportedOperationException("operation not supported for class " + this.getClass().getName());
	}

	@Override
	public Car findEntityById(Long id) throws DaoException {
		throw new UnsupportedOperationException("operation not supported for class " + this.getClass().getName());
	}

	@Override
	public boolean delete(Long id) throws DaoException {
		throw new UnsupportedOperationException("operation not supported for class " + this.getClass().getName());
	}

	@Override
	public boolean update(Car t) throws DaoException {
		throw new UnsupportedOperationException("operation not supported for class " + this.getClass().getName());
	}
}
