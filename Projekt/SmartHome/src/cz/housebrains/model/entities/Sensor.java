package cz.housebrains.model.entities;

import cz.housebrains.controller.HouseController;
import cz.housebrains.model.RoundClock;
import cz.housebrains.model.Weather;
import cz.housebrains.utils.Constants;

public class Sensor implements RoundClock {

    private final Room room;
    private Weather.Status currentWeather = null;
    protected int currentTemperature = 0;
    protected HouseController houseController = HouseController.getInstance();

    /**
     * Creates sensor in room specified in parameter.
     * @param room place to create sensor
     */
    public Sensor(Room room) {
        this.room = room;
    }

    /**
     * @return instance of current room
     */
    public Room getRoom() {
        return room;
    }

    /**
     * Iterates through sensor reactions for changing weather.
     */
    @Override
    public void nextRound() {
        currentWeather = Weather.getInstance().getCurrentWeather();
        currentTemperature = Weather.getInstance().getCurrentTemperature();

        if (currentWeather == Weather.Status.WINDY || currentWeather == Weather.Status.RAINY || currentWeather == Weather.Status.STORM) {
            if (room.areWindowsOpen()) {
                room.closeWindows();
                houseController.eventLog.writeToLog(room.getName()+": windows closed.");
            }
            if (room.areBlindsClosed()) {
                room.openBlinds();
                houseController.eventLog.writeToLog(room.getName()+": blinds raised.");
            }
        } else {
            // i.e. cloudy or sunny
            if ((currentTemperature >= Constants.WEATHER_OPEN_WINDOWS_TEMP) && (!room.areWindowsOpen())) {
                room.openWindows();
                houseController.eventLog.writeToLog(room.getName()+": windows opened.");
            }
            if ((currentTemperature < Constants.WEATHER_OPEN_WINDOWS_TEMP) && (room.areWindowsOpen())) {
                room.closeWindows();
                houseController.eventLog.writeToLog(room.getName()+": windows closed.");
            }
        }
    }
}
