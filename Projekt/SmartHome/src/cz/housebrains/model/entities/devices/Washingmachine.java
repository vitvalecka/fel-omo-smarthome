package cz.housebrains.model.entities.devices;

import cz.housebrains.model.entities.Device;
import cz.housebrains.model.entities.Room;
import cz.housebrains.model.entities.details.Consumption;

public class Washingmachine extends Device {

    /**
     * Creates new Washing Machine with given name and consumption in given room.
     * @param name name of the device
     * @param room room for device to spawn in
     * @param consumption consumption of the device
     */
    public Washingmachine(String name, Room room, Consumption consumption) {
        super(name, room, consumption);
    }
}
