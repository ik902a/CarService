package by.epam.learn.model.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.learn.entity.Car;
import by.epam.learn.entity.User;
import by.epam.learn.exception.DaoException;
import by.epam.learn.exception.ServiceException;
import by.epam.learn.model.dao.impl.CarDaoImpl;
import by.epam.learn.model.service.CarService;
import by.epam.learn.validator.CarDataValidator;

import static by.epam.learn.controller.command.DataKeyword.*;

/**
 * The {@code CarServiceImpl} class is responsible for operations with the car
 * 
 * @author Ihar Klepcha
 * @see CarService
 */
public class CarServiceImpl implements CarService {
	public static Logger log = LogManager.getLogger();
    private static final String SPACE = " ";

	@Override
	public boolean addCar(Map<String, String> carData, User user) throws ServiceException {
		boolean isAdded = false;
		try {
			if (CarDataValidator.areValidData(carData)) {
				String vin = carData.get(VIN_KEY);
				String brand = carData.get(BRAND_KEY);
				String model = carData.get(MODEL_KEY);
				String year = carData.get(YEAR_KEY);
				String fuel = carData.get(FUEL_KEY);
				String volume = carData.get(VOLUME_KEY);
				String transmission = carData.get(TRANSMISSION_KEY);
				Car car = new Car(user, vin, brand, model, year, fuel, volume, transmission);
				isAdded = CarDaoImpl.getInstance().create(car);
			}
		} catch (DaoException e) {
			log.error("car adding error", e);
			throw new ServiceException("car adding error", e);
		}
		return isAdded;
	}
	
	@Override
	public Map<Long, String> findCar(User client) throws ServiceException {
		long userId = client.getUserId();
		List<Car> cars;
		try {
			cars = CarDaoImpl.getInstance().findCarsByUser(userId);
		} catch (DaoException e) {
			log.error("car search error", e);
			throw new ServiceException("cars search error", e);
		}
		Map<Long, String> carData = cars.stream()
				.collect(Collectors.toMap(car -> (Long) car.getCarId(),
							car -> (String) car.getBrand() + SPACE + car.getModel()));
		return carData;
	}
}
