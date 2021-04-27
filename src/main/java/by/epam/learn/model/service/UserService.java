package by.epam.learn.model.service;

import java.util.Map;
import java.util.Optional;

import by.epam.learn.entity.User;
import by.epam.learn.exception.ServiceException;

public interface UserService {
	
	public boolean signUp(Map<String, String> userData) throws ServiceException;
	
	Optional<User> login(String email, String password) throws ServiceException;

}
