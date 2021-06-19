package by.epam.learn.controller.command;

/**
 * The {@code AttributeParameter} class contains all attributes
 * 
 * @author Ihar Klepcha
 */
public final class AttributeParameter {
	public static final String LOCALE = "locale";
	public static final String LANGUAGE = "lang";
	public static final String EXCEPTION = "exception";
	//User
	public static final String USER = "user";
	public static final String USER_DATA = "userData";
	public static final String LOGIN = "login";
	public static final String NAME = "name";
	public static final String EMAIL = "email";
	public static final String PHONE = "phone";
	public static final String ROLE = "role";
	//Entity
	public static final String PRICE = "price";
	//List
	public static final String USER_LIST = "userList";
	public static final String PRICE_LIST = "priceList";
	public static final String WORK_LIST = "workList";
	public static final String ORDER_LIST = "orderList";
	public static final String MECHANIC_LIST = "mechanicList";
	public static final String CAR_LIST = "carList";
	//Message
	public static final String ERROR_MESSAGE_LIST = "errorMessageList";
	public static final String ERROR_MESSAGE = "errorMessage";
	public static final String INFO_MESSAGE = "infoMessage";
	
	private AttributeParameter() {
	}
}
