package by.epam.learn.validator;

import static by.epam.learn.controller.command.DataKeyword.*;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The {@code CarDateValidator} class validates car info
 * 
 * @author Ihar Klepcha
 */
public class CarDataValidator {
	private static final Pattern VIN_PATTERN = Pattern.compile("^(?i)[A-HJ-NPR-Z0-9]{17}$");
    private static final Pattern STRING_PATTERN = Pattern.compile("^[0-9a-zA-Zа-яёА-ЯЁ\\-\\s]{1,45}$");
    private static final Pattern YEAR_PATTERN = Pattern.compile("^[\\d]{4}$");
    private static final Pattern VOLUME_PATTERN = Pattern.compile("^[\\d]*[.,]?[\\d]+{1,45}$");
    
    private CarDataValidator() {
    }
    
    /**
  	 * Checks if vin number is valid
  	 * 
  	 * @param inputtedData {@link String} data
  	 * @return boolean true if data is valid, else false
  	 */
    public static boolean isVin(String inputtedData) {
        if (inputtedData == null || inputtedData.isBlank()) {
        	return false;
        }
        Matcher matcher = VIN_PATTERN.matcher(inputtedData);
        return matcher.matches();
    }
    
    /**
  	 * Checks if data is valid
  	 * 
  	 * @param inputtedData {@link String} data
  	 * @return boolean true if data is valid, else false
  	 */
    public static boolean isString(String inputtedData) {
        if(inputtedData == null || inputtedData.isBlank()) {
        	return false;
        }
        Matcher matcher = STRING_PATTERN.matcher(inputtedData);
        return matcher.matches();
    }
    
    /**
  	 * Checks if year of production is valid
  	 * 
  	 * @param inputtedData {@link String} data
  	 * @return boolean true if data is valid, else false
  	 */
    public static boolean isYear(String inputtedData) {
        if (inputtedData == null || inputtedData.isBlank()) {
        	return false;
        }
        Matcher matcher = YEAR_PATTERN.matcher(inputtedData);
        return matcher.matches();
    }
    
    /**
  	 * Checks if volume engine is valid
  	 * 
  	 * @param inputtedData {@link String} data
  	 * @return boolean true if data is valid, else false
  	 */
    public static boolean isVolume(String inputtedData) {
        if (inputtedData == null || inputtedData.isBlank()) {
        	return false;
        }
        Matcher matcher = VOLUME_PATTERN.matcher(inputtedData);
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
	 * Checks for invalid car data
	 * 
	 * @param carData {@link Map} of {@link String} and {@link String} car data values
	 * @return boolean true if car info is valid, else false
	 */
    public static boolean areValidData(Map<String, String> carData) {
        boolean valid = true;
        String vinValue = carData.get(VIN_KEY);
        if (!isVin(vinValue)) {
            carData.put(VIN_KEY, vinValue + INCORRECT_VALUE);
            valid = false;
        }
        String brandValue = carData.get(BRAND_KEY);
        if (!isString(brandValue)) {
            carData.put(BRAND_KEY, brandValue + INCORRECT_VALUE);
            valid = false;
        }
        String modelValue = carData.get(MODEL_KEY);
        if (!isString(modelValue)) {
            carData.put(MODEL_KEY, modelValue + INCORRECT_VALUE);
            valid = false;
        }
        String fuelValue = carData.get(FUEL_KEY);
        if (isEmpty(fuelValue)) {
            carData.put(FUEL_KEY, INCORRECT_VALUE);
            valid = false;
        }
        String yearValue = carData.get(YEAR_KEY);
        if (!isYear(yearValue)) {
            carData.put(YEAR_KEY, yearValue + INCORRECT_VALUE);
            valid = false;
        }
        String volumeValue = carData.get(VOLUME_KEY);
        if (!isVolume(yearValue)) {
            carData.put(VOLUME_KEY, volumeValue + INCORRECT_VALUE);
            valid = false;
        }
        String transmissionValue = carData.get(TRANSMISSION_KEY);
        if (isEmpty(transmissionValue)) {
            carData.put(TRANSMISSION_KEY, INCORRECT_VALUE);
            valid = false;
        }
        return valid;
    }
}
