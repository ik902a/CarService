package by.epam.learn.validator;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.epam.learn.controller.command.DataKeyword.*;

public class UserDataValidator {
	private static final Pattern LOGIN_PATTERN = Pattern.compile("[.\\w]{5,20}");
    private static final Pattern NAME_PATTERN = Pattern.compile("[\\p{Alpha}\\s-]{0,40}");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w+&*-]+(?:\\.[\\w+&*-]+)*@(?:[\\p{Alnum}-]+.)+[\\p{Alpha}]{2,7}$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("[.\\w]{5,20}");

    //private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[\\d])(?=.*[\\p{Lower}])(?=.*[\\p{Upper}])(?=.*[@#$%^&()])(?=\\S+).{8,20}$");
    
    private UserDataValidator() {
    }
    
    public static boolean isLogin(String inputtedData) {
        if (inputtedData == null || inputtedData.isBlank()) return false;
        Matcher matcher = LOGIN_PATTERN.matcher(inputtedData);
        return matcher.matches();
    }
    
    public static boolean isName(String inputtedData) {
        if(inputtedData == null || inputtedData.isBlank()) {
        	return false;
        }
        Matcher matcher = NAME_PATTERN.matcher(inputtedData);
        return matcher.matches();
    }

    public static boolean isPassword(String inputtedData) {
        if (inputtedData == null || inputtedData.isBlank()) {
        	return false;
        }
        Matcher matcher = PASSWORD_PATTERN.matcher(inputtedData);
        return matcher.matches();
    }

    public static boolean isEmail(String inputtedData) {
        if (inputtedData == null || inputtedData.isBlank()) {
        	return false;
        }
        Matcher matcher = EMAIL_PATTERN.matcher(inputtedData);
        return matcher.matches();
    }
    
    public static boolean areValidData(Map<String, String> userData) {
        boolean valid = true;
        String loginValue = userData.get(LOGIN_KEY);
        if (!isLogin(loginValue)) {
            userData.put(LOGIN_KEY, loginValue + INCORRECT_VALUE);
            valid = false;
        }
        String nameValue = userData.get(NAME_KEY);
        if (!isName(nameValue)) {
            userData.put(NAME_KEY, nameValue + INCORRECT_VALUE);
            valid = false;
        }
        String emailValue = userData.get(EMAIL_KEY);
        if (!isEmail(emailValue)) {
            userData.put(EMAIL_KEY, emailValue + INCORRECT_VALUE);
            valid = false;
        }
        String passwordValue = userData.get(PASSWORD_KEY);
        if (!isPassword(passwordValue)) {
            userData.put(PASSWORD_KEY, passwordValue + INCORRECT_VALUE);
            valid = false;
        }
        String confirmingPasswordValue = userData.get(CONFIRMING_PASSWORD_KEY);
        if (!passwordValue.equals(confirmingPasswordValue)) {
            userData.put(CONFIRMING_PASSWORD_KEY, confirmingPasswordValue + DOESNT_MATCH);
            valid = false;
        }
        return valid;
    }
}
