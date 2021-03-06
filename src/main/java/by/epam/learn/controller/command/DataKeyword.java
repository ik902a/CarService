package by.epam.learn.controller.command;

/**
 * The {@code DataKeyword} class contains all keywords for data
 * 
 * @author Ihar Klepcha
 */
public final class DataKeyword {
	//User
	public static final String ID_KEY = "id";
    public static final String LOGIN_KEY = "login";
    public static final String NAME_KEY = "name";
    public static final String EMAIL_KEY = "email";
    public static final String PHONE_KEY = "phone";
    public static final String PASSWORD_KEY = "password";
    public static final String CONFIRMING_PASSWORD_KEY = "confirmingPassword";
    public static final String ROLE_KEY = "role";
    public static final String STATUS_KEY = "status";
    //Info message
    public static final String INCORRECT_VALUE = " INCORRECT";
    public static final String DOESNT_MATCH = " doesn't match";
    public static final String ALREADY_EXISTS = " already exists";
	//Car
	public static final String VIN_KEY = "vin";
	public static final String BRAND_KEY = "brand";
	public static final String MODEL_KEY = "model";
	public static final String YEAR_KEY = "year";
	public static final String FUEL_KEY = "fuel";
	public static final String VOLUME_KEY = "volume";
	public static final String TRANSMISSION_KEY = "transmission";
	//Order
	public static final String CAR_ID_KEY = "carId";
	public static final String WORK_ID_KEY = "workId";
	public static final String MESSAGE_KEY = "message";
	//Price
	public static final String OPERATION_KEY = "operation";
	public static final String PRICE_KEY = "price";
	public static final String WORK_TYPE_KEY = "workType";

    private DataKeyword() {
    }
}
