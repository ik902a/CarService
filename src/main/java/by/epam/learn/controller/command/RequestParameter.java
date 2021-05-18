package by.epam.learn.controller.command;

public final class RequestParameter {
	//User
	public static final String LOGIN = "login";
	public static final String NAME = "name";
	public static final String EMAIL = "email";
	public static final String PASSWORD = "password";
	public static final String PASSWORD_CONFIRMING = "passwordConfirming";
	public static final String PHONE = "phone";
	public static final String ROLE = "role";
	public static final String STATUS = "status";
	public static final String LOCALE = "locale";
	//Car
	public static final String VIN = "vin";
	public static final String BRAND = "brand";
	public static final String MODEL = "model";
	public static final String YEAR = "year";
	public static final String FUEL = "fuel";
	public static final String VOLUME = "volume";
	public static final String TRANSMISSION = "transmission";
	public static final String CAR_LIST = "carList";
	public static final String WORK_LIST = "workList";
	//Order
	public static final String CAR_ORDER = "carOrder";
	public static final String WORK_ORDER = "workOrder";
	public static final String MESSAGE_ORDER = "messageOrder";
	public static final String ORDER_LIST = "orderList";
	//Manager
	public static final String MECHANIC_LIST = "mechanicList";
	
	//Message
	public static final String ERROR_MESSAGE_LIST = "errorMessageList";
	public static final String ERROR_MESSAGE = "error_message";
	public static final String INFO_MESSAGE = "info_message";
	
	
	private RequestParameter() {
	}
}
