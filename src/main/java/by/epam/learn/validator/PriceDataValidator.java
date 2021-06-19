package by.epam.learn.validator;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.epam.learn.controller.command.DataKeyword.*;

/**
 * The {@code PriceDateValidator} class validates car info
 * 
 * @author Ihar Klepcha
 */
public class PriceDataValidator {
	private static final Pattern OPERATION_PATTERN = Pattern.compile("^[0-9a-zA-Zа-яёА-ЯЁ .,!?;:-]{0,100}$");
	private static final Pattern PRICE_PATTERN = Pattern.compile("^[\\d]*[.]?[\\d]+{1,13}$");

    private PriceDataValidator() {
    }
    
    /**
   	 * Checks if operation is valid
   	 * 
   	 * @param inputtedData {@link String} data
   	 * @return boolean true if data is valid, else false
   	 */
    public static boolean isOperation(String inputtedData) {
        if(inputtedData == null) {
        	return false;
        }
        Matcher matcher = OPERATION_PATTERN.matcher(inputtedData);
        return matcher.matches();
    }
    
    /**
   	 * Checks if price is valid
   	 * 
   	 * @param inputtedData {@link String} data
   	 * @return boolean true if data is valid, else false
   	 */
    public static boolean isPrice(String inputtedData) {
        if(inputtedData == null) {
        	return false;
        }
        Matcher matcher = PRICE_PATTERN.matcher(inputtedData);
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
	 * Checks for invalid price data
	 * 
	 * @param priceData {@link Map} of {@link String} and {@link String} price data values
	 * @return boolean true if price info is valid, else false
	 */
    public static boolean areValidData(Map<String, String> priceData) {
    	boolean valid = true;
        String operationValue = priceData.get(OPERATION_KEY);
        if (priceData.containsKey(OPERATION_KEY) && !isOperation(operationValue)) {
            priceData.put(OPERATION_KEY, operationValue + INCORRECT_VALUE);
            valid = false;
        }
        String priceValue = priceData.get(PRICE_KEY);
        if (priceData.containsKey(PRICE_KEY) && !isPrice(priceValue)) {
            priceData.put(PRICE_KEY, priceValue + INCORRECT_VALUE);
            valid = false;
        }
        String workTypeValue = priceData.get(WORK_TYPE_KEY);
        if (priceData.containsKey(WORK_TYPE_KEY) && isEmpty(workTypeValue)) {
            priceData.put(WORK_TYPE_KEY, INCORRECT_VALUE);
            valid = false;
        }
        return valid;
    }
}
