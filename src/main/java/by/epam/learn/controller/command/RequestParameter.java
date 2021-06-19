package by.epam.learn.controller.command;

/**
 * The {@code RequestParameter} class contains all parameters
 * 
 * @author Ihar Klepcha
 */
public final class RequestParameter {
	public static final String LOCALE = "locale";
	public static final String CURRENT_PAGE = "current_page";
	public static final String ID = "id";
	public static final String MECHANIC_ID = "mechanicId";
	//User
	public static final String USER = "user";
	public static final String LOGIN = "login";
	public static final String OLD_LOGIN = "oldLogin";
	public static final String NAME = "name";
	public static final String EMAIL = "email";
	public static final String PHONE = "phone";
	public static final String ROLE = "role";
	public static final String STATUS = "status";
	public static final String PASSWORD = "password";
	public static final String PASSWORD_CONFIRMING = "passwordConfirming";
	//Car
	public static final String VIN = "vin";
	public static final String BRAND = "brand";
	public static final String MODEL = "model";
	public static final String YEAR = "year";
	public static final String FUEL = "fuel";
	public static final String VOLUME = "volume";
	public static final String TRANSMISSION = "transmission";
	//Order
	public static final String ORDER = "order";
	public static final String CAR_ORDER = "carOrder";
	public static final String WORK_ORDER = "workOrder";
	public static final String MESSAGE_ORDER = "messageOrder";
	//Price
	public static final String OPERATION = "operation";
	public static final String PRICE = "price";
	public static final String WORK_TYPE = "workType";
	
	private RequestParameter() {
	}
}
