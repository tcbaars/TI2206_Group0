package settings.valuesettings;

public class ScaleIncrementSettings extends ValueIncrementSettings{

    private static final ScaleIncrementSettings instance = new ScaleIncrementSettings();
    private int numberIncrements;

    private ScaleIncrementSettings(){
        super();
        numberIncrements = 10000;
    }

    public static ScaleIncrementSettings getInstance(){
        return instance;
    }

    public double getBaseScaleIncrement(){
        double maxArea = getAverageMaxArea();
        double minArea = getAverageMinArea();
        double scaleIncrement = (maxArea - minArea) / numberIncrements;
        scaleIncrement = scaleIncrement / getAverageArea();
        return scaleIncrement;
    }
}
