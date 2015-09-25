package handlers;

import entities.HighScore;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Adriaan on 25-9-2015.
 * This class is doing everything with the HighScores
 */
public class HighScoresHandler {
    private static HighScoresHandler instance = new HighScoresHandler();
    private ArrayList<HighScore> highScores;

    public static HighScoresHandler getInstance() {
        return instance;
    }

    /**
     * Constructor for a new HighScoreHandler
     */
    public HighScoresHandler() {
        highScores = new ArrayList<HighScore>();
        highScores.add(new HighScore("AAA", 0));
    }

    /**
     * Sort all HighScores
     */
    private void sort() {
        Collections.sort(highScores);
    }

    /**
     * Get all the current scores sorted
     * @return sorted HighScore list
     */
    public ArrayList<HighScore> getHighScores() {
        sort();
        return highScores;
    }

    /**
     * Add a new player highscore
     * @param name player name
     * @param score player highscore
     */
    public void addScore(String name, int score) {
        highScores.add(new HighScore(name, score));
    }
}
