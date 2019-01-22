package cz.housebrains.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import cz.housebrains.model.entities.*;
import org.junit.Test;

import cz.housebrains.model.entities.details.Consumption;
import cz.housebrains.model.entities.devices.Computer;

public class HouseControllerTest {

	/**
	 * Test for adding room and check if it present
	 */
	@Test
	public void addRoomsTest() {
		List<Room> roomList = new ArrayList<>();
		Room livingroom = new Room("livingroom", 1);
		roomList.add(livingroom);
		Room kitchen1 = new Room("kitchen1", 1);
		roomList.add(kitchen1);

		HouseController controllerObj = HouseController.getInstance();
		controllerObj.addRooms(roomList);
		assertEquals("livingroom", controllerObj.getRooms().get(0).getName());
		assertEquals("kitchen1", controllerObj.getRooms().get(1).getName());
	}

	/**
	 * Test for adding people and check if it present
	 */
	@Test
	public void addPeopleTest() {

		List<Person> people = new ArrayList<>();
		Room livingroom = new Room("livingroom", 1);
		Person mom = new Person("Mamka", "female", 42, livingroom);
		people.add(mom);
		Room bedroom = new Room("bedroom", 1);
		Person father = new Person("Tatinek", "male", 45, bedroom);
		people.add(father);
		HouseController controllerObj = HouseController.getInstance();
		controllerObj.addPeople(people);
		assertEquals("Mamka", controllerObj.getPeople().get(0).getName());
		assertEquals("Tatinek", controllerObj.getPeople().get(1).getName());
	}

	/**
	 * Test for adding pet and check if it present
	 */
	@Test
	public void addPetTest() {
		Room livingroom = new Room("livingroom", 1);

		List<Pet> pets = new ArrayList<>();

		Pet dog = new Pet("Lab", "Dog", livingroom);
		pets.add(dog);

		HouseController controllerObj = HouseController.getInstance();
		controllerObj.addPets(pets);
		assertEquals("Lab", controllerObj.getPets().get(0).getAnimal());
	}

	/**
	 * Test for adding tool and check if it present
	 */
	@Test
	public void addToolsTest() {
		Room livingroom = new Room("livingroom", 1);
		List<Tool> tools = new ArrayList<>();
		tools.add(new Tool("Yoga mat", livingroom));
		tools.add(new Tool("Bike 1", livingroom));

		HouseController controllerObj = HouseController.getInstance();
		controllerObj.addTools(tools);
		assertEquals("Yoga mat", controllerObj.getTools().get(0).getName());
	}

	/**
	 * Test for adding device and check if it present
	 */
	@Test
	public void addDevicesTest() {
		Room livingroom = new Room("livingroom", 1);
		List<Device> devices = new ArrayList<>();
		Device computer = new Computer("PC", livingroom, new Consumption(100,
				1, 0, 0, 0, 0));
		devices.add(computer);
		HouseController controllerObj = HouseController.getInstance();
		controllerObj.addDevices(devices);
		assertEquals("PC", controllerObj.getDevices().get(0).getName());
	}

	/**
	 * Test for adding sensor and check if it present
	 */
	@Test
	public void addSensorsTest() {
		Room livingroom = new Room("livingroom", 1);
		List<Sensor> roomSensors = new ArrayList<>();
		roomSensors.add(new Sensor(livingroom));
		HouseController controllerObj = HouseController.getInstance();
		controllerObj.addSensors(roomSensors);

	}

	/**
	 * test for adding
	 */
	@Test
	public void addTest() {
		Room livingroom = new Room("livingroom", 1);
		List<Sensor> roomSensors = new ArrayList<>();
		roomSensors.add(new Sensor(livingroom));
		HouseController controllerObj = HouseController.getInstance();
		controllerObj.addSensors(roomSensors);

	}

}
