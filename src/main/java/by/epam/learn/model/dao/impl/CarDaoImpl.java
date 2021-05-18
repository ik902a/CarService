package by.epam.learn.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.learn.entity.Car;
import by.epam.learn.exception.DaoException;
import by.epam.learn.model.dao.CarDao;
import by.epam.learn.model.pool.ConnectionPool;

public class CarDaoImpl implements CarDao {
	public static Logger log = LogManager.getLogger();
	private static final CarDao instance = new CarDaoImpl();
	
	private static final String SQL_ADD_CAR = "INSERT INTO cars(user_id, vin, brand, model, year, fuel_type,"
			+ "volume, transmission) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String SQL_FIND_CAR_BY_USER = "SELECT idcar, vin, brand, model, year, fuel_type,"
			+ "volume, transmission FROM cars WHERE user_id=?";
	
	private CarDaoImpl() {
	}

	public static CarDao getInstance() {
		return instance;
	}
	
	@Override
	public boolean addCar(Car car) throws DaoException {
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
			if (rowCount != 0) {
				isAdded = true;
				log.info("Car added successfully");
			} else {
				log.error("Car {} wasn't added", car);
				isAdded = false;
			}
		} catch (SQLException e) {
			log.error("Exception while adding new car", e);
			throw new DaoException("Exception while adding new car", e);
		}
		return isAdded;
	}
	
	@Override
	public Optional<Car> findCarsByUser(long userId) throws DaoException {
		Optional<Car> cars = Optional.empty();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_CAR_BY_USER)) {
			statement.setLong(1, userId);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {

				Car car = DaoEntityBuilder.buildCar(resultSet);
				cars = Optional.of(car);
			}
		} catch (SQLException e) {
			throw new DaoException("database error", e);
		}
		return cars;
	}
	

	
	
	
	@Override
	public List<Car> findAll() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Car findEntityById(Integer id) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Car t) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Integer id) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean create(Car t) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Car t) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

}
