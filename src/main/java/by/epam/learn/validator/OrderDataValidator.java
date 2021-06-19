package by.epam.learn.validator;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static by.epam.learn.controller.command.DataKeyword.*;

/**
 * The {@code OrderDateValidator} class validates order info
 * 
 * @author Ihar Klepcha
 */
public class OrderDataValidator {
	private static final Pattern MESSAGE_PATTERN = Pattern.compile("^[0-9a-zA-Zа-яёА-ЯЁ .,!?;:]{0,400}$");

    private OrderDataValidator() {
    }
    
    /**
  	 * Checks if message is valid
  	 * 
  	 * @param inputtedData {@link String} data
  	 * @return boolean true if data is valid, else false
  	 */
    public static boolean isMessage(String inputtedData) {
        if(inputtedData == null) {
        	return false;
        }
        Matcher matcher = MESSAGE_PATTERN.matcher(inputtedData);
        return matcher.matches();
    }
    
    /**
 	 * Checks if data is not empty
 	 * 
 	 * @param inputtedData {@link String} data
 	 * @return boolean true if data is valid, else false
 	 */
    public static boolean isEmpty(String inputtedData) {
        return inputtedData == null;
    }
    
	/**
	 * Checks for invalid order data
	 * 
	 * @param orderData {@link Map} of {@link String} and {@link String} order data values
	 * @return boolean true if car info is valid, else false
	 */
    public static boolean areValidData(Map<String, String> orderData) {
    	boolean valid = true;
        String modelValue = orderData.get(MESSAGE_KEY);
        if (!isMessage(modelValue)) {
            orderData.put(MESSAGE_KEY, modelValue + INCORRECT_VALUE);
            valid = false;
        }
        String carIdValue = orderData.get(CAR_ID_KEY);
        if (isEmpty(carIdValue)) {
            orderData.put(CAR_ID_KEY, INCORRECT_VALUE);
            valid = false;
        }
        String workTypeIdValue = orderData.get(WORK_ID_KEY);
        if (isEmpty(workTypeIdValue)) {
            orderData.put(WORK_ID_KEY, INCORRECT_VALUE);
            valid = false;
        }
        return valid;
    }
}
