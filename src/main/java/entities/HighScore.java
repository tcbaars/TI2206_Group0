package entities;

import java.io.Serializable;

/**
 * This class is a HighScore object that has a name and a score.
 */
public class HighScore implements Serializable, Comparable<HighScore> {
    private int score;
    private String name;

    /**
     * Constructor
     * @param name  Player name
     * @param score Player score
     */
    public HighScore(String name, int score) {
        this.name = name;
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
    public String getName() {
        return name;
    }

    public int compareTo(HighScore score1) {
        return ((Integer)(score1.getScore())).compareTo(getScore());
    }
}
