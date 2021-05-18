package by.epam.learn.model.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.learn.entity.Order;
import by.epam.learn.entity.OrderStatus;
import by.epam.learn.exception.DaoException;
import by.epam.learn.model.dao.OrderDao;
import by.epam.learn.model.pool.ConnectionPool;

public class OrderDaoImpl implements OrderDao {
	public static Logger log = LogManager.getLogger();
	private static final OrderDao instance = new OrderDaoImpl();
	
	private static final String SQL_ADD_ORDER = "INSERT INTO orders(car_id, work_type_id, message, "
			+ "order_status, date) VALUES (?, ?, ?, ?, ?)";
	private static final String SQL_FIND_ORDER_BY_USER = "SELECT idorder, message, order_status, date, mechanic_id"
			+ "work_types.idworktype, work_types.work_type, cars.idcar, cars.user_id, cars.vin, cars.brand, "
			+ "cars.model, cars.year, cars.fuel_type, cars.volume, cars.transmission "
			+ "FROM orders "
			+ "JOIN work_types ON orders.work_type_id=work_types.idworktype "
			+ "JOIN cars ON orders.car_id=cars.idcar "
			+ "WHERE orders.user_id=?";
	private static final String SQL_FIND_ALL_ORDER = "SELECT idorder, message, order_status, date, mechanic_id"
			+ "work_types.idworktype, work_types.work_type, cars.idcar, cars.user_id, cars.vin, cars.brand, "
			+ "cars.model, cars.year, cars.fuel_type, cars.volume, cars.transmission "
			+ "FROM orders "
			+ "JOIN work_types ON orders.work_type_id=work_types.idworktype "
			+ "JOIN cars ON orders.car_id=cars.idcar ";
	private static final String SQL_UPDATE_ORDER = "UPDATE orders SET order_status=?, mechanic_id=?"
			+ "WHERE idorder=?";

	private OrderDaoImpl() {
	}

	public static OrderDao getInstance() {
		return instance;
	}

	
	@Override
	public boolean create(Order order) throws DaoException {
		boolean isAdded;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_ADD_ORDER)) {
			long carId = order.getCar().getCarId();
			long workTypeId = order.getWorkType().getWorkTypeId();
			String message = order.getMessage();
			OrderStatus orderStatus = order.getStatus();
			LocalDate date = order.getDate();
			
			//Blob message = (Blob) order.getMessage();
			//Timestamp sqlDate = new Timestamp(date);
		
			statement.setLong(1, carId);
			statement.setLong(2, workTypeId);
			//statement.setString(3, message);
			statement.setObject(3, message);
			statement.setString(4, orderStatus.toString());
			
			statement.setObject(5, Date.valueOf(date));
			int rowCount = statement.executeUpdate();
			if (rowCount != 0) {
				isAdded = true;
				log.info("Order added successfully");
			} else {
				log.error("order {} wasn't added", order);
				isAdded = false;
			}
		} catch (SQLException e) {
			log.error("Exception while adding new order", e);
			throw new DaoException("Exception while adding new order", e);
		}
		return isAdded;
	}
	
	@Override
	public Optional<Order> findOrderByUser(long userId) throws DaoException {

		Optional<Order> orders = Optional.empty();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_ORDER_BY_USER)) {
			statement.setLong(1, userId);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Order order = DaoEntityBuilder.buildOrder(resultSet);
				orders = Optional.of(order);
			}
		} catch (SQLException e) {
			throw new DaoException("database error", e);
		}
		return orders;
	}
	
	@Override
	public List<Order> findAll() throws DaoException {
		List<Order> orders = new ArrayList<>();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_ORDER)) {

			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {

				Order order = DaoEntityBuilder.buildOrder(resultSet);
				orders = List.of(order);
			}
		} catch (SQLException e) {
			throw new DaoException("database error", e);
		}
		return orders;
	}
	
	@Override
	public boolean update(Order order) throws DaoException {
		boolean isUpdate;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_ORDER)) {
			long orderId = order.getOrderId();
			OrderStatus status = order.getStatus();
			long mechanicId = order.getMechanicId();
			statement.setString(1, status.toString());
			statement.setLong(2, mechanicId);
			statement.setLong(3, orderId);

			int rowCount = statement.executeUpdate();
			if (rowCount != 0) {
				isUpdate = true;
				log.info("Order updated successfully");
			} else {
				log.error("Order {} wasn't updayed", order);
				isUpdate = false;
			}
		} catch (SQLException e) {
			log.error("Exception while update order", e);
			throw new DaoException("Exception while update order", e);
		}
		return isUpdate;
	}
	
	
	
	

	@Override
	public Order findEntityById(Integer id) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Order t) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Integer id) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}



}
