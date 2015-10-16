package settings.valuesettings;

import settings.ScreenSettings;
import tools.entitytools.AverageEntityValues;

/**
 * Base on screen width. ie. the number increments necessary to cross
 */
public class BaseMovementSpeedSettings{

    private static final BaseMovementSpeedSettings instance = new BaseMovementSpeedSettings();
    private int numberIncrements;

    private BaseMovementSpeedSettings(){
        super();
        numberIncrements = 1000;
    }

    public static BaseMovementSpeedSettings getInstance(){
        return instance;
    }

    public double getBaseMovementSpeed(){
        double screenWidth = ScreenSettings.getInstance().getWidth();
        double speed = screenWidth / numberIncrements;
        speed = speed * AverageEntityValues.getInstance().getAverageMinArea();
        return speed;
    }
}
