package cz.housebrains.model;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class WeatherTest {

	/**
	 * Test will call update method and check whether values are set or null
	 */
	@Test
	public void Test() {

		Weather weather = Weather.getInstance();
		weather.update();
		assertNotNull(weather.getCurrentWeather());
		assertNotNull(weather.getCurrentTemperature());
		assertNotNull(weather.isCloudy());
		assertNotNull(weather.isOvercast());
		assertNotNull(weather.isRainy());
		assertNotNull(weather.isStorm());
		assertNotNull(weather.isSunny());
	}
}
