package cz.housebrains.model.entities;

import cz.housebrains.controller.HouseController;
import cz.housebrains.model.Entity;

import java.util.ArrayList;
import java.util.List;

public class Tool extends Entity {

    /**
     * Condition of the tool in percents - represents in how good/bad condition the tool is.
     *      100 brand new
     *      0   destroyed
     */
    public int condition = 100;

    /**
     * Enum representing state of the device.
     *      BROKEN  tool is broken and needs to be repaired
     *      IDLE    tool is idle and ready to be used
     *      ACTIVE  tool is in use
     */
    public enum State { BROKEN, IDLE, ACTIVE }

    /**
     * State of the device.
     */
    public State state = State.IDLE;

    protected List<Person> observers = new ArrayList<>();
    protected HouseController houseController = HouseController.getInstance();

    /**
     * Creates new tool with given name and in given room.
     * @param name string with name of the tool
     * @param room room, where this tool is
     */
    public Tool (String name, Room room) {
        super(name, room);
    }

    /**
     * Adds observer to the tool.
     * @param observer person observing the tool
     */
    public void addObserver(Person observer) {
        observers.add(observer);
    }

    /**
     * Returns condition of the tool
     * @return double value the condition
     */
    public double getCondition() {
        return condition;
    }

    /**
     * Returns information whether the tool is broken.
     * @return boolean information if the tool is broken
     *      true    if tool is broken
     *      false   otherwise
     */
    public boolean isBroken() {
        return state == State.BROKEN;
    }

    /**
     * Replaces old tool with the new one. Only broken items can be replaced/repaired.
     * @return boolean value if the tool was replaced
     */
    public boolean repair() {
        if (state == State.BROKEN) {
            condition = 100;
            System.out.println(name + " replaced with new one.");
            state = State.IDLE;
            return true;
        }
        return false;
    }

    /**
     * Uses tool that can be used by only 1 person at a time. This method is overwritten for different cases.
     * @return boolean value of whether the tool has been used
     *      true    if tool was used
     *      false   otherwise (tool is already used, tool is broken)
     */
    public boolean use(){
        if (state == State.IDLE) {
            state = State.ACTIVE;
            condition--;
            if (condition < 1) {
                state = State.BROKEN;
                System.out.println(name + " broke down.");
                notifyAllObservers("repair tool");
            }
            return true;
        } else {
            return false;
        }
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

    /**
     * Sets state of item to broken. Method can be called only on idle tool.
     * @return boolean value whether the tool has been destroyed
     */
    public boolean destroy() {
        if (state == State.IDLE) {
            state = State.BROKEN;
            notifyAllObservers("repair tool");
            return true;
        }
        return false;
    }

    /**
     * Cycles through time.
     * Tool can be used only for one unit of time.
     */
    @Override
    public void nextRound(){
        if (!(state == State.BROKEN)) {
            state = State.IDLE;
        }
    }
}
