package cz.housebrains.run;

import cz.housebrains.controller.HouseController;
import cz.housebrains.model.entities.*;
import cz.housebrains.model.entities.devices.*;
import cz.housebrains.model.entities.details.*;

import java.util.ArrayList;
import java.util.List;

public class runConfig1 {

    public static void main(String args[]) {

        //
        // CREATE NEW HOUSE

        HouseController houseController = HouseController.getInstance();

        //
        // LOAD PARAMETERS

        // Rooms

        List<Room> allRooms = new ArrayList<>();

        Room kitchen = new Room("Kitchen", 0);
        Room livingroom = new Room("Livingroom", 0);
        Room bedroom = new Room("Bedroom", 1);
        Room office = new Room("Office", 1);
        Room pantry = new Room("Pantry", 0);
        Room kidsroom = new Room("Kids Room", 1);
        Room terrace = new Room("Terrace", 1);
        Room bathroom = new Room("Bathroom", 1);

        allRooms.add(kitchen);
        allRooms.add(livingroom);
        allRooms.add(bedroom);
        allRooms.add(office);
        allRooms.add(pantry);
        allRooms.add(kidsroom);
        allRooms.add(terrace);
        allRooms.add(bathroom);

        // Add sensors to all rooms

        List<Sensor> allSensors = new ArrayList<>();
        for (Room room : allRooms) {
            allSensors.add(new Sensor(room));
        }

        // People

        List<Person> allPeople = new ArrayList<>();
        Person jon = new Person("Jon", "male", 27, livingroom);
        Person liz = new Person("Liz", "female", 25, livingroom);
        Person dad = new Person("Jon's Dad", "male", 62, terrace);
        Person mum = new Person("Jon's Mum", "female", 61, terrace);
        allPeople.add(jon);
        allPeople.add(liz);
        allPeople.add(dad);
        allPeople.add(mum);

        // Devices

        List<Device> allDevices = new ArrayList<>();

        Device computer = new Computer("PC", office, new Consumption(90, 1, 0, 0, 0, 0));
        Device laptop = new Computer("Laptop", livingroom, new Consumption(50, 1, 0, 0, 0, 0));
        Device freezer = new Freezer("Freezer", kitchen, new Consumption(150, 150, 0, 0, 0, 0));
        Device fridge = new Fridge("Fridge", kitchen, new Consumption(120, 120, 0, 0, 0, 0));
        Device washingmachine = new Washingmachine("Washingmachine", bathroom, new Consumption(150, 2, 200, 0, 0, 0));
        Device hifi = new Radio("Hifi", livingroom, new Consumption(60, 1, 0, 0, 0, 0));
        Device coffeemaker = new Coffeemaker("Coffeemaker", kitchen, new Consumption(100, 1, 10, 0, 0, 0));
        Device tv = new Television("TV", livingroom, new Consumption(80, 6, 0, 0, 0, 0));

        allPeople.forEach(computer::addObserver);
        allPeople.forEach(laptop::addObserver);
        allPeople.forEach(freezer::addObserver);
        allPeople.forEach(fridge::addObserver);
        allPeople.forEach(washingmachine::addObserver);
        allPeople.forEach(hifi::addObserver);
        allPeople.forEach(coffeemaker::addObserver);
        allPeople.forEach(tv::addObserver);

        allDevices.add(computer);
        allDevices.add(laptop);
        allDevices.add(freezer);
        allDevices.add(fridge);
        allDevices.add(washingmachine);
        allDevices.add(hifi);
        allDevices.add(coffeemaker);
        allDevices.add(tv);

        allPeople.forEach((p) -> {
            p.setUsableDevices(allDevices);
        });

        // Tools

        List<Tool> allTools = new ArrayList<>();

        allTools.add(new Tool("Yoga Mat", bedroom));
        allTools.add(new Tool("Bike 1", terrace));
        allTools.add(new Tool("Bike 2", terrace));
        allTools.add(new Tool("Ski", terrace));

        for (Tool tool : allTools) {
            tool.addObserver(jon);
            tool.addObserver(liz);
            tool.addObserver(dad);
        }

        allPeople.forEach((p) -> {
            p.setUsableTools(allTools);
        });

        // Pets

        List<Pet> pets = new ArrayList<>();

        Pet garfield = new Pet("cat", "Garfield", bedroom);
        Pet odie = new Pet("dog", "Odie", livingroom);
        Pet nermal = new Pet("cat", "Nermal", terrace);

        allPeople.forEach(garfield::addOvserver);
        odie.addOvserver(liz);
        odie.addOvserver(jon);
        nermal.addOvserver(mum);
        nermal.addOvserver(dad);

        pets.add(garfield);
        pets.add(odie);
        pets.add(nermal);

        //
        // ADD ALL TO HOUSE

        houseController.addRooms(allRooms);
        houseController.addSensors(allSensors);
        houseController.addPeople(allPeople);
        houseController.addDevices(allDevices);
        houseController.addTools(allTools);
        houseController.addPets(pets);

        //
        // RUN SIMULATION

        houseController.Run();
    }
}
