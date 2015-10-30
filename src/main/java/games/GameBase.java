package games;

import states.gamestates.GameState;
import util.Logger;

/**
 * The GameBase class represents a general implementation of the Game interface.
 */
public abstract class GameBase implements Game{

    private boolean paused;
    private boolean gameOver;
    private GameState currentState;

    /**
     * Creates a new game.
     */
    public GameBase(){
        restart();
    }

    public void restart(){
        gameOver = false;
        resume();
        Logger.info("The game has been restarted.");
    }

    /**
     * Returns the game-specific state for when the game is running.
     * @return the game running state.
     */
    abstract protected GameState runningGameState();

    /**
     * Returns the game-specific state for when the game is paused.
     * @return the game paused state.
     */
    abstract protected GameState pausedGameState();

    /**
     * Returns the game-specific state for when the game is over.
     * @return the game over state.
     */
    abstract protected GameState gameOverGameState();

    public void pause(){
        paused = true;
        setCurrentState(pausedGameState());
        Logger.info("The game has been paused.");
    }

    public void resume(){
        paused = false;
        setCurrentState(runningGameState());
        Logger.info("The game has been resumed.");
    }

    public boolean isPaused(){
        return paused;
    }

    /**
     * Sets the current game state to the game over state.
     */
    protected void setGameOver(){
        gameOver = true;
        pause();
        setCurrentState(gameOverGameState());
        Logger.info("The game is over.");
    }

    public boolean isGameOver(){
        return gameOver;
    }

    public GameState getCurrentState(){
        return currentState;
    }

    /**
     * Sets the current game state to the specified state.
     * @param currentState the new state.
     */
    protected void setCurrentState(GameState currentState){
        this.currentState = currentState;
    }
}
