package games;

import java.util.Iterator;

import entities.Entity;
import states.gamestates.GameState;

public interface Game {
    void restart();
    void pause();
    void resume();
    boolean isPaused();
    boolean isGameOver();
    void update();
    Iterator<Entity> getEntities();
    GameState getCurrentState();
}
