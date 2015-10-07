package highscores;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * The HighScores class is responsible for handling the global leaderboard.
 * The leaderboard is a sorted list of scores, starting with the highest score at index 0.
 * Where each score is the name of the player and the score achieved.
 */
public class HighScores {

    private static final HighScores instance = new HighScores();

    private int maxNumberScores;
    private int numberScores;
    private Score[] leaderboard;

    /**
     * Creates an instance of the global leaderboard.
     * With the initial global leaderboard.
     */
    private HighScores(){
        // Initialise the leaderboard
        maxNumberScores = 10;
        leaderboard = new Score[10];

        // Add placeholder values sorted according to score
        for (int i = 0; i < maxNumberScores; i++){
            leaderboard[i] = new Score();
        }

        // Update the current number of scores
        numberScores = 10;
    }

    /**
     * Returns an instance of the global leaderboard.
     * @return the global leaderboard.
     */
    public static HighScores getInstance(){
        return instance;
    }

    /**
     * Returns the highest score recorded in the global leaderboard.
     * @return the highest recorded score.
     */
    public Score getHighestScore(){
        return leaderboard[0];
    }

    /**
     * Returns all the scores recorded in the global leaderboard.
     * @return all the scores recorded.
     */
    public Iterator<Score> getLeaderBoard(){
        return new ArrayList<Score>(Arrays.asList(leaderboard)).iterator();
    }

    /**
     * Returns the current number of scores recorded in the global leaderboard.
     * @return the number of scores recorded.
     */
    public int getNumberScores(){
        return numberScores;
    }

    /**
     * Returns the index of the lowest score recorded.
     * @return the index of the lowest score.
     */
    private int getLastScoreIndex(){
        int lastScoreIndex = getNumberScores() - 1;
        if (lastScoreIndex < 0) {
            lastScoreIndex = 0;
        }
        return lastScoreIndex;
    }

    /**
     * Adds the specified score.
     * If the specified score is greater than the lowest score recorded.
     * @param score the score.
     */
    public void addScore(Score score){
        int lastScoreIndex = getLastScoreIndex();
        // If the last score on the leaderboard is less than the specified score
        if ((leaderboard[lastScoreIndex].compareTo(score)) < 0) {
            // Add the score to the end of the leaderboard
            leaderboard[lastScoreIndex] = score;
            // And sort the leaderboard
            sort();
        }
    }

    /**
     * Sorts the list from highest score to lowest score.
     * Using one pass of a bubble sort, because the leaderboard should already be a sorted list.
     */
    private void sort(){
        // Starting from lowest
        for (int i = getLastScoreIndex(); i > 0; i--) {
            int nextScoreIndex = i - 1;
            // If greater than next
            if ((leaderboard[i].compareTo(leaderboard[nextScoreIndex])) > 0) {
                // Swap positions with next
                swap(i, nextScoreIndex);
            }
        }
    }

    /**
     * Swaps the positions of the scores at the indexes specified.
     * @param score1 the first index.
     * @param score2 the second index.
     */
    private void swap(int score1, int score2){
        Score temp = leaderboard[score2];
        leaderboard[score2] = leaderboard[score1];
        leaderboard[score1] = temp;
    }

}
