package tools.entitytools;

import enumerations.GameEntities;

/**
 * The AverageEntityValues class represents the global average entity values.
 * It uses the specified values in the GameEntities to calculate:
 * the average minimum area,
 * the average area,
 * the average maximum area.
 */
public class AverageEntityValues {

    private static final AverageEntityValues instance = new AverageEntityValues();
    private int numberEntities;
    private double averageMinArea;
    private double averageMaxArea;
    private double averageArea;

    /**
     * Creates a new global average entity values instance.
     */
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

    /**
     * Returns the global average entity values instance.
     * @return the global average entity values.
     */
    public static AverageEntityValues getInstance(){
        return instance;
    }

    /**
     * Calculates an approximation of the area of the entity.
     * Using the function to calculate the area of an ellipse,
     * as all the entities have ellipse bounding boxes.
     * @param width the width of the entity.
     * @param height the height of the entity.
     * @return the area of the entity.
     */
    private double Area(double width, double height){
        return Math.PI * width * height;
    }

    /**
     * Returns an approximation of the average minimum area of the entities.
     * @return the average minimum area.
     */
    public double getAverageMinArea(){
        return averageMinArea;
    }

    /**
     * Returns an approximation of the average maximum area of the entities.
     * @return the average maximum area.
     */
    public double getAverageMaxArea(){
        return averageMaxArea;
    }

    /**
     * Returns an approximation of the average area of the entities.
     * @return the average area.
     */
    public double getAverageArea(){
        return averageArea;
    }
}
