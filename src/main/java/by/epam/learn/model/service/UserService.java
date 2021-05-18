package by.epam.learn.model.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import by.epam.learn.entity.User;
import by.epam.learn.exception.ServiceException;

public interface UserService {
	
	public boolean signUp(Map<String, String> userData) throws ServiceException;
	
	public Optional<User> login(String email, String password) throws ServiceException;

	public boolean updateClient(Map<String, String> userData, User user) throws ServiceException;
	
	public List<User> findMechanic() throws ServiceException;
	
	public boolean activate(String login) throws ServiceException;
	
}
