package cz.housebrains.model.entities;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cz.housebrains.model.entities.details.Consumption;
import cz.housebrains.model.entities.devices.Computer;
import cz.housebrains.model.entities.devices.Freezer;

public class PersonTest {

	/**
	 * Create person add usable device and tools
	 */
	@Test
	public void test() {
		Room bedroom = new Room("bedroom", 1);
		Person person = new Person("John", "male", 21, bedroom);
		person.setCurrentRoom(bedroom);
		Device notebook1 = new Computer("ASUS notebook", bedroom,
				new Consumption(50, 2, 0, 0, 0, 0));
		Device freezer = new Freezer("Freezer1", bedroom, new Consumption(200,
				200, 0, 0, 0, 0));
		List<Device> usableDevices = new ArrayList<>();
		usableDevices.add(notebook1);
		usableDevices.add(freezer);
		person.setUsableDevices(usableDevices);

		List<Tool> usableTools = new ArrayList<>();
		usableTools.add(new Tool("Yoga mat", bedroom));
		usableTools.add(new Tool("Bike 1", bedroom));

		person.setUsableTools(usableTools);

		person.doSomethingRandom();
		assertEquals(21, person.getAge());
		assertEquals("John", person.getName());
		assertEquals("male", person.getSex());

	}
}
