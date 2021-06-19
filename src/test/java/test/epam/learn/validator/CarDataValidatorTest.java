package test.epam.learn.validator;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import by.epam.learn.validator.CarDataValidator;

public class CarDataValidatorTest {
	
	@Test(dataProvider = "validVin")
	public void  isVinPositiveTest(String inputtedData) {		
		boolean value = CarDataValidator.isVin(inputtedData);
		assertTrue(value);
	}

	@Test(dataProvider = "invalidVin")
	public void isVinNegativeTest(String inputtedData) {
		boolean value = CarDataValidator.isVin(inputtedData);
		assertFalse(value);
	}
	
	@Test(dataProvider = "validString")
	public void  isStringPositiveTest(String inputtedData) {		
		boolean value = CarDataValidator.isString(inputtedData);
		assertTrue(value);
	}

	@Test(dataProvider = "invalidString")
	public void isStringNegativeTest(String inputtedData) {
		boolean value = CarDataValidator.isString(inputtedData);
		assertFalse(value);
	}
	
	@Test(dataProvider = "validYear")
	public void  isYearPositiveTest(String inputtedData) {		
		boolean value = CarDataValidator.isYear(inputtedData);
		assertTrue(value);
	}

	@Test(dataProvider = "invalidYear")
	public void isYearNegativeTest(String inputtedData) {
		boolean value = CarDataValidator.isYear(inputtedData);
		assertFalse(value);
	}
	
	@Test(dataProvider = "validVolume")
	public void  isVolumePositiveTest(String inputtedData) {		
		boolean value = CarDataValidator.isVolume(inputtedData);
		assertTrue(value);
	}

	@Test(dataProvider = "invalidVolume")
	public void isVolumeNegativeTest(String inputtedData) {
		boolean value = CarDataValidator.isVolume(inputtedData);
		assertFalse(value);
	}
	
	@Test(dataProvider = "validEmpty")
	public void  isEmptyPositiveTest(String inputtedData) {		
		boolean value = CarDataValidator.isEmpty(inputtedData);
		assertTrue(value);
	}

	@Test(dataProvider = "invalidEmpty")
	public void isEmptyNegativeTest(String inputtedData) {
		boolean value = CarDataValidator.isEmpty(inputtedData);
		assertFalse(value);
	}
	
	@DataProvider(name = "validVin")
	public static Object[][] createValidVin() {
		return new Object[][] { { "vwgzzz1tz6w175209" }, { "vwgzzz1tz6w175209" }, { "vwgzzz1tz6w175209" } };
	}

	@DataProvider(name = "invalidVin")
	public static Object[][] createInvalidVin() {
		return new Object[][] { { "фвгззз1тз6в175209" }, { null }, { "vwgzzz1t6w175209" } };
	}
	
	@DataProvider(name = "validString")
	public static Object[][] createValidString() {
		return new Object[][] { { "Вася" }, { "vasy-a" }, { "Василий Петрович" } };
	}

	@DataProvider(name = "invalidString")
	public static Object[][] createInvalidString() {
		return new Object[][] { { "вася, петрович" }, { null }, { "<script>" } };
	}
	
	@DataProvider(name = "validYear")
	public static Object[][] createValidYear() {
		return new Object[][] { { "2020" }, { "1988" }, { "1000" } };
	}

	@DataProvider(name = "invalidYear")
	public static Object[][] createInvalidYear() {
		return new Object[][] { { "12" }, { null }, { "19999" } };
	}
	
	@DataProvider(name = "validVolume")
	public static Object[][] createValidVolume() {
		return new Object[][] { { "2.20" }, { "1,9" }, { "2" } };
	}

	@DataProvider(name = "invalidVolume")
	public static Object[][] createInvalidVolume() {
		return new Object[][] { { "1-2" }, { null }, { "поллитра" } };
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
