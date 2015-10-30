package highscores;

/**
 * The Score class represents a score.
 * Each 'score' is composed of the score achieved,
 * and the player associated with it.
 * The player is identified by a name, which is a 3 character long String.
 */
public class Score implements Comparable<Score>{

    private String name;
    private int score;

    /**
     * Creates a placeholder score.
     * With default values for the name and score.
     */
    public Score(){
        // Default name
        this.name = "AAA";
        // Default score
        this.score = 0;
    }

    /**
     * Creates the specified score.
     * Where the name is a 3 character long String to identify the player.
     * And the score is the score achieved by the player.
     * @param name the player's name.
     * @param score the player's score.
     */
    public Score(String name, int score){
        this.name = name;
        this.score = score;
    }

    /**
     * Returns the 3 character long String to identify the player,
     * which is associated with the score.
     * @return the player's name.
     */
    public String getName(){
        return name;
    }

    /**
     * Returns the score achieved.
     * @return the score.
     */
    public int getScore(){
        return score;
    }

    /**
     * Compares this score to the specified score.
     * @param score the score to be compared to.
     * @return the value 0 if the scores achieved are equal;
     * a value less than 0 if the score achieved is less than that of the score achieved by the player in the specified score;
     * and a value greater than 0 if the score achieved is greater than that of the score achieved by the player in the specified score.
     */
    public int compareTo(Score score){
        if (score !=  null) {
            return Integer.valueOf(getScore()).compareTo(Integer.valueOf(score.getScore()));
        }
        return 1;
    }

    public boolean equals(Object object){
        if (object != null && object instanceof Score) {
            Score score = (Score) object;
            boolean nameEqual = getName().equals(score.getName());
            boolean scoreEqual = compareTo(score) == 0;
            return nameEqual && scoreEqual;
        }
        return false;
    }

    /**
     * Implemented as recommended by the FindBug report.
     * The hashCode should not be used, and will throw a AssertionError when used.
     * @return an arbitrary constant, (42).
     */
    @Override
    public int hashCode() {
        // throw a AssertionError
        assert false : "hashCode not designed";
        // return an arbitrary constant
        return 42;
    }
}
