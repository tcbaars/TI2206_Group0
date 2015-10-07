package game;

import java.util.Iterator;

import entities.Entity;
import states.gamestates.GameState;

public interface Game {
    public void restart();
    public void pause();
    public void resume();
    public boolean isPaused();
    public boolean isGameOver();
    public void update();
    public Iterator<Entity> getEntities();
    public GameState getCurrentState();
}
