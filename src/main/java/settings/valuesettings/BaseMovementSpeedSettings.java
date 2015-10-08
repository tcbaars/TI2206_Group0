package settings.valuesettings;

import settings.ScreenSettings;

/**
 * Base on screen width. ie. the number increments necessary to cross
 */
public class BaseMovementSpeedSettings extends ValueIncrementSettings{

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
        speed = speed * getAverageMinArea();
        return speed;
    }
}
