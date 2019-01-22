package cz.housebrains.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ConstantsTest {

	/**
	 * Test the constants values
	 */
	@Test
	public void test() {
		Constants obj = new Constants();
		assertEquals(24, Constants.DEFAULT_WEATHER_TEMPERATURE);
		assertEquals(false, Constants.DEFAULT_WINDOWS_CLOSED);
		assertEquals(20, Constants.WEATHER_CLOUDY_INFLEX_TEMP);
		assertEquals(10, Constants.WEATHER_RAINY_INFLEX_TEMP);
	}
}
