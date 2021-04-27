package by.epam.learn.model.service;

import java.util.Map;

import by.epam.learn.exception.ServiceException;

public interface CarService {
	public boolean addCar(Map<String, String> userData) throws ServiceException;

}
