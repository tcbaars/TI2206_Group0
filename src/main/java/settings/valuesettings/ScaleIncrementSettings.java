package settings.valuesettings;

import tools.entitytools.AverageEntityValues;

/**
 * The ScaleIncrementSettings class represents the global settings for the base scale increment settings.
 * The base scale increment in calculate by dividing the difference between the average maximum size and the average minimum size,
 * by the specified number of increments. This gives an approximation for the scale increment which would result in the maximum size being reached,
 * from the minimum size, after the specified number increments. This value is then divided by the average size to account for the fact that each increment is
 * proportion to the area of the entity consumed.
 */
public class ScaleIncrementSettings{

    private static final ScaleIncrementSettings instance = new ScaleIncrementSettings();
    private int numberIncrements;

    /**
     * Creates a new global scale increment settings instance.
     */
    private ScaleIncrementSettings(){
        super();
        numberIncrements = 2000;
    }

    /**
     * Returns the global scale increment settings instance.
     * @return the global scale increment settings.
     */
    public static ScaleIncrementSettings getInstance(){
        return instance;
    }

    /**
     * Returns the base scale increment.
     * @return the base scale increment.
     */
    public double getBaseScaleIncrement(){
        double maxArea = AverageEntityValues.getInstance().getAverageMaxArea();
        double minArea = AverageEntityValues.getInstance().getAverageMinArea();
        double scaleIncrement = (maxArea - minArea) / numberIncrements;
        scaleIncrement = scaleIncrement / AverageEntityValues.getInstance().getAverageArea();
        return scaleIncrement;
    }
}
