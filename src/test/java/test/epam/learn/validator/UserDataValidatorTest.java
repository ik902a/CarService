package test.epam.learn.validator;

import static org.testng.Assert.assertTrue;

import java.util.Map;


import org.testng.annotations.Test;

import by.epam.learn.validator.UserDataValidator;

public class UserDataValidatorTest {
	
	@Test 
	public void  isValidTest() {	
		Map<String, String> userData = Map.ofEntries(
				Map.entry("login", "admin"),
				Map.entry("name", "admin"),
				Map.entry("email", "admin@rambler.ru"),
				Map.entry("password", "11111111"),
				Map.entry("confirmingPassword", "11111111"));
		boolean value = UserDataValidator.areValidData(userData);
		assertTrue(value);
	}
}
