package by.epam.learn.model.service;

import java.util.List;
import java.util.Map;

import by.epam.learn.entity.Order;
import by.epam.learn.entity.User;
import by.epam.learn.exception.ServiceException;

public interface OrderService {
	public boolean addOrder(Map<String, String> orderData) throws ServiceException;
	
	public List<Order> findOrder(User user) throws ServiceException;
	
	public List<Order> findAllOrder() throws ServiceException;
	
	public boolean updateActiveStatus(Order order, User mechanic) throws ServiceException;

	public boolean updateReadyStatus(Order order) throws ServiceException;
}
