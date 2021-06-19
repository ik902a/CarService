package test.epam.learn.validator;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import by.epam.learn.validator.PriceDataValidator;

public class PriceDataValidatorTest {

	@Test(dataProvider = "validOperation")
	public void  isOperationPositiveTest(String inputtedData) {		
		boolean value = PriceDataValidator.isOperation(inputtedData);
		assertTrue(value);
	}

	@Test(dataProvider = "invalidOperation")
	public void isOperationNegativeTest(String inputtedData) {
		boolean value = PriceDataValidator.isOperation(inputtedData);
		assertFalse(value);
	}

	@Test(dataProvider = "validPrice")
	public void  isPricePositiveTest(String inputtedData) {		
		boolean value = PriceDataValidator.isPrice(inputtedData);
		assertTrue(value);
	}

	@Test(dataProvider = "invalidPrice")
	public void isPriceNegativeTest(String inputtedData) {
		boolean value = PriceDataValidator.isPrice(inputtedData);
		assertFalse(value);
	}
	
	@Test(dataProvider = "validEmpty")
	public void  isEmptyPositiveTest(String inputtedData) {		
		boolean value = PriceDataValidator.isEmpty(inputtedData);
		assertTrue(value);
	}

	@Test(dataProvider = "invalidEmpty")
	public void isEmptyNegativeTest(String inputtedData) {
		boolean value = PriceDataValidator.isEmpty(inputtedData);
		assertFalse(value);
	}
	
	@DataProvider(name = "validOperation")
	public static Object[][] createValidOperation() {
		return new Object[][] { { "Help me!" }, { "Please" }, { "Поможите" } };
	}

	@DataProvider(name = "invalidOperation")
	public static Object[][] createInvalidOperation() {
		return new Object[][] { { "(message)" }, { null }, { "<script>" } };
	}
	
	@DataProvider(name = "validPrice")
	public static Object[][] createValidPrice() {
		return new Object[][] { { "42" }, { "42.42" }, { "42,42" } };
	}

	@DataProvider(name = "invalidPrice")
	public static Object[][] createInvalidPrice() {
		return new Object[][] { { "(42)" }, { null }, { "<script>" } };
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
