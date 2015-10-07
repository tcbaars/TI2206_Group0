package game;

import states.gamestates.GameState;

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
    }

    abstract protected GameState runningGameState();
    abstract protected GameState pausedGameState();
    abstract protected GameState gameOverGameState();

    public void pause(){
        paused = true;
        setCurrentState(pausedGameState());
    }

    public void resume(){
        paused = false;
        setCurrentState(runningGameState());
    }

    public boolean isPaused(){
        return paused;
    }

    protected void setGameOver(){
        gameOver = true;
        pause();
        setCurrentState(gameOverGameState());
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
