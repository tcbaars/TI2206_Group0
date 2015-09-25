package entities;

import java.io.Serializable;

/**
 * Created by Adriaan on 25-9-2015.
 * This class is a HighScore object that has a name and a score.
 */
public class HighScore implements Serializable, Comparable<HighScore> {
    private int score;
    private String naam;

    /**
     * Constructor
     * @param naam  Player name
     * @param score Player score
     */
    public HighScore(String naam, int score) {
        this.naam = naam;
        this.score = score;
    }

    /**
     * Get player score
     * @return score
     */
    public int getScore() {
        return score;
    }

    /**
     * Get player name
     * @return name
     */
    public String getNaam() {
        return naam;
    }

    public int compareTo(HighScore score1) {
        return ((Integer)(score1.getScore())).compareTo(getScore());
    }
}
