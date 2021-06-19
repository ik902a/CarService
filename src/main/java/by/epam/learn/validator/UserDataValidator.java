package by.epam.learn.validator;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.epam.learn.controller.command.DataKeyword.*;

/**
 * The {@code UserDateValidator} class validates user info
 * 
 * @author Ihar Klepcha
 */
public class UserDataValidator {
	private static final Pattern LOGIN_PATTERN = Pattern.compile("^[\\w]{1,20}$");
	private static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Zа-яёА-ЯЁ\\-\\s]{1,45}$");
	private static final Pattern EMAIL_PATTERN = Pattern.compile(
			"^[\\w+&*-]+(?:\\.[\\w+&*-]+)*@(?:[\\w-]+.)+[a-z]{2,7}$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(
    		"^(?=.*[\\d])(?=.*[\\w])(?=\\S+).{4,20}$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("^[.\\d]{9}$");
    
    private UserDataValidator() {
    }
    
    /**
  	 * Checks if login is valid
  	 * 
  	 * @param inputtedData {@link String} data
  	 * @return boolean true if data is valid, else false
  	 */
    public static boolean isLogin(String inputtedData) {
        if (inputtedData == null || inputtedData.isBlank()) {
        	return false;
        }
        Matcher matcher = LOGIN_PATTERN.matcher(inputtedData);
        return matcher.matches();
    }
    
    /**
  	 * Checks if name is valid
  	 * 
  	 * @param inputtedData {@link String} data
  	 * @return boolean true if data is valid, else false
  	 */
    public static boolean isName(String inputtedData) {
        if(inputtedData == null || inputtedData.isBlank()) {
        	return false;
        }
        Matcher matcher = NAME_PATTERN.matcher(inputtedData);
        return matcher.matches();
    }

    /**
  	 * Checks if password is valid
  	 * 
  	 * @param inputtedData {@link String} data
  	 * @return boolean true if data is valid, else false
  	 */
    public static boolean isPassword(String inputtedData) {
        if (inputtedData == null || inputtedData.isBlank()) {
        	return false;
        }
        Matcher matcher = PASSWORD_PATTERN.matcher(inputtedData);
        return matcher.matches();
    }

    /**
  	 * Checks if email is valid
  	 * 
  	 * @param inputtedData {@link String} data
  	 * @return boolean true if data is valid, else false
  	 */
    public static boolean isEmail(String inputtedData) {
        if (inputtedData == null || inputtedData.isBlank()) {
        	return false;
        }
        Matcher matcher = EMAIL_PATTERN.matcher(inputtedData);
        return matcher.matches();
    }   
    
    /**
  	 * Checks if phone number is valid
  	 * 
  	 * @param inputtedData {@link String} data
  	 * @return boolean true if data is valid, else false
  	 */
	public static boolean isPhone(String inputtedData) {
		boolean isPhone = true;
		if (!inputtedData.isBlank() && inputtedData != null) {
			Matcher matcher = PHONE_PATTERN.matcher(inputtedData);
			isPhone = matcher.matches();
		}
		return isPhone;
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
	 * Checks for invalid user data
	 * 
	 * @param userData {@link Map} of {@link String} and {@link String} user data values
	 * @return boolean true if user info is valid, else false
	 */
    public static boolean areValidData(Map<String, String> userData) {
        boolean valid = true;
        String loginValue = userData.get(LOGIN_KEY);
        if (userData.containsKey(LOGIN_KEY) && !isLogin(loginValue)) {
            userData.put(LOGIN_KEY, loginValue + INCORRECT_VALUE);
            valid = false;
        }
        String nameValue = userData.get(NAME_KEY);
        if (userData.containsKey(NAME_KEY) && !isName(nameValue)) {
            userData.put(NAME_KEY, nameValue + INCORRECT_VALUE);
            valid = false;
        }
        String emailValue = userData.get(EMAIL_KEY);
        if (userData.containsKey(EMAIL_KEY) && !isEmail(emailValue)) {
            userData.put(EMAIL_KEY, emailValue + INCORRECT_VALUE);
            valid = false;
        }
        String phoneValue = userData.get(PHONE_KEY);
        if (userData.containsKey(PHONE_KEY) && !isPhone(phoneValue)) {
            userData.put(PHONE_KEY, phoneValue + INCORRECT_VALUE);
            valid = false;
        }
        String roleValue = userData.get(ROLE_KEY);
        if (userData.containsKey(ROLE_KEY) && isEmpty(roleValue)) {
            userData.put(ROLE_KEY, roleValue + INCORRECT_VALUE);
            valid = false;
        }
        String passwordValue = userData.get(PASSWORD_KEY);
        if (userData.containsKey(PASSWORD_KEY) && !isPassword(passwordValue)) {
            userData.put(PASSWORD_KEY, passwordValue + INCORRECT_VALUE);
            valid = false;
        }
        String confirmingPasswordValue = userData.get(CONFIRMING_PASSWORD_KEY);
        if (userData.containsKey(CONFIRMING_PASSWORD_KEY) && !passwordValue.equals(confirmingPasswordValue)) {
            userData.put(CONFIRMING_PASSWORD_KEY, confirmingPasswordValue + DOESNT_MATCH);
            valid = false;
        }
        return valid;
    } 
}
