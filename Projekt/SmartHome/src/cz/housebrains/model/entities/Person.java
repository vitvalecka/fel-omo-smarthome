package cz.housebrains.model.entities;

import cz.housebrains.controller.HouseController;
import cz.housebrains.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Person extends Entity {

    private final String sex;
    private int age;
    private List<Event> toDo = new ArrayList<>();
    protected List<Device> usableDevices  = new ArrayList<>();
    protected List<Tool> usableTools = new ArrayList<>();
    protected HouseController houseController = HouseController.getInstance();

    /**
     * Creates person with given name, sex and age in given room.
     * @param name  string name of person
     * @param sex   string name of person's sex
     * @param age   integer value of person's age
     * @param room  room for the person to spawn in
     */
    public Person(String name, String sex, int age, Room room) {
        super(name, room);
        this.sex = sex;
        this.age = age;
    }


    /**
     * Returns string value of person's name.
     * @return string value of person's name
     */
    public String getName() {
        return super.getName();
    }

    /**
     * Returns string value of person's sex.
     * @return string value of person's sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * Returns integer value of person's age.
     * @return integer value of person's age
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets which devices this person can use.
     * @param usableDevices list of devices person can use
     */
    public void setUsableDevices(List<Device> usableDevices) {
        this.usableDevices = usableDevices;
    }

    /**
     * Sets which tools this person can use.
     * @param usableTools list of tools person can use
     */
    public void setUsableTools(List<Tool> usableTools) {
        this.usableTools = usableTools;
    }

    /**
     * Sets room for the person to spawn in or move to. If person currently is in any room, it is first removed from
     * that room and then placed in the new one.
     * @param room room for the person to spawn in or move to
     */
    //@Override
    public void setCurrentRoom(Room room) {
        if (currentRoom != null) {
            currentRoom.removePerson();
        }
        room.addPerson();
        currentRoom = room;
    }

    /**
     *  Increases age of the person by one year.
     */
    public void haveBirthday() {
        age += 1;
    }

    /**
     * Calculates changes between clock ticks, moves time to the next round.
     */
    @Override
    public void nextRound(){
        if (!toDo.isEmpty()) {
            Event eventToDo = toDo.get(0);

            switch (eventToDo.getWhatToDo()) {
                case "repair device":
                    setCurrentRoom(eventToDo.device.getCurrentRoom());
                    if (eventToDo.device.repair()) {
                        System.out.println(name + " repaired " + eventToDo.device.getName() + " in "+ eventToDo.device.getCurrentRoom().getName());
                        houseController.eventLog.writeToLog(name + " repaired " + eventToDo.device.getName() + " in "+ eventToDo.device.getCurrentRoom().getName());
                        houseController.activityUsageLog.writeToLog(name + " repaired " + eventToDo.device.getName() + " in "+ eventToDo.device.getCurrentRoom().getName());
                    }
                    break;
                case "repair tool":
                    setCurrentRoom(eventToDo.tool.getCurrentRoom());
                    if (eventToDo.tool.repair()) {
                        System.out.println(name + " repaired " + eventToDo.tool.getName() + " in " + eventToDo.tool.getCurrentRoom().getName());
                        houseController.eventLog.writeToLog(name + " repaired " + eventToDo.tool.getName() + " in " + eventToDo.tool.getCurrentRoom().getName());
                        houseController.activityUsageLog.writeToLog(name + " repaired " + eventToDo.tool.getName() + " in " + eventToDo.tool.getCurrentRoom().getName());
                    }
                    break;
                case "feed pet":
                    setCurrentRoom(eventToDo.pet.getCurrentRoom());
                    if (eventToDo.pet.feed()) {
                        System.out.println(name + " fed " + eventToDo.pet.getName() + " in " + eventToDo.pet.getCurrentRoom().getName());
                        houseController.eventLog.writeToLog(name + " fed " + eventToDo.pet.getName() + " in " + eventToDo.pet.getCurrentRoom().getName());
                        houseController.activityUsageLog.writeToLog(name + " fed " + eventToDo.pet.getName() + " in " + eventToDo.pet.getCurrentRoom().getName());
                    }
                    break;
                case "out of":
                    setCurrentRoom(eventToDo.device.getCurrentRoom());
                    eventToDo.device.refill();
                    System.out.println(name+" refilled " + eventToDo.device.getName() + " in " + eventToDo.device.getCurrentRoom().getName());
                    houseController.eventLog.writeToLog(name + " refilled " + eventToDo.device.getName() + " in " + eventToDo.device.getCurrentRoom().getName());
                    houseController.activityUsageLog.writeToLog(name + " refilled " + eventToDo.device.getName() + " in " + eventToDo.device.getCurrentRoom().getName());
                    break;
                default:
            }
            toDo.remove(0);
        } else {
            if (!doSomethingRandom()) {
                System.out.println(name + " has nothing to do. " + name + " is in " + currentRoom.getName());
                houseController.eventLog.writeToLog(name + " has nothing to do. " + name + " is in " + currentRoom.getName());
                houseController.activityUsageLog.writeToLog(name + " has nothing to do. " + name + " is in " + currentRoom.getName());
            }
        }
    }

    /**
     * Calculates random behaviour of the person in case, it has nothing to do.
     * @return boolean value if person done something
     *      true    if random activity has been done
     *      false   otherwise
     */
    public boolean doSomethingRandom(){
        int magicNumber = getRandomNumber(1, 100);

        if (magicNumber > 50) {
            Collections.shuffle(usableDevices);

            for (Device device : usableDevices) {
                if (device.use()) {
                    setCurrentRoom(device.getCurrentRoom());
                    System.out.println(name + " used " + device.getName() + " in " + currentRoom.toString());
                    houseController.activityUsageLog.writeToLog(name + " used " + device.getName() + " in " + currentRoom.toString());
                    return true;
                }
            }

            System.out.println(name + ": No usable devices. I'll look for some tool.");
            Collections.shuffle(usableTools);

            for (Tool tool : usableTools) {
                if (tool.use()) {
                    setCurrentRoom(tool.getCurrentRoom());
                    System.out.println(name + " used " + tool.getName() + " in " + currentRoom.toString());
                    houseController.activityUsageLog.writeToLog(name + " used " + tool.getName() + " in " + currentRoom.toString());
                    return true;
                }
            }

            System.out.println(name + ": No usable tools or devices found.");

        } else {

            Collections.shuffle(usableTools);
            for (Tool tool : usableTools) {
                if (tool.use()) {
                    setCurrentRoom(tool.getCurrentRoom());
                    System.out.println(name + " used " + tool.getName() + " in " + currentRoom.toString());
                    houseController.activityUsageLog.writeToLog(name + " used " + tool.getName() + " in " + currentRoom.toString());
                    return true;
                }
            }

            System.out.println(name + ": No usable tools. I'll look for some devices.");
            Collections.shuffle(usableDevices);

            for (Device device : usableDevices) {
                if (device.use()) {
                    setCurrentRoom(device.getCurrentRoom());
                    System.out.println(name + " used " + device.getName() + " in " + currentRoom.toString());
                    houseController.activityUsageLog.writeToLog(name + " used " + device.getName() + " in " + currentRoom.toString());
                    return true;
                }
            }

            System.out.println(name + ": No usable tools or devices found.");
        }
        return false;
    }

    /**
     * Updates what a person should do with device.
     * @param entity    device the person should work with
     * @param whatToDo  string description that should be done
     */
    public void update(Device entity, String whatToDo) {
        toDo.add(new Event(entity, whatToDo));
    }

    /**
     * Updates what a person should do with tool.
     * @param entity    tool the person should work with
     * @param whatToDo  string description that should be done
     */
    public void update(Tool entity, String whatToDo) {
        toDo.add(new Event(entity, whatToDo));
    }

    /**
     * Updates what a person should do with pet.
     * @param entity    pet the person should work with
     * @param whatToDo  string description that should be done
     */
    public void update(Pet entity, String whatToDo) {
        toDo.add(new Event(entity, whatToDo));
    }
}
