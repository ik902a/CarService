package by.epam.learn.validator;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static by.epam.learn.controller.command.DataKeyword.*;

public class OrderDataValidator {
	private static final Pattern MESSAGE_PATTERN = Pattern.compile("^[0-9a-zA-Zа-яёА-ЯЁ .,!?;:]{0,400}$");

    private OrderDataValidator() {
    }
    
    public static boolean isMessage(String inputtedData) {
        if(inputtedData == null) {
        	return false;
        }
        Matcher matcher = MESSAGE_PATTERN.matcher(inputtedData);
        return matcher.matches();
    }
    
    public static boolean areValidData(Map<String, String> userData) {
    	boolean valid = true;
        String modelValue = userData.get(MESSAGE_KEY);
        if (!isMessage(modelValue)) {
            userData.put(MESSAGE_KEY, modelValue + INCORRECT_VALUE);
            valid = false;
        }
        return valid;
    }
}
