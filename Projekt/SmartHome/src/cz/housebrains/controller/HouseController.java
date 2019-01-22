package cz.housebrains.controller;

import cz.housebrains.logging.Log;
import cz.housebrains.model.RoundClock;
import cz.housebrains.model.Weather;
import cz.housebrains.model.entities.*;
import cz.housebrains.utils.Constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HouseController {

    private static final HouseController instance = new HouseController();

    // Rounds
    private final int numberOfRounds = 200;

    // Interface for time
    private final List<RoundClock> entities = new ArrayList<>();

    // entities in house
    private final List<Sensor> sensors = new ArrayList<>();
    private final List<Room> rooms = new ArrayList<>();
    private final List<Person> people = new ArrayList<>();
    private final List<Pet> pets = new ArrayList<>();
    private final List<Device> devices = new ArrayList<>();
    private final List<Tool> tools = new ArrayList<>();

    // Logging
    /**
     * Log file for house configuration info
     */
    public final Log configLog = new Log("../HouseConfigurationReport.log");

    /**
     * Log file for events.
     */
    public final Log eventLog = new Log("../EventReport.log");

    /**
     * Log file for energy and resources consumption.
     */
    public final Log consumptionLog = new Log("../ConsumptionReport.log");

    /**
     * Log file for activity and usage information
     */
    public final Log activityUsageLog = new Log("../ActivityAndUsageReport.log");


    // Singleton Pattern => private constructor
    private HouseController() {}

    /**
     * Returns instance of house controller.
     * @return current house controller
     */
    public static HouseController getInstance() {
        return instance;
    }


    /**
     * Run method for running the house simulation.
     */
    public void Run() {
        System.out.println("----------------------------------");
        System.out.println("Starting simulation of Smart House");
        System.out.println("----------------------------------");
        StartLogs();

        for (int round = 0; round < numberOfRounds; round++) {
            System.out.println("\nRound No." + round + " started");
            writeNewRound(round);

            // Change weather
            if (round % Constants.WEATHER_CYCLE == 0) {
                Weather.getInstance().update();
                // Update sensors
                for (Sensor sensor : sensors) {
                    sensor.nextRound();
                }
            }

            // Update devices
            for (Device device : devices) {
                device.nextRound();
            }

            // Update tools
            for (Tool tool : tools) {
                tool.nextRound();
            }

            // Update people
            Collections.shuffle(people);
            for (Person person : people) {
                person.nextRound();
            }

            // Update pets
            Collections.shuffle(pets);
            for (Pet pet : pets) {
                pet.nextRound();
            }
        }

        // Close log files
        configLog.closeLog();
        eventLog.closeLog();
        ConsumptionReport();
        consumptionLog.closeLog();
    }


    //
    // ADDING THINGS

    /**
     * Adds given room to the house.
     * @param room room to add to house
     */
    public void addRoom(Room room) {
        rooms.add(room);
        configLog.writeToLog("Added new room called " + room.getName() + " to the floor no. " + room.getFloor());
    }

    /**
     * Adds multiple rooms to the house.
     * @param rooms list of rooms to be added
     */
    public void addRooms(List<Room> rooms) {
        rooms.forEach(this::addRoom);
    }

    /**
     * Adds given person to the house.
     * @param person person to be added to the house
     */
    public void addPerson(Person person) {
        people.add(person);
        entities.add(person);
        configLog.writeToLog("Added new person called " + person.getName() + " (" + person.getSex() + ") to the room " + person.getCurrentRoom().getName());
    }

    /**
     * Adds multiple people to the house
     * @param people list of people to be added to the house
     */
    public void addPeople(List<Person> people) {
        people.forEach(this::addPerson);
    }

    /**
     * Adds given pet to the house.
     * @param pet pet to be added to the house
     */
    public void addPet(Pet pet) {
        pets.add(pet);
        entities.add(pet);
        configLog.writeToLog("Add new animal with name " + pet.getName() + "(" + pet.getAnimal() + ") to the room " + pet.getCurrentRoom().getName());
    }

    /**
     * Adds multiple pets to the house.
     * @param pets list of pets to be added to the house
     */
    public void addPets(List<Pet> pets) {
        pets.forEach(this::addPet);
    }

    /**
     * Adds given tool to the house.
     * @param tool tool to be added to the house
     */
    public void addTool(Tool tool) {
        tools.add(tool);
        configLog.writeToLog("Add new tool called " + tool.getName() + " to the room " + tool.getCurrentRoom().getName());
    }

    /**
     * Adds multiple tools to the house.
     * @param tools list of tools to be added to the house
     */
    public void addTools(List<Tool> tools) {
        tools.forEach(this::addTool);
    }

    /**
     * Adds given device to the house.
     * @param device device to be added to the house
     */
    public void addDevice(Device device) {
        devices.add(device);
        entities.add(device);
        configLog.writeToLog("Add new device " + device.getName() + " to the room " + device.getCurrentRoom().getName());
    }

    /**
     * Adds multiple devices to the house.
     * @param devices list of devices to be added
     */
    public void addDevices(List<Device> devices) {
        devices.forEach(this::addDevice);
    }

    /**
     * Adds sensor to the house.
     * @param sensor sensor to be added to the house
     */
    public void addSensor(Sensor sensor) {
        sensors.add(sensor);
        configLog.writeToLog("Add new room sensor to the " + sensor.getRoom().getName());
    }

    /**
     * Adds multiple sensors to the house.
     * @param sensors
     */
    public void addSensors(List<Sensor> sensors) {
        sensors.forEach(this::addSensor);
    }

    /**
     * Returns list of all rooms that are currently in the house.
     * @return list of all rooms
     */
    public List<Room> getRooms() {
        return rooms;
    }

    /**
     * Returns list of all people that are currently in the house.
     * @return list of all people
     */
    public List<Person> getPeople() {
        return people;
    }

    /**
     * Returns list of all pets that are currently in the house.
     * @return list of all pets
     */
    public List<Pet> getPets() {
        return pets;
    }

    /**
     * Returns list of all devices that are currently in the house.
     * @return list of all devices
     */
    public List<Device> getDevices() {
        return devices;
    }

    /**
     * Returns list of all tools that are currently in the house.
     * @return list of all tools
     */
    public List<Tool> getTools() {
        return tools;
    }

    //
    // GENERATING REPORTS

    private void ConsumptionReport(){
        for (Device device : devices) {
            consumptionLog.writeToLog(device.getName());
            consumptionLog.writeToLog("electricity consumption: "+device.getConsumption().getElectricityConsumption());
            consumptionLog.writeToLog("gas consumption: "+device.getConsumption().getGasConsumption());
            consumptionLog.writeToLog("water consumption: "+device.getConsumption().getWaterConsumption());
            consumptionLog.writeToLog("\n\n");
        }
    }

    private void StartLogs(){
        eventLog.writeToLog("EVENT REPORT");
        eventLog.writeToLog(" ");
        consumptionLog.writeToLog("CONSUMPTION REPORT");
        consumptionLog.writeToLog(" ");
        activityUsageLog.writeToLog("ACTIVITY AND USAGE REPORT");
        activityUsageLog.writeToLog(" ");
    }

    private void writeNewRound(int round) {
        eventLog.writeToLog(" ");
        eventLog.writeToLog("Round No." + round + " started");
        activityUsageLog.writeToLog(" ");
        activityUsageLog.writeToLog("Round No." + round + " started");
    }
}
