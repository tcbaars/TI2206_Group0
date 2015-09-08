package handlers;

import java.awt.Graphics2D;

import entities.Player;
import enumerations.Direction;

public abstract class GameHandler {
 
    
    protected boolean paused;

    public GameHandler() {
    	newGame();
    }
    
    protected abstract void newGame();
    
    public boolean isPaused(){
    	return paused;
    }
    
    public void pause(){
    	paused = true;
    }
    
    public void resume(){
    	paused = false;
    }
  
    public abstract void update();

    public abstract void draw(Graphics2D g);

    public abstract void drawHUD(Graphics2D g);

    public abstract void move(Direction d);
    
    public abstract boolean isGameOver();
    public abstract boolean isGameWon();
}
