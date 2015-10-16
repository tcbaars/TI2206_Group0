package tools.entitytools;

import enumerations.GameEntities;

public class AverageEntityValues {

    private static final AverageEntityValues instance = new AverageEntityValues();
    private int numberEntities;
    private double averageMinArea;
    private double averageMaxArea;
    private double averageArea;

    private AverageEntityValues(){
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

    public static AverageEntityValues getInstance(){
        return instance;
    }

    private double Area(double width, double height){
        return Math.PI * width * height;
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
