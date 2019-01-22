package cz.housebrains.model.entities.devices;

import cz.housebrains.controller.HouseController;
import cz.housebrains.model.entities.Device;
import cz.housebrains.model.entities.Room;
import cz.housebrains.model.entities.Tool;
import cz.housebrains.model.entities.details.Consumption;

public class GamingConsole extends Device {

    HouseController houseController = HouseController.getInstance();

    /**
     * Creates new Gaming Console with given name and consumption in given room.
     * @param name name of the device
     * @param room room for device to spawn in
     * @param consumption consumption of the device
     */
    public GamingConsole(String name, Room room, Consumption consumption) {
        super(name, room, consumption);
    }

    /**
     * Override method for using Gaming Console, as it can be used by multiple people at the same time.
     * @return boolean value weather the device has been used
     *      true    if device was successfully used, or device is already used by different person
     *      false   otherwise
     */
    @Override
    public boolean use(){
        switch (state){
            case ACTIVE:
                return true;
            case IDLE:
                state = Tool.State.ACTIVE;
                condition--;
                if (condition < 1) {
                    state = Tool.State.BROKEN;
                    System.out.println(getName() + " broke down.");
                    houseController.eventLog.writeToLog(getName() + " broke down.");
                    notifyAllObservers("repair device");
                }
                return true;
            default:
                return false;
        }
    }
}
