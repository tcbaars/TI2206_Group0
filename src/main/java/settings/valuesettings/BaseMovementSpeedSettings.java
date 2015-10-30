package settings.valuesettings;

import settings.ScreenSettings;
import tools.entitytools.AverageEntityValues;

/**
 * The BaseMovementSpeedSettings class represents the global settings for the base movement speeds of an entity.
 * The base movement speed is the determined by specifying the desired number of steps an entity should take to cross the current width of the screen.
 * This value is then multiplied by the average minimum area, to take into account the fact that the movement speed is inverse proportional to the area of the entity.
 */
public class BaseMovementSpeedSettings{

    private static final BaseMovementSpeedSettings instance = new BaseMovementSpeedSettings();
    private int numberIncrements;

    /**
     * Creates a new global base movement speed settings instance.
     */
    private BaseMovementSpeedSettings(){
        super();
        numberIncrements = 1000;
    }

    /**
     * Returns the global base movement speed settings.
     * @return the base movement speed settings.
     */
    public static BaseMovementSpeedSettings getInstance(){
        return instance;
    }

    /**
     * Returns the base movement speed.
     * @return base movement speed.
     */
    public double getBaseMovementSpeed(){
        double screenWidth = ScreenSettings.getInstance().getWidth();
        double speed = screenWidth / numberIncrements;
        speed = speed * AverageEntityValues.getInstance().getAverageMinArea();
        return speed;
    }
}
