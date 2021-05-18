package by.epam.learn.model.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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

public class OrderServiceImpl implements OrderService {
	public static Logger log = LogManager.getLogger();
	private static final int ZERO = 0;

	@Override
	public boolean addOrder(Map<String, String> orderData) throws ServiceException {
		boolean isAdded;
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
				
			} else {
				isAdded = false;
			}
		} catch (DaoException e) {
			log.error(e);
			throw new ServiceException(e);
		}
		return isAdded;
	}

	@Override
	public List<Order> findOrder(User user) throws ServiceException {
		long userId = user.getUserId();
		Optional<Order> orders = Optional.empty();
		try {
			orders = OrderDaoImpl.getInstance().findOrderByUser(userId);
		} catch (DaoException e) {
			throw new ServiceException("orders search error", e);
		}

		List<Order> orderList = orders.stream()
				.collect(Collectors.toList());// Что делать если не страшно если будет пустой лист?
		
		return orderList;
	}

	@Override
	public List<Order> findAllOrder() throws ServiceException {
		List<Order> orders = new ArrayList<>();
		try {
			orders = OrderDaoImpl.getInstance().findAll();
		} catch (DaoException e) {
			throw new ServiceException("orders search error", e);
		}

//		List<Order> orderList = orders.stream()
//				.collect(Collectors.toList());// Что делать если не страшно если будет пустой лист?
		
		return orders;
	}
	
	@Override
	public boolean updateActiveStatus(Order order, User mechanic) throws ServiceException {
		boolean isUpdated;
		try {
			order.setStatus(OrderStatus.ACTIVE);
			order.setMechanicId(mechanic.getUserId());

			isUpdated = OrderDaoImpl.getInstance().update(order);
			
		} catch (DaoException e) {
			log.error(e);
			throw new ServiceException(e);
		}
		return isUpdated;
	}

	@Override
	public boolean updateReadyStatus(Order order) throws ServiceException {
		boolean isUpdated;
		try {
			order.setStatus(OrderStatus.READY);
			order.setMechanicId(ZERO);
			isUpdated = OrderDaoImpl.getInstance().update(order);
		} catch (DaoException e) {
			log.error(e);
			throw new ServiceException(e);
		}
		return isUpdated;
	}
}
