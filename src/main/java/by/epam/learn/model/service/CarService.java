package by.epam.learn.model.service;

import java.util.Map;

import by.epam.learn.entity.User;
import by.epam.learn.exception.ServiceException;


public interface CarService {
	public boolean addCar(Map<String, String> userData, User user) throws ServiceException;
	
	public Map<Long, String> findCar(User user) throws ServiceException;

}
