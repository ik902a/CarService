package by.epam.learn.validator;

import static by.epam.learn.controller.command.DataKeyword.*;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CarDataValidator {
	private static final Pattern VIN_PATTERN = Pattern.compile("^(?i)[A-HJ-NPR-Z0-9]{17}$");
    private static final Pattern STRING_PATTERN = Pattern.compile("^[0-9a-zA-Zа-яёА-ЯЁ\\-\\s]{1,45}$");
    private static final Pattern YEAR_PATTERN = Pattern.compile("^[\\d]{4}$");
    private static final Pattern VOLUME_PATTERN = Pattern.compile("^[\\d]*[.,]?[\\d]+{1,45}$");
    
    private CarDataValidator() {
    }
    
    public static boolean isVin(String inputtedData) {
        if (inputtedData == null || inputtedData.isBlank()) {
        	return false;
        }
        Matcher matcher = VIN_PATTERN.matcher(inputtedData);
        return matcher.matches();
    }
    
    public static boolean isString(String inputtedData) {
        if(inputtedData == null || inputtedData.isBlank()) {
        	return false;
        }
        Matcher matcher = STRING_PATTERN.matcher(inputtedData);
        return matcher.matches();
    }
    
    public static boolean isYear(String inputtedData) {
        if (inputtedData == null || inputtedData.isBlank()) {
        	return false;
        }
        Matcher matcher = YEAR_PATTERN.matcher(inputtedData);
        return matcher.matches();
    }
    
    public static boolean isVolume(String inputtedData) {
        if (inputtedData == null || inputtedData.isBlank()) {
        	return false;
        }
        Matcher matcher = VOLUME_PATTERN.matcher(inputtedData);
        return matcher.matches();
    }
    
    public static boolean isEmpty(String inputtedData) {
    	boolean result = false;
        if(inputtedData == null || inputtedData.isBlank()) {
        	result = true;
        }
        return result;
    }
    
    public static boolean areValidData(Map<String, String> userData) {
        boolean valid = true;
        String vinValue = userData.get(VIN_KEY);
        if (!isVin(vinValue)) {
            userData.put(VIN_KEY, vinValue + INCORRECT_VALUE);
            valid = false;
        }
        String brandValue = userData.get(BRAND_KEY);
        if (!isString(brandValue)) {
            userData.put(BRAND_KEY, brandValue + INCORRECT_VALUE);
            valid = false;
        }
        String modelValue = userData.get(MODEL_KEY);
        if (!isString(modelValue)) {
            userData.put(MODEL_KEY, modelValue + INCORRECT_VALUE);
            valid = false;
        }
        String fuelValue = userData.get(FUEL_KEY);
        if (isEmpty(fuelValue)) {
            userData.put(FUEL_KEY, INCORRECT_VALUE);
            valid = false;
        }
        String yearValue = userData.get(YEAR_KEY);
        if (!isYear(yearValue)) {
            userData.put(YEAR_KEY, yearValue + INCORRECT_VALUE);
            valid = false;
        }
        String volumeValue = userData.get(VOLUME_KEY);
        if (!isVolume(yearValue)) {
            userData.put(VOLUME_KEY, volumeValue + INCORRECT_VALUE);
            valid = false;
        }
        String transmissionValue = userData.get(TRANSMISSION_KEY);
        if (isEmpty(transmissionValue)) {
            userData.put(TRANSMISSION_KEY, INCORRECT_VALUE);
            valid = false;
        }
        return valid;
    }
}
