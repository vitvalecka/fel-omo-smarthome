package cz.housebrains.model.entities.devices;

import cz.housebrains.model.entities.Device;
import cz.housebrains.model.entities.Room;
import cz.housebrains.model.entities.details.Consumption;
import cz.housebrains.model.entities.details.Documentation;
import cz.housebrains.utils.Constants;

public class Freezer extends Device {

    private Documentation documentation;

    /**
     * Creates new Freezer with given name and consumption in given room.
     * @param name name of the device
     * @param room room for device to spawn in
     * @param consumption consumption of the device
     */
    public Freezer (String name, Room room, Consumption consumption) {
        super(name, room, consumption);
        setContent(Constants.HAS_CONTENT);
    }

    /**
     * Repairs the freezer and refills it. At the end, device status is set to IDLE.
     * Can be done only if device status is BROKEN.
     * @return boolean value representing if repair was successful
     *      true    device vas repaired and refilled
     *      false   otherwise
     */
    @Override
    public boolean repair() {
        if (state == State.BROKEN) {
            if (documentation == null) {
                documentation = new Documentation(this);
            }
            state = State.IDLE;
            condition = Constants.DEVICE_LIKE_NEW;
            refill();
            return true;
        }
        return false;
    }
}
