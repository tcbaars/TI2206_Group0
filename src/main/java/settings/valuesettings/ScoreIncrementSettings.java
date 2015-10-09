package settings.valuesettings;

public class ScoreIncrementSettings extends ValueIncrementSettings{

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
        score = score / getAverageArea();
        return score;
    }

}
