package test.epam.learn.validator;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import by.epam.learn.validator.UserDataValidator;

public class UserDataValidatorTest {
	
	@Test(dataProvider = "validLogin")
	public void  isLoginPositiveTest(String inputtedData) {		
		boolean value = UserDataValidator.isLogin(inputtedData);
		assertTrue(value);
	}

	@Test(dataProvider = "invalidLogin")
	public void isLoginNegativeTest(String inputtedData) {
		boolean value = UserDataValidator.isLogin(inputtedData);
		assertFalse(value);
	}
	
	@Test(dataProvider = "validName")
	public void  isNamePositiveTest(String inputtedData) {		
		boolean value = UserDataValidator.isName(inputtedData);
		assertTrue(value);
	}

	@Test(dataProvider = "invalidName")
	public void isNameNegativeTest(String inputtedData) {
		boolean value = UserDataValidator.isName(inputtedData);
		assertFalse(value);
	}
	
	@Test(dataProvider = "validPassword")
	public void  isPasswordPositiveTest(String inputtedData) {		
		boolean value = UserDataValidator.isPassword(inputtedData);
		assertTrue(value);
	}

	@Test(dataProvider = "invalidPassword")
	public void isPasswordNegativeTest(String inputtedData) {
		boolean value = UserDataValidator.isPassword(inputtedData);
		assertFalse(value);
	}
	
	@Test(dataProvider = "validEmail")
	public void  isEmailPositiveTest(String inputtedData) {		
		boolean value = UserDataValidator.isEmail(inputtedData);
		assertTrue(value);
	}

	@Test(dataProvider = "invalidEmail")
	public void isEmailNegativeTest(String inputtedData) {
		boolean value = UserDataValidator.isEmail(inputtedData);
		assertFalse(value);
	}
	
	@Test(dataProvider = "validPhone")
	public void  isPhonePositiveTest(String inputtedData) {		
		boolean value = UserDataValidator.isPhone(inputtedData);
		assertTrue(value);
	}

	@Test(dataProvider = "invalidPhone")
	public void isPhoneNegativeTest(String inputtedData) {
		boolean value = UserDataValidator.isPhone(inputtedData);
		assertFalse(value);
	}
	
	@Test(dataProvider = "validEmpty")
	public void  isEmptyPositiveTest(String inputtedData) {		
		boolean value = UserDataValidator.isEmpty(inputtedData);
		assertTrue(value);
	}

	@Test(dataProvider = "invalidEmpty")
	public void isEmptyNegativeTest(String inputtedData) {
		boolean value = UserDataValidator.isEmpty(inputtedData);
		assertFalse(value);
	}
	
	@DataProvider(name = "validLogin")
	public static Object[][] createValidLogin() {
		return new Object[][] { { "vasya" }, { "vasiya1" }, { "First" } };
	}

	@DataProvider(name = "invalidLogin")
	public static Object[][] createInvalidLogin() {
		return new Object[][] { { "по русски" }, { null }, { "<script>" } };
	}
	
	@DataProvider(name = "validName")
	public static Object[][] createValidName() {
		return new Object[][] { { "Вася" }, { "vasy-a" }, { "Василий Петрович" } };
	}

	@DataProvider(name = "invalidName")
	public static Object[][] createInvalidName() {
		return new Object[][] { { "вася, петрович" }, { null }, { "<script>" } };
	}
	
	@DataProvider(name = "validPassword")
	public static Object[][] createValidPassword() {
		return new Object[][] { { "1first" }, { "2Second" }, { "009bond" } };
	}

	@DataProvider(name = "invalidPassword")
	public static Object[][] createInvalidPassword() {
		return new Object[][] { { "юра" }, { null }, { "one" } };
	}
	
	@DataProvider(name = "validEmail")
	public static Object[][] createValidEmail() {
		return new Object[][] { { "vasya@rambler.ru" }, { "sveta@gmail.com" }, { "009bond@mail.ru" } };
	}

	@DataProvider(name = "invalidEmail")
	public static Object[][] createInvalidEmail() {
		return new Object[][] { { "юра" }, { null }, { "one@two" } };
	}
	
	@DataProvider(name = "validPhone")
	public static Object[][] createValidPhone() {
		return new Object[][] { { "448883322" }, { "334567891" }, { "259999999" } };
	}

	@DataProvider(name = "invalidPhone")
	public static Object[][] createInvalidPhone() {
		return new Object[][] { { "три" }, { "" }, { "two" } };
	}
	
	@DataProvider(name = "validEmpty")
	public static Object[] createValidEmpty() {
		return new Object[] { null };
	}

	@DataProvider(name = "invalidEmpty")
	public static Object[][] createInvalidEmpty() {
		return new Object[][] { { "0" }, { " " }, { "null" } };
	}
}
