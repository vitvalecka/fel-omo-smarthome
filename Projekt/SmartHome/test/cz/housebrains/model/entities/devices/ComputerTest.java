package cz.housebrains.model.entities.devices;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import cz.housebrains.model.entities.Room;
import cz.housebrains.model.entities.details.Consumption;

public class ComputerTest {
    
    /**
     * Test for creating the new PC
     */
	@Test
	public void computerCreationTest() {
		Computer comp = new Computer("PC", new Room("LivingRoom", 1),
				new Consumption(200, 200, 0, 0, 0, 0));
		comp.use();
		assertEquals("PC", comp.getName());
	}
}
