package settings.valuesettings;

import tools.entitytools.AverageEntityValues;

public class ScaleIncrementSettings{

    private static final ScaleIncrementSettings instance = new ScaleIncrementSettings();
    private int numberIncrements;

    private ScaleIncrementSettings(){
        super();
        numberIncrements = 2000;
    }

    public static ScaleIncrementSettings getInstance(){
        return instance;
    }

    public double getBaseScaleIncrement(){
        double maxArea = AverageEntityValues.getInstance().getAverageMaxArea();
        double minArea = AverageEntityValues.getInstance().getAverageMinArea();
        double scaleIncrement = (maxArea - minArea) / numberIncrements;
        scaleIncrement = scaleIncrement / AverageEntityValues.getInstance().getAverageArea();
        return scaleIncrement;
    }
}
