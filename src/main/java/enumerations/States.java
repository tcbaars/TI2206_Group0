package enumerations;

/**
 * The GameStates enumeration is used as a way to easily reference the possible states that the game can be in.
 * The properties are structured in such a way that state changes can easily be logged.
 */
public enum States {
    TITLE_SCREEN("Title Screen"),
    GAME_SCREEN("Game Screen"),
    SCORE_SCREEN("High Scores Screen"),
    INSTRUCTIONS_SCREEN("Instructions Screen"),
    EXIT_SCREEN("Exit Screen");

    private String description;

    /**
     * The properties of a possible state of the game.
     * @param description a brief description of the state.
     */
    private States(String description){
        this.description = description;
    }

    /**
     * Returns a brief description which identifies what the state is.
     * @return a brief description of the state.
     */
    public String getDescription(){
        return description;
    }
}
