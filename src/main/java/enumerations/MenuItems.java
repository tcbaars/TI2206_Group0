package enumerations;

/**
 * The MenuItems enumeration is used as a way to easily reference the possible menu options that a menu can have.
 * The properties are structured in such a way that the menu option can easily be displayed.
 * The available menu options:
 * <ul>
 * <li>{@link #CLASSIC}</li>
 * <li>{@link #INSTRUCTIONS}</li>
 * <li>{@link #HIGH_SCORES}</li>
 * <li>{@link #BACK}</li>
 * <li>{@link #RESUME}</li>
 * <li>{@link #RESTART}</li>
 * <li>{@link #INCREMENT}</li>
 * <li>{@link #SET_VALUE}</li>
 * <li>{@link #DECREMENT}</li>
 * <li>{@link #TITLE_SCREEN}</li>
 * <li>{@link #EXIT}</li>
 * </ul>
 */
public enum MenuItems {
    /**
     * Start new single-player classic game mode.
     */
    CLASSIC("New Classic Game"),
    /**
     * View the instructions screen.
     */
    INSTRUCTIONS("Instructions"),
    /**
     * View the high scores screen.
     */
    HIGH_SCORES("High Scores"),
    /**
     * Go back to the previous screen.
     */
    BACK("Back"),
    /**
     * Resume the current paused game.
     */
    RESUME("Resume Game"),
    /**
     * Restart the current game.
     */
    RESTART("Restart"),
    /**
     * Increment the currently selected value.
     */
    INCREMENT("Increment Current Value"),
    /**
     * Enter currently selected value.
     */
    SET_VALUE("Set Current Value"),
    /**
     * Decrement the currently selected value.
     */
    DECREMENT("Decrement Current Value"),
    /**
     * Go back to the main screen.
     */
    TITLE_SCREEN("Go Back To Title Screen"),
    /**
     * Exit the application.
     */
    EXIT("Exit");

    private String text;

    /**
     * The properties of the menu option.
     * @param text String representation of the menu option.
     */
    private MenuItems(String text){
        this.text = text;
    }

    /**
     * Returns the String representation of the menu option.
     * Which can be used to display the menu option.
     * @return String representation of the menu option.
     */
    public String getText(){
        return text;
    }
}
