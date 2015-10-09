package settings.valuesettings;

import enumerations.GameEntities;

/**
 * Looks at Entiy maxWidthHeight and minWidthHeight for minArea and MaxArea
 * For testing, make sure entity-end values fall in a reasonable range for all entities.
 * I.e no movement speed = 100
 */
public abstract class ValueIncrementSettings {

    private int numberEntities;
    private double averageMinArea;
    private double averageMaxArea;
    private double averageArea;

    public ValueIncrementSettings(){
        initialise();
    }

    private double Area(double width, double height){
        return Math.PI * width * height;
    }
    private void initialise(){
        numberEntities = GameEntities.values().length;
        double minArea = 0;
        double maxArea = 0;
        double sumMinArea = 0;
        double sumMaxArea = 0;
        double sumAverageArea = 0;
        for (GameEntities entity : GameEntities.values()){
            minArea = Area(entity.getMinEntityWidth(), entity.getMinEntityHeight());
            maxArea = Area(entity.getMaxEntityWidth(), entity.getMaxEntityHeight());
            sumMinArea += minArea;
            sumMaxArea += maxArea;
            sumAverageArea += (maxArea + minArea) / 2;
        }
        averageMinArea = sumMinArea / numberEntities;
        averageMaxArea = sumMaxArea / numberEntities;
        averageArea = sumAverageArea / numberEntities;
    }

    public double getAverageMinArea(){
        return averageMinArea;
    }
    public double getAverageMaxArea(){
        return averageMaxArea;
    }
    public double getAverageArea(){
        return averageArea;
    }
}
