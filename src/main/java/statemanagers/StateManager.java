package statemanagers;

import java.awt.Graphics2D;

import enumerations.States;
import games.Game;
import keys.Key;
import keys.TypedKey;
import states.ExitScreenState;
import states.GameScreenState;
import states.HighScoresScreenState;
import states.InstructionsScreenState;
import states.State;
import states.TitleScreenState;
import util.Logger;

/**
 * The StateManager class is used to make changing states easier.
 * It is responsible for switching the current state between the main states:
 * Title Screen State, Game Screen State, High Score Screen State, and Exit Screen State.
 * It is also responsible for managing the current state,
 * which involves passing key events to, drawing, and updating the current state.
 */
public class StateManager {

    private Game game;
    private State currentState;

    /**
     * Creates a new state manager
     */
    public StateManager(){
        Logger.info("The state manager has been started.");
        // The initial state
        setCurrentState(States.TITLE_SCREEN);
    }

    /**
     * Sets the current state to the specified state.
     * @param state the desired state.
     */
    public void setCurrentState(States state){
        Logger.info("The state has been changed to: " + state.getDescription() + ".");
        switch (state) {
            case TITLE_SCREEN:
                currentState = new TitleScreenState(this);
                break;
            case GAME_SCREEN:
                currentState = new GameScreenState(this, game);
                break;
            case SCORE_SCREEN:
                currentState = new HighScoresScreenState(this);
                break;
            case INSTRUCTIONS_SCREEN:
                currentState = new InstructionsScreenState(this);
                break;
            case EXIT_SCREEN:
                currentState = new ExitScreenState(this);
                break;
            default:
                break;
        }
    }

    /**
     * Launches a new game.
     * @param game the desired game.
     */
    public void launchNewGame(Game game){
        Logger.info("A new game has been launched.");
        this.game = game;
        game.restart();
        setCurrentState(States.GAME_SCREEN);
    }

    /**
     * Performs the necessary operation to update the current state, each tick.
     */
    public void update(){
        currentState.update();
    }

    /**
     * Draws the current state to the specified screen.
     * @param screen the screen.
     */
    public void drawToScreen(Graphics2D screen){
        currentState.drawToScreen(screen);
    }

    /**
     * Handles the specified key press event.
     * @param key the pressed key.
     */
    public void handleKeyPressed(Key key){
        if (key != null) {
            currentState.handleKeyPressed(key);
        }
    }

    /**
     * Handles the specified key release event.
     * @param key the released key.
     */
    public void handleKeyReleased(Key key){
        if (key != null) {
            currentState.handleKeyReleased(key);
        }
    }

    /**
     * Handles the specified key typed event.
     * @param key the typed key.
     */
    public void handleKeyTyped(TypedKey key){
        if (key != null) {
            currentState.handleKeyTyped(key);
        }
    }
}
