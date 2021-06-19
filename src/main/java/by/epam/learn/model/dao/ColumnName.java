package by.epam.learn.model.dao;

/**
 * The {@code ColumnName} class describes all column name
 * 
 * @author Ihar Klepcha
 */
public class ColumnName {
	//users
	public static final String USERS_IDUSER = "iduser";
	public static final String USERS_LOGIN = "login";
	public static final String USERS_NAME = "name";
	public static final String USERS_EMAIL = "email";
	public static final String USERS_PASSWORD = "password";
	public static final String USERS_PHONE = "phone";
	public static final String USERS_ROLE = "role";
	public static final String USERS_STATUS = "status";
	//cars
	public static final String CARS_IDCAR = "idcar";
	public static final String CARS_USER_ID = "user_id";
	public static final String CARS_VIN = "vin";
	public static final String CARS_BRAND = "brand";
	public static final String CARS_MODEL = "model";
	public static final String CARS_YEAR = "year";
	public static final String CARS_FUEL = "fuel_type";
	public static final String CARS_VOLUME = "volume";
	public static final String CARS_TRANSMISSION = "transmission";
	//work_types
	public static final String WORK_TYPES_IDWORKTYPE = "idworktype";
	public static final String WORK_TYPSE_WORK_TYPE = "work_type";
	//orders
	public static final String ORDERS_IDORDER = "idorder";
	public static final String ORDERS_CAR_ID = "car_id";
	public static final String ORDERS_WORK_TYPE_ID = "work_type_id";
	public static final String ORDERS_MESSAGE = "message";
	public static final String ORDERS_ORDER_STATUS = "order_status";
	public static final String ORDERS_DATE = "date";
	public static final String ORDERS_MECHANIC_ID = "mechanic_id";
	//prices
	public static final String PRICES_IDPRICE = "idprice";
	public static final String PRICES_OPERATION = "operation";
	public static final String PRICES_PRICE = "price";
	public static final String PRICES_WORK_TYPE_ID = "work_type_id";
	//invoices
	public static final String INVOICES_IDINVOICE = "idinvoice";
	public static final String INVOICES_USER_ID = "user_id";
	public static final String INVOICES_PRICE_ID = "price_id";
	public static final String INVOICES_DATE = "date";

	private ColumnName() {
	}
}
