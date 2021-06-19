package by.epam.learn.model.dao.impl;

import static by.epam.learn.model.dao.ColumnName.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import by.epam.learn.entity.Car;
import by.epam.learn.entity.Order;
import by.epam.learn.entity.OrderStatus;
import by.epam.learn.entity.Price;
import by.epam.learn.entity.User;
import by.epam.learn.entity.UserRole;
import by.epam.learn.entity.UserStatus;
import by.epam.learn.entity.WorkType;

/**
 * The {@code DaoEntityBuilder} class builds different entities
 * 
 * @author Ihar Klepcha
 */
final class DaoEntityBuilder {
	
	private DaoEntityBuilder() {
	}
	
	/**
	 * Builds user
	 * 
	 * @param resultSet {@link ResultSet} database result set
	 * @return {@link User}
	 * @throws SQLException
	 */
	static User buildUser(ResultSet resultSet) throws SQLException {
		long userId = resultSet.getLong(USERS_IDUSER);
    	String login = resultSet.getNString(USERS_LOGIN);
        String name = resultSet.getNString(USERS_NAME);
    	String email = resultSet.getNString(USERS_EMAIL);
        String phone = resultSet.getNString(USERS_PHONE);
        UserRole role = UserRole.valueOf(resultSet.getNString(USERS_ROLE));
        UserStatus status = UserStatus.valueOf(resultSet.getNString(USERS_STATUS));
        User user = new User(userId, login, name,  email, phone, role, status);
		return user;
	}
	
	/**
	 * Builds car
	 * 
	 * @param resultSet {@link ResultSet} database result set
	 * @return {@link Car}
	 * @throws SQLException
	 */
	static Car buildCar(ResultSet resultSet) throws SQLException {
		long carId = resultSet.getLong(CARS_IDCAR);
		User user = DaoEntityBuilder.buildUser(resultSet);
    	String vin = resultSet.getNString(CARS_VIN);
        String brand = resultSet.getNString(CARS_BRAND);
    	String model = resultSet.getNString(CARS_MODEL);
        String year = resultSet.getNString(CARS_YEAR);
        String fuel = resultSet.getNString(CARS_FUEL);
        String volume = resultSet.getNString(CARS_VOLUME);
        String transmission = resultSet.getNString(CARS_TRANSMISSION);
        Car car = new Car(carId, user, vin, brand, model, year, fuel, volume, transmission);
		return car;
	}
	
	/**
	 * Builds order
	 * 
	 * @param resultSet {@link ResultSet} database result set
	 * @return {@link Order}
	 * @throws SQLException
	 */
	static Order buildOrder(ResultSet resultSet) throws SQLException {
		long orderId = resultSet.getLong(ORDERS_IDORDER);
		Car car = DaoEntityBuilder.buildCar(resultSet);
		WorkType workType = DaoEntityBuilder.buildWorkType(resultSet);
		String message = resultSet.getNString(ORDERS_MESSAGE);
		OrderStatus orderStatus = OrderStatus.valueOf(resultSet.getNString(ORDERS_ORDER_STATUS));
		LocalDate date = resultSet.getDate(ORDERS_DATE).toLocalDate();
		long mechanicId = resultSet.getLong(ORDERS_MECHANIC_ID);
		Order order = new Order(orderId, car, workType, message, orderStatus, date, mechanicId);
		return order;
	}
	
	/**
	 * Builds price
	 * 
	 * @param resultSet {@link ResultSet} database result set
	 * @return {@link Price}
	 * @throws SQLException
	 */
	static Price buildPrice(ResultSet resultSet) throws SQLException {
		long priceId = resultSet.getLong(PRICES_IDPRICE);
		String operation = resultSet.getNString(PRICES_OPERATION);
		double price = resultSet.getDouble(PRICES_PRICE);
		WorkType workType = DaoEntityBuilder.buildWorkType(resultSet);
		Price pricelist = new Price(priceId, operation, price, workType);
		return pricelist;
	}
	
	/**
	 * Builds type of work
	 * 
	 * @param resultSet {@link ResultSet} database result set
	 * @return {@link WorkType}
	 * @throws SQLException
	 */
	static WorkType buildWorkType(ResultSet resultSet) throws SQLException {
		long workTypeId = resultSet.getLong(WORK_TYPES_IDWORKTYPE);
		String workTypeName = resultSet.getNString(WORK_TYPSE_WORK_TYPE);
		WorkType workType = new WorkType(workTypeId, workTypeName);
		return workType;
	}
}
