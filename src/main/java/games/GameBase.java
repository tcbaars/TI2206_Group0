package games;

import states.gamestates.GameState;
import util.Logger;

public abstract class GameBase implements Game{

    private boolean paused;
    private boolean gameOver;
    private GameState currentState;

    public GameBase(){
        restart();
    }
    public void restart(){
        gameOver = false;
        resume();
        Logger.info("The game has been restarted.");
    }

    abstract protected GameState runningGameState();
    abstract protected GameState pausedGameState();
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

    protected void setCurrentState(GameState currentState){
        this.currentState = currentState;
    }
}
