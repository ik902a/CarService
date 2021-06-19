package by.epam.learn.model.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.learn.entity.Order;
import by.epam.learn.entity.OrderStatus;
import by.epam.learn.exception.ConnectionPoolException;
import by.epam.learn.exception.DaoException;
import by.epam.learn.model.dao.OrderDao;
import by.epam.learn.model.pool.ConnectionPool;

/**
 * The {@code OrderDaoImpl} class works with database table orders
 * 
 * @author Ihar Klepcha
 * @see OrderDao
 */
public class OrderDaoImpl implements OrderDao {
	public static Logger log = LogManager.getLogger();
	private static final OrderDao instance = new OrderDaoImpl();
	private static final String SQL_ADD_ORDER = "INSERT INTO orders(car_id, work_type_id, message, "
			+ "order_status, date) VALUES (?, ?, ?, ?, ?)";
	private static final String SQL_FIND_ALL_ORDER = "SELECT idorder, message, order_status, date, "
			+ "mechanic_id, work_types.idworktype, work_types.work_type, cars.idcar, cars.vin, cars.brand, "
			+ "cars.model, cars.year, cars.fuel_type, cars.volume, cars.transmission, users.iduser, "
			+ "users.login, users.name, users.email, users.phone, users.role, users.status "
			+ "FROM orders "
			+ "JOIN work_types ON orders.work_type_id=work_types.idworktype "
			+ "JOIN cars ON orders.car_id=cars.idcar "
			+ "JOIN users ON cars.user_id=users.iduser "
			+ "WHERE orders.order_status!='COMPLETED'";
	private static final String SQL_FIND_ORDER_BY_MECHANIC = "SELECT idorder, message, order_status, date, "
			+ "mechanic_id, work_types.idworktype, work_types.work_type, cars.idcar, cars.vin, cars.brand, "
			+ "cars.model, cars.year, cars.fuel_type, cars.volume, cars.transmission, users.iduser, "
			+ "users.login, users.name, users.email, users.phone, users.role, users.status "
			+ "FROM orders "
			+ "JOIN work_types ON orders.work_type_id=work_types.idworktype "
			+ "JOIN cars ON orders.car_id=cars.idcar "
			+ "JOIN users ON cars.user_id=users.iduser "
			+ "WHERE orders.mechanic_id=? AND orders.order_status='ACTIVE'";
	private static final String SQL_UPDATE_STATUS = "UPDATE orders SET order_status=?, mechanic_id=? WHERE idorder=?";

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
			statement.setLong(1, carId);
			statement.setLong(2, workTypeId);
			statement.setObject(3, message);
			statement.setString(4, orderStatus.toString());
			statement.setObject(5, Date.valueOf(date));
			int rowCount = statement.executeUpdate();
			isAdded = (rowCount != 0);
		} catch (ConnectionPoolException | SQLException e) {
			log.error("exception while adding new order", e);
			throw new DaoException("database error", e);
		}
		return isAdded;
	}
	
	@Override
	public List<Order> findAll() throws DaoException {
		List<Order> orders = new ArrayList<>();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_ORDER)) {
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Order order = DaoEntityBuilder.buildOrder(resultSet);
				orders.add(order);
			}
		} catch (ConnectionPoolException | SQLException e) {
			log.error("exception while finding an order", e);
			throw new DaoException("database error", e);
		}
		return orders;
	}
	
	@Override
	public List<Order> findOrderByMechanic(long mechanicId) throws DaoException {
		List<Order> orders = new ArrayList<>();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_ORDER_BY_MECHANIC)) {
			statement.setLong(1, mechanicId);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Order order = DaoEntityBuilder.buildOrder(resultSet);
				orders.add(order);
			}
		} catch (ConnectionPoolException | SQLException e) {
			log.error("exception while finding an order", e);
			throw new DaoException("database error", e);
		}
		return orders;
	}
	
	@Override
	public boolean update(Order order) throws DaoException {
		boolean isUpdate;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_STATUS)) {
			long orderId = order.getOrderId();
			long mechanicId = order.getMechanicId();
			OrderStatus status = order.getStatus();
			statement.setString(1, status.toString());
			statement.setLong(2, mechanicId);
			statement.setLong(3, orderId);
			int rowCount = statement.executeUpdate();
			isUpdate = (rowCount != 0);
		} catch (ConnectionPoolException | SQLException e) {
			log.error("exception while update order", e);
			throw new DaoException("database error", e);
		}
		return isUpdate;
	}
	
	@Override
	public Order findEntityById(Long id) throws DaoException {
		throw new UnsupportedOperationException("operation not supported for class " + this.getClass().getName());
	}

	@Override
	public boolean delete(Long id) throws DaoException {
		throw new UnsupportedOperationException("operation not supported for class " + this.getClass().getName());
	}
}
