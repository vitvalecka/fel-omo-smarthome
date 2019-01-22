package cz.housebrains.model.entities.devices;

import cz.housebrains.model.entities.Device;
import cz.housebrains.model.entities.Room;
import cz.housebrains.model.entities.details.Consumption;
import cz.housebrains.utils.Constants;

public class Coffeemaker extends Device {

    /**
     * Creates new Coffeemaker with given name and consumption in given room.
     * @param name name of the device
     * @param room room for device to spawn in
     * @param consumption consumption of the device
     */
    public Coffeemaker(String name, Room room, Consumption consumption) {
        super(name, room, consumption);
        setContent(Constants.HAS_CONTENT);
    }
}
