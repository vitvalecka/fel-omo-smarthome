package cz.housebrains.model.entities.devices;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

import cz.housebrains.model.entities.Room;
import cz.housebrains.model.entities.details.Consumption;

public class FreezerTest {

	/**
	 * Test for repairing freezer with full health.
	 */
	@Test
	public void frezerRepairFullHealthUnsuccessfulTest() {
		Freezer frezer = new Freezer("Freezer1", new Room("Hall", 1),
				new Consumption(100, 200, 0, 0, 0, 0));
		assertFalse(frezer.repair());
	}
}
