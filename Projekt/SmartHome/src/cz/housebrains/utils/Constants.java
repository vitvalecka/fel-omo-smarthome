package cz.housebrains.utils;

public class Constants {

    //
    // WINDOWS AND BLINDS CONSTANTS

    /**
     * Default value for windows status.
     */
    public static final boolean DEFAULT_WINDOWS_CLOSED = false;

    /**
     * Default value for blinds status.
     */
    public static final boolean DEFAULT_BLINDS_OPENED = false;


    //
    // WEATHER CONSTANTS

    /**
     * Default initial temperature.
     */
    public static final int DEFAULT_WEATHER_TEMPERATURE = 24;

    /**
     * Inflection value of temperature for sunny weather. For lower values, temperature rises, for equal or higher values, temperature goes down.
     */
    public static final int WEATHER_SUNNY_INFLEX_TEMP = 35;

    /**
     * Inflection value of temperature for cloudy weather. For lower values, temperature rises, for equal or higher values, temperature goes down.
     */
    public static final int WEATHER_CLOUDY_INFLEX_TEMP = 20;

    /**
     * Inflection value of temperature for windy weather. For lower values, temperature rises, for equal or higher values, temperature goes down.
     */
    public static final int WEATHER_WINDY_INFLEX_TEMP = 15;

    /**
     * Inflection value of temperature for rainy weather. For lower values, temperature rises, for equal or higher values, temperature goes down.
     */
    public static final int WEATHER_RAINY_INFLEX_TEMP = 10;

    /**
     * Inflection value of temperature for storm. For lower values, temperature rises, for equal or higher values, temperature goes down.
     */
    public static final int WEATHER_STORM_INFLEX_TEMP = 5;

    /**
     * Temperature at which windows are opened and rooms start to went.
     */
    public static final int WEATHER_OPEN_WINDOWS_TEMP = 28;

    /**
     * After how many rounds weather changes.
     */
    public static final int WEATHER_CYCLE = 4;


    //
    // DEVICES

    /**
     * Default value for devices that has content.
     */
    public static final boolean HAS_CONTENT = true;

    /**
     * Default integer value for device with content that is full.
     */
    public static final int DEVICE_FULL = 100;

    /**
     * Default integer value for new or repaired device.
     */
    public static final int DEVICE_LIKE_NEW = 100;


    //
    // PETS

    /**
     * After how many cycles pet needs feeding.
     */
    public static final int PET_FEED_CYCLE = 12;
}
