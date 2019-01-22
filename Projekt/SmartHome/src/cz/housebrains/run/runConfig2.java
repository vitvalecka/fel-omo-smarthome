package cz.housebrains.run;

import cz.housebrains.controller.HouseController;
import cz.housebrains.model.entities.*;
import cz.housebrains.model.entities.devices.*;
import cz.housebrains.model.entities.details.*;

import java.util.ArrayList;
import java.util.List;

public class runConfig2 {

    public static void main(String args[]) {

        //
        // CREATE NEW HOUSE

        HouseController houseController = HouseController.getInstance();

        //
        // LOAD PARAMETERS

        // Rooms

        List<Room> allRooms = new ArrayList<>();

        Room kitchen = new Room("Kitchen", 0);
        Room hall = new Room("Hall", 0);
        Room livingroom = new Room("Livingroom", 0);
        Room garage = new Room("Garage", 0);
        Room bedroom = new Room("Bedroom", 1);
        Room bathroom = new Room("Bathroom", 1);
        Room wednesdaysroom = new Room("Wednesday's Room", 2);
        Room pugsleysroom = new Room("Pugsley's Room", 2);
        Room festersroom = new Room("Fester's Room", 2);
        Room office = new Room("Office", 1);
        Room vault = new Room("Vault", -1);
        Room terrace = new Room("Terrace", 3);
        Room atic = new Room("Atic", 4);

        allRooms.add(kitchen);
        allRooms.add(hall);
        allRooms.add(livingroom);
        allRooms.add(garage);
        allRooms.add(bedroom);
        allRooms.add(bathroom);
        allRooms.add(wednesdaysroom);
        allRooms.add(pugsleysroom);
        allRooms.add(festersroom);
        allRooms.add(office);
        allRooms.add(vault);
        allRooms.add(terrace);
        allRooms.add(atic);

        // Add sensors to all rooms

        List<Sensor> allSensors = new ArrayList<>();
        for (Room room : allRooms) {
            if (room.getFloor() >= 0) {
                allSensors.add(new Sensor(room));
            }
        }

        // People

        List<Person> allPeople = new ArrayList<>();
        Person morticia = new Person("Morticia", "female", 145, bedroom);
        Person gomez = new Person("Gomez", "male", 53, vault);
        Person fester = new Person("Fester", "male", 51, office);
        Person wednesday = new Person("Wednesday", "female", 14, terrace);
        Person pugsley = new Person("Pugsley", "male", 13, terrace);
        Person grandma = new Person("Grandma", "female", 320, atic);
        Person lurch = new Person("Lurch", "male", 76, hall);
        Person thing = new Person("Thing", "male", 32, hall);

        allPeople.add(morticia);
        allPeople.add(gomez);
        allPeople.add(fester);
        allPeople.add(wednesday);
        allPeople.add(pugsley);
        allPeople.add(grandma);
        allPeople.add(lurch);
        allPeople.add(thing);

        // Devices

        List<Device> allDevices = new ArrayList<>();

        Device computer = new Computer("PC", office, new Consumption(90, 1, 0, 0, 0, 0));
        Device laptop = new Computer("Laptop", office, new Consumption(50, 1, 0, 0, 0, 0));
        Device freezer = new Freezer("Freezer", kitchen, new Consumption(150, 150, 0, 0, 0, 0));
        Device freezerForCorpses = new Freezer("Freezer for Corpses", atic, new Consumption(150, 150, 0, 0, 0, 0));
        Device fridge1 = new Fridge("Fridge 1", kitchen, new Consumption(120, 120, 0, 0, 0, 0));
        Device fridge2 = new Fridge("Fridge 2", kitchen, new Consumption(120, 120, 0, 0, 0, 0));
        Device fridge3 = new Fridge("Fridge 3", atic, new Consumption(120, 120, 0, 0, 0, 0));
        Device washingmachine = new Washingmachine("Washingmachine", bathroom, new Consumption(150, 2, 200, 0, 0, 0));
        Device gramophone1 = new Radio("Gramophone 1", office, new Consumption(30, 1, 0, 0, 0, 0));
        Device gramophone2 = new Radio("Gramophone 2", bedroom, new Consumption(30, 1, 0, 0, 0, 0));
        Device gramophone3 = new Radio("Gramophone 3", vault, new Consumption(30, 1, 0, 0, 0, 0));
        Device gramophone4 = new Radio("Gramophone 4", livingroom, new Consumption(30, 1, 0, 0, 0, 0));
        Device gramophone5 = new Radio("Gramophone 5", hall, new Consumption(30, 1, 0, 0, 0, 0));
        Device cassetteplayer1 = new Radio("Cassette Player 1", wednesdaysroom, new Consumption(20, 1, 0, 0, 0, 0));
        Device cassetteplayer2 = new Radio("Cassette Player 2", pugsleysroom, new Consumption(20, 1, 0, 0, 0, 0));
        Device cassetteplayer3 = new Radio("Cassette Player 3", festersroom, new Consumption(20, 1, 0, 0, 0, 0));
        Device coffeemaker = new Coffeemaker("Coffeemaker", kitchen, new Consumption(100, 1, 10, 0, 0, 0));
        Device tv = new Television("TV", livingroom, new Consumption(80, 6, 0, 0, 0, 0));
        Device console = new GamingConsole("PlaySystem 4", livingroom, new Consumption(60, 1,0,0,0,0));
        Device stove1 = new Stove("Stove 1", kitchen, new Consumption(3,0,0,0,30,1));
        Device stove2 = new Stove("Stove 2", atic, new Consumption(3,0,0,0,30,1));

        allPeople.forEach(computer::addObserver);
        allPeople.forEach(laptop::addObserver);
        allPeople.forEach(freezer::addObserver);
        allPeople.forEach(freezerForCorpses::addObserver);
        allPeople.forEach(fridge1::addObserver);
        allPeople.forEach(fridge2::addObserver);
        allPeople.forEach(fridge3::addObserver);
        allPeople.forEach(washingmachine::addObserver);
        allPeople.forEach(gramophone1::addObserver);
        allPeople.forEach(gramophone2::addObserver);
        allPeople.forEach(gramophone3::addObserver);
        allPeople.forEach(gramophone4::addObserver);
        allPeople.forEach(gramophone5::addObserver);
        allPeople.forEach(cassetteplayer1::addObserver);
        allPeople.forEach(cassetteplayer2::addObserver);
        allPeople.forEach(cassetteplayer3::addObserver);
        allPeople.forEach(coffeemaker::addObserver);
        allPeople.forEach(tv::addObserver);
        allPeople.forEach(console::addObserver);
        allPeople.forEach(stove1::addObserver);
        allPeople.forEach(stove2::addObserver);

        allDevices.add(computer);
        allDevices.add(laptop);
        allDevices.add(freezer);
        allDevices.add(freezerForCorpses);
        allDevices.add(fridge1);
        allDevices.add(fridge2);
        allDevices.add(fridge3);
        allDevices.add(washingmachine);
        allDevices.add(gramophone1);
        allDevices.add(gramophone2);
        allDevices.add(gramophone3);
        allDevices.add(gramophone4);
        allDevices.add(gramophone5);
        allDevices.add(cassetteplayer1);
        allDevices.add(cassetteplayer2);
        allDevices.add(cassetteplayer3);
        allDevices.add(coffeemaker);
        allDevices.add(tv);
        allDevices.add(console);
        allDevices.add(stove1);
        allDevices.add(stove2);

        allPeople.forEach((p) -> {
            p.setUsableDevices(allDevices);
        });

        // Tools

        List<Tool> allTools = new ArrayList<>();

        allTools.add(new Tool("Hammer", garage));
        allTools.add(new Tool("Dynamite", garage));
        allTools.add(new Tool("Bike 1", terrace));
        allTools.add(new Tool("Bike 2", terrace));
        allTools.add(new Tool("Ski", terrace));

        allPeople.forEach((p) -> {
            allTools.forEach((t) -> {
                t.addObserver(p);
            });
            p.setUsableTools(allTools);
        });

        // Pets

        List<Pet> pets = new ArrayList<>();

        Pet cat = new Pet("cat", "Meow", hall);
        Pet dog = new Pet("dog", "Cerberus", vault);
        Pet crow = new Pet("crow", "Harpy", atic);

        allPeople.forEach(cat::addOvserver);
        dog.addOvserver(gomez);
        dog.addOvserver(fester);
        dog.addOvserver(morticia);
        allPeople.forEach(crow::addOvserver);

        pets.add(cat);
        pets.add(dog);
        pets.add(crow);

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