package by.epam.learn.model.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.learn.entity.Car;
import by.epam.learn.entity.Order;
import by.epam.learn.entity.OrderStatus;
import by.epam.learn.entity.User;
import by.epam.learn.entity.WorkType;
import by.epam.learn.exception.DaoException;
import by.epam.learn.exception.ServiceException;
import by.epam.learn.model.dao.impl.OrderDaoImpl;
import by.epam.learn.model.service.OrderService;
import by.epam.learn.validator.OrderDataValidator;

import static by.epam.learn.controller.command.DataKeyword.*;

/**
 * The {@code OrderServiceImpl} class is responsible for operations with the order
 * 
 * @author Ihar Klepcha
 * @see OrderService
 */
public class OrderServiceImpl implements OrderService {
	public static Logger log = LogManager.getLogger();

	@Override
	public boolean addOrder(Map<String, String> orderData) throws ServiceException {
		boolean isAdded = false;
		try {
			if (OrderDataValidator.areValidData(orderData)) {
				long carId = Long.parseLong(orderData.get(CAR_ID_KEY));
				Car car = new Car();
				car.setCarId(carId);
				long workId = Long.parseLong(orderData.get(WORK_ID_KEY));
				WorkType workType = new WorkType();
				workType.setWorkTypeId(workId);
				String message = orderData.get(MESSAGE_KEY);
				OrderStatus status = OrderStatus.NEW;
				LocalDate date = LocalDate.now();
				Order order = new Order(car, workType, message, status, date);
				isAdded = OrderDaoImpl.getInstance().create(order);
			}
		} catch (DaoException e) {
			log.error("order adding error", e);
			throw new ServiceException("order adding error", e);
		}
		return isAdded;
	}
	
	@Override
	public List<Order> findAllOrder() throws ServiceException {
		List<Order> orders;
		try {
			orders = OrderDaoImpl.getInstance().findAll();
		} catch (DaoException e) {
			orders = new ArrayList<>();
			log.error("order search error", e);
			throw new ServiceException("order search error", e);
		}
		return orders;
	}

	@Override
	public List<Order> findOrderByMechanic(User mechanic) throws ServiceException {
		long mechanicId = mechanic.getUserId();
		List<Order> orders;
		try {
			orders = OrderDaoImpl.getInstance().findOrderByMechanic(mechanicId);
		} catch (DaoException e) {
			log.error("orders search error", e);
			throw new ServiceException("orders search error", e);
		}
		return orders;
	}

	@Override
	public boolean updateActiveStatus(Order order, String mechanicIdValue) throws ServiceException {
		boolean isUpdated;
		long mechanicId = Long.parseLong(mechanicIdValue);
		order.setStatus(OrderStatus.ACTIVE);
		order.setMechanicId(mechanicId);
		try {
			isUpdated = OrderDaoImpl.getInstance().update(order);
		} catch (DaoException e) {
			log.error("order update error", e);
			throw new ServiceException("order update error", e);
		}
		return isUpdated;
	}

	@Override
	public boolean updateReadyStatus(Order order) throws ServiceException {
		boolean isUpdated;
		order.setStatus(OrderStatus.READY);
		try {
			isUpdated = OrderDaoImpl.getInstance().update(order);
		} catch (DaoException e) {
			log.error("order update error", e);
			throw new ServiceException("order update error", e);
		}
		return isUpdated;
	}
	
	@Override
	public boolean updateCompletedStatus(Order order) throws ServiceException {
		boolean isUpdated;
		order.setStatus(OrderStatus.COMPLETED);
		try {
			isUpdated = OrderDaoImpl.getInstance().update(order);
		} catch (DaoException e) {
			log.error("order update error", e);
			throw new ServiceException("order update error", e);
		}
		return isUpdated;
	}
}
