package enumerations;

/**
 * The MenuItems enumeration is used as a way to easily reference the possible menu options that a menu can have.
 * The properties are structured in such a way that the menu option can easily be displayed.
 * The available menu options:
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
 */
public enum MenuItems {
    /**
     * Start new game.
     */
    CLASSIC("New Classic Game"),
    INSTRUCTIONS("Instructions"),
    HIGH_SCORES("High Scores"),
    BACK("Back"),
    RESUME("Resume Game"),
    RESTART("Restart"),
    INCREMENT("Increment Current Value"),
    SET_VALUE("Set Current Value"),
    DECREMENT("Decrement Current Value"),
    TITLE_SCREEN("Go Back To Title Screen"),
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
