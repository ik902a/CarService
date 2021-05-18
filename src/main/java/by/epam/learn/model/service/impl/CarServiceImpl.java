package by.epam.learn.model.service.impl;

import java.util.Map;
import java.util.Optional;
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

				isAdded = CarDaoImpl.getInstance().addCar(car);
			} else {
				isAdded = false;
			}
		} catch (DaoException e) {
			log.error(e);
			throw new ServiceException(e);
		}
		
//		TODO
//        if (successfullySignedUp) {
//            String emailValue = userData.get(EMAIL_KEY);
//            String topic = "Welcome to GO!";
//            String text = "Thank you for registering on our rental service. Wish you a great road!";
//            sendMail(emailValue, topic, text);
//        }

		return isAdded;
	}
	
	public Map<Long, String> findCar(User user) throws ServiceException {
		long userId = user.getUserId();
		Optional<Car> cars = Optional.empty();
		try {
			cars = CarDaoImpl.getInstance().findCarsByUser(userId);
		} catch (DaoException e) {
			throw new ServiceException("cars search error", e);
		}
		
		Map<Long, String> carData = cars.stream()
				.collect(Collectors.toMap(car -> (Long) car.getCarId(),
							car -> (String) car.getBrand() + SPACE + car.getModel()));
		
		return carData;
	}
	
}
