package games;

import java.util.Iterator;

import entities.Entity;
import states.gamestates.GameState;

/**
 * The Game interface represents the constraints of a generic Game class.
 */
public interface Game {

    /**
     * Restarts the game.
     */
    void restart();

    /**
     * Pauses the game.
     */
    void pause();

    /**
     * Resumes the game.
     */
    void resume();

    /**
     * Whether or not the game is paused.
     * @return <code>true</code> if and only if the game is paused, otherwise <code>false</code>.
     */
    boolean isPaused();

    /**
     * Whether or not the game is over.
     * @return <code>true</code> if and only if the game is over, otherwise <code>false</code>.
     */
    boolean isGameOver();

    /**
     * Performs the operations which are necessary to update the game each tick.
     */
    void update();

    /**
     * Returns the entities handled by the game.
     * @return the in-game entities.
     */
    Iterator<Entity> getEntities();

    /**
     * Returns the current state of the game.
     * @return the current game state.
     */
    GameState getCurrentState();
}
