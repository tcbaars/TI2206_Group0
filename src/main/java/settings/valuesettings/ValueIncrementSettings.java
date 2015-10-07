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
    private double averageScoreScalingFactor;
    private double averageMovementSpeedScalingFactor;

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
        double sumScoreScalingFactor = 0;
        double sumMovementSpeedScalingFactor = 0;
        for (GameEntities entity : GameEntities.values()){
            minArea = Area(entity.getMinEntityWidth(), entity.getMinEntityHeight());
            maxArea = Area(entity.getMaxEntityWidth(), entity.getMaxEntityHeight());
            sumMinArea += minArea;
            sumMaxArea += maxArea;
            sumAverageArea += (maxArea + minArea) / 2;
            sumScoreScalingFactor += entity.getScoreScalingFactor();
            sumMovementSpeedScalingFactor += entity.getMovementSpeedScalingFactor();
        }
        averageMinArea = sumMinArea / numberEntities;
        averageMaxArea = sumMaxArea / numberEntities;
        averageArea = sumAverageArea / numberEntities;
        averageScoreScalingFactor = sumScoreScalingFactor / numberEntities;
        averageMovementSpeedScalingFactor = sumMovementSpeedScalingFactor / numberEntities;
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
    public double getAverageScoreScalingFactor(){
        return averageScoreScalingFactor;
    }
    public double getAverageMovementSpeedScalingFactor(){
        return averageMovementSpeedScalingFactor;
    }
}
