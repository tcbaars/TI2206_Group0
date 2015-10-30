package settings.valuesettings;

import tools.entitytools.AverageEntityValues;

/**
 * The ScoreIncrementSettings class represents the global score increment settings.
 * The base score increment is calculated by dividing the specified desired score
 * by the specified number of increments. This value is then divided by the average entity area,
 * to account for the fact that score increment is proportional to the area of the entity consumed.
 */
public class ScoreIncrementSettings{

    private static final ScoreIncrementSettings instance = new ScoreIncrementSettings();
    private int desiredScore;
    private int numberIncrements;

    private ScoreIncrementSettings(){
        super();
        desiredScore = 1000;
        numberIncrements = 10;
    }

    public static ScoreIncrementSettings getInstance(){
        return instance;
    }

    public double getBaseScoreIncrement(){
        double score = (double)desiredScore / (double)numberIncrements;
        score = score / AverageEntityValues.getInstance().getAverageArea();
        return score;
    }

}
