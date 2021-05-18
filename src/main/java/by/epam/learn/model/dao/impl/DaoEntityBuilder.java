package by.epam.learn.model.dao.impl;

import static by.epam.learn.model.dao.ColumnName.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import by.epam.learn.entity.Car;
import by.epam.learn.entity.Order;
import by.epam.learn.entity.OrderStatus;
import by.epam.learn.entity.User;
import by.epam.learn.entity.UserRole;
import by.epam.learn.entity.UserStatus;
import by.epam.learn.entity.WorkType;

final class DaoEntityBuilder {
	
	private DaoEntityBuilder() {
	}
	
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
	
	static Order buildOrder(ResultSet resultSet) throws SQLException {
		long orderId = resultSet.getLong(ORDERS_IDORDER);
		Car car = DaoEntityBuilder.buildCar(resultSet);
		WorkType workType = DaoEntityBuilder.buildWorkType(resultSet);
		String message = resultSet.getNString(ORDERS_MESSAGE);
		OrderStatus orderStatus = OrderStatus.valueOf(resultSet.getNString(ORDERS_ORDER_STATUS));
		
		//LocalDate date = LocalDate.parse(resultSet.getNString(ORDERS_DATE).substring(0, 10));// TODO
		LocalDate date = resultSet.getDate(ORDERS_DATE).toLocalDate();// TODO
		long mechanicId = resultSet.getLong(ORDERS_MECHANIC_ID);
		
		Order order = new Order(orderId, car, workType, message, orderStatus, date, mechanicId);
		return order;
	}
	
	static WorkType buildWorkType(ResultSet resultSet) throws SQLException {
		long workTypeId = resultSet.getLong(WORK_TYPES_IDWORKTYPE);
		String workTypeName = resultSet.getNString(WORK_TYPSE_WORK_TYPE);
		WorkType workType = new WorkType(workTypeId, workTypeName);
		return workType;
	}
}
