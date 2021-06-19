package test.epam.learn.validator;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import by.epam.learn.validator.OrderDataValidator;

public class OrderDataValidatorTest {
	
	@Test(dataProvider = "validMessage")
	public void  isMessagePositiveTest(String inputtedData) {		
		boolean value = OrderDataValidator.isMessage(inputtedData);
		assertTrue(value);
	}

	@Test(dataProvider = "invalidMessage")
	public void isMessageNegativeTest(String inputtedData) {
		boolean value = OrderDataValidator.isMessage(inputtedData);
		assertFalse(value);
	}
	
	@Test(dataProvider = "validEmpty")
	public void  isEmptyPositiveTest(String inputtedData) {		
		boolean value = OrderDataValidator.isEmpty(inputtedData);
		assertTrue(value);
	}

	@Test(dataProvider = "invalidEmpty")
	public void isEmptyNegativeTest(String inputtedData) {
		boolean value = OrderDataValidator.isEmpty(inputtedData);
		assertFalse(value);
	}
	
	@DataProvider(name = "validMessage")
	public static Object[][] createValidMessage() {
		return new Object[][] { { "Help me!" }, { "Please" }, { "Поможите" } };
	}

	@DataProvider(name = "invalidMessage")
	public static Object[][] createInvalidMessage() {
		return new Object[][] { { "(message)" }, { null }, { "<script>" } };
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
