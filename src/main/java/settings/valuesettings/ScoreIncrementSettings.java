package settings.valuesettings;

import tools.entitytools.AverageEntityValues;

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
