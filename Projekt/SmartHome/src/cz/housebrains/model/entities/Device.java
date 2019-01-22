package cz.housebrains.model.entities;

import cz.housebrains.controller.HouseController;
import cz.housebrains.model.entities.details.Consumption;
import cz.housebrains.model.entities.details.Documentation;
import cz.housebrains.utils.Constants;

public class Device extends Tool{

    private final Consumption consumption;
    private Documentation documentation;
    protected int content = -100;

    /**
     * Creates device with given name and consumption in given room.
     * @param name string name of the device
     * @param room room for the device to spawn in
     * @param consumption consumption of the device
     */
    public Device(String name, Room room, Consumption consumption) {
        super(name, room);
        this.consumption = consumption;
    }

    /**
     * Returns consumption of the device.
     * @return object representing consumption of the device
     */
    public Consumption getConsumption() {
        return consumption;
    }

    /**
     * Sets if there is any content of the device.
     * @param content boolean value representing if device has any content
     *                true  if device has content, sets amount of content to 100
     *                false otherwise
     */
    public void setContent(boolean content){
        if (content) {
            this.content = 100;
        }
    }

    /**
     * Repairs the device. Works only for broken device.
     * @return boolean value representing whether device was repaired
     *      true    device was repaired
     *      false   otherwise
     */
    @Override
    public boolean repair() {
        if (state == State.BROKEN) {
            if (documentation == null) {
                documentation = new Documentation(this);
            }
            state = State.IDLE;
            condition = 100;
            return true;
        }
        return false;
    }

    /**
     * Moves time for the device to the next round.
     * Device status is set to IDLE, as anybody can use a device only for one round.
     */
    @Override
    public void nextRound(){
        if (!(state == State.BROKEN)) {
            consumption.update(state);
            state = State.IDLE;
        }
    }

    /**
     * Uses the device. Device can be used only when IDLE and not BROKEN.
     * Device can be used only by one person at a time.
     * @return boolean value representing whether the device has been used
     *      true    device was used, i.e. was IDLE and isn't BROKEN
     *      false   otherwise
     */
    @Override
    public boolean use() {
        if (state == State.IDLE) {
            state = State.ACTIVE;
            condition--;
            if (condition < 1) {
                state = State.BROKEN;
                System.out.println(name + " broke down.");
                notifyAllObservers("repair device");
            }
            takeContent();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Removes content from the device. Device becomes empty.
     */
    public void takeContent() {
        if (content > 0) {
            content--;
            if (content < 1) {
                System.out.println("Out of content in " + getName());
                houseController.eventLog.writeToLog("Out of content in " + getName());
                notifyAllObservers("out of");
            }
        }
    }

    /**
     * Refills content of the device.
     */
    public void refill() {
        content = Constants.DEVICE_FULL;
    }

    /**
     * Notifies all observers of the tool.
     * @param whatToDo string message what to do
     */
    public void notifyAllObservers(String whatToDo) {
        if (!observers.isEmpty()) {
            String strObsList = "";
            for (Person observer : observers) {
                observer.update(this, whatToDo);
                strObsList += observer.getName() + ", ";
            }
            strObsList = strObsList.substring(0, strObsList.length()-2);
            System.out.println(name + " broke down. Notify sent to: " + strObsList);
            houseController.eventLog.writeToLog(name + " broke down. Notify sent to: " + strObsList);
        } else {
            System.out.println(name + " has no observers.");
        }
    }

}
