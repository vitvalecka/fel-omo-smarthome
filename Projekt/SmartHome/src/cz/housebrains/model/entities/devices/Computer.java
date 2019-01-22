package cz.housebrains.model.entities.devices;

import cz.housebrains.controller.HouseController;
import cz.housebrains.model.entities.Device;
import cz.housebrains.model.entities.Room;
import cz.housebrains.model.entities.details.Consumption;

public class Computer extends Device {

    HouseController houseController = HouseController.getInstance();

    /**
     * Creates new Fridge with given name and consumption in given room.
     * @param name name of the device
     * @param room room for device to spawn in
     * @param consumption consumption of the device
     */
    public Computer(String name, Room room, Consumption consumption) {
        super(name, room, consumption);
    }

    /**
     * Override method for using fridge, as it can be infected with malware.
     * @return boolean value weather the device has been used
     *      true    if device was successfully used
     *      false   otherwise
     */
    @Override
    public boolean use() {
        if (state == State.IDLE) {
            state = State.ACTIVE;
            condition--;
            if (condition < 1) {
                state = State.BROKEN;
                System.out.println(getName() + " broke down.");
                houseController.eventLog.writeToLog(getName() + " broke down.");
                notifyAllObservers("repair device");
            }
            takeContent();
            int magicNumber = getRandomNumber(0, 100);
            if (magicNumber < 2) {
                System.out.println(getName() + " was malware-infected.");
                houseController.eventLog.writeToLog(getName() + " was malware-infected.");
                notifyAllObservers("repair device");
            }
            return true;
        } else {
            return false;
        }
    }
}
