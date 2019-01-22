package cz.housebrains.model;

import cz.housebrains.utils.Constants;

import java.util.Random;

public class Weather {

    private static final Weather instance = new Weather();

    /**
     * Weather states, values are self descriptive.
     */
    public static enum Status {SUNNY, RAINY, CLOUDY, WINDY, STORM}

    private Status currentWeather = Status.SUNNY;
    private int currentTemperature = Constants.DEFAULT_WEATHER_TEMPERATURE;
    private final Random rand = new Random();


    private Weather() {}

    /**
     * @return returns weather instance
     */
    // Weather as singleton
    public static Weather getInstance(){
        return instance;
    }

    /**
     * @return returns current weather status
     */
    public Status getCurrentWeather() {
        return currentWeather;
    }

    /**
     * @return returns current temperature
     */
    public int getCurrentTemperature() {
        return currentTemperature;
    }

    /**
     * @return boolean information whether it is sunny
     *      true    for sunny weather
     *      false   otherwise
     */
    public boolean isSunny() {
        return currentWeather == Status.SUNNY;
    }

    /**
     * @return boolean information whether it is rainy
     *      true    for rainy weather
     *      false   otherwise
     */
    public boolean isRainy() {
        return currentWeather == Status.RAINY;
    }

    /**
     * @return boolean information whether it is cloudy
     *      true    for cloudy weather
     *      false   otherwise
     */
    public boolean isCloudy() {
        return currentWeather == Status.CLOUDY;
    }

    /**
     * @return boolean information whether it is windy
     *      true    for windy weather
     *      false   otherwise
     */
    public boolean isOvercast() {
        return currentWeather == Status.WINDY;
    }

    /**
     * @return boolean information whether there is a storm outside
     *      true    for raging  storm outside
     *      false   otherwise
     */
    public boolean isStorm() {
        return currentWeather == Status.STORM;
    }


    /**
     *  Updates current weather to new random value and logs this change to standard output.
     */
    public void update() {
        changeWeather();
        changeTemparature();
        System.out.println("It is " + currentWeather + " now, with temperature of " + currentTemperature + " °C");
    }

    private void changeWeather() {
        int magicNumber = getRandomNumber(1, 100);

        if (magicNumber > 75) {

            magicNumber = getRandomNumber(1, 8);

            if (magicNumber <= 3) {
                currentWeather = Status.SUNNY;
            } else if (magicNumber <= 5) {
                currentWeather = Status.WINDY;
            } else if (magicNumber <= 6) {
                currentWeather = Status.CLOUDY;
            } else if (magicNumber <= 7) {
                currentWeather = Status.RAINY;
            } else {
                currentWeather = Status.STORM;
            }
        }
    }

    private void changeTemparature() {
        int magicNumber = getRandomNumber(1, 5);

        switch (currentWeather) {
            case SUNNY:
                if (currentTemperature < Constants.WEATHER_SUNNY_INFLEX_TEMP) {
                    currentTemperature += magicNumber;
                } else {
                    currentTemperature -= magicNumber;
                }
                break;
            case CLOUDY:
                if (currentTemperature < Constants.WEATHER_CLOUDY_INFLEX_TEMP) {
                    currentTemperature += magicNumber;
                } else {
                    currentTemperature -= magicNumber;
                }
                break;
            case WINDY:
                if (currentTemperature < Constants.WEATHER_WINDY_INFLEX_TEMP) {
                    currentTemperature += magicNumber;
                } else {
                    currentTemperature -= magicNumber;
                }
                break;
            case RAINY:
                if (currentTemperature < Constants.WEATHER_RAINY_INFLEX_TEMP) {
                    currentTemperature += magicNumber;
                } else {
                    currentTemperature -= magicNumber;
                }
                break;
            default:
                if (currentTemperature < Constants.WEATHER_STORM_INFLEX_TEMP) {
                    currentTemperature += magicNumber;
                } else {
                    currentTemperature -= magicNumber;
                }
                break;
        }
    }


    private int getRandomNumber(int minimum, int maximum) {
        return rand.nextInt((maximum - minimum) + 1) + minimum;
    }

    private boolean getRandom() {
        return rand.nextBoolean();
    }
}
