package cz.housebrains.model.entities;

import cz.housebrains.utils.Constants;

public class Room {
    /**
     *  Enum of states in which room can be.
     */
    public static enum Status { OPEN, CLOSE } // UP, DOWN

    private final int floor;
    private final String name;

    private boolean windowsOpen = Constants.DEFAULT_WINDOWS_CLOSED;
    private boolean blindsClosed = Constants.DEFAULT_BLINDS_OPENED;

    private int numberOfDevices = 0;
    private int numberOfPeople = 0;

    /**
     * Creates new room.
     * @param name name of the room
     * @param floor floor on which this room is
     */
    public Room(String name, int floor) {
        this.floor = floor;
        this.name = name;
    }

    /**
     * @return returns floor on which this room is
     */
    public int getFloor() {
        return floor;
    }

    /**
     * @return returns name of the room
     */
    public String getName() {
        return name;
    }

    /**
     * @return returns information whether windows are opened
     *      true if windows are opened
     *      false otherwise
     */
    public boolean areWindowsOpen() {
        return windowsOpen;
    }

    /**
     * @return returns information whether blinds are closed
     *      true if blinds are closed
     *      false otherwise
     */
    public boolean areBlindsClosed() {
        return blindsClosed;
    }

    /**
     * @return returns number of devices in room
     */
    public int getNumberOfDevices() {
        return numberOfDevices;
    }

    /**
     * @return returns number of people in room
     */
    public int getNumberOfPeople() {
        return numberOfPeople;
    }


    /**
     *  Closes windows in the room.
     */
    public void closeWindows() {
        windowsOpen = false;
    }

    /**
     *  Opens windows in the room.
     */
    public void openWindows() {
        windowsOpen = true;
    }

    /**
     *  Opens blinds on the windows.
     */
    public void openBlinds() {
        blindsClosed = false;
    }

    /**
     *  Closes blinds on the windows.
     */
    public void closeBlinds() {
        blindsClosed = true;
    }


    /**
     * Increases number of devices in the room.
     */
    public void addDevice() {
        numberOfDevices++;
    }

    /**
     * Reduces number of devices in the room by one.
     */
    public void removeDevice() {
        if (numberOfDevices > 0) {
            numberOfDevices--;
        }
    }

    /**
     * Increases number of people in the room.
     */
    public void addPerson() {
        numberOfPeople++;
    }

    /**
     * Reduces number of people in the room by one.
     */
    public void removePerson() {
        if (numberOfPeople > 0) {
            numberOfPeople--;
        }
    }

    /**
     * @return returns string containing basic info about the room including name and floor
     */
    @Override
    public String toString() {
        return "room \"" + name + "\" on floor no. " + floor;
    }
}
