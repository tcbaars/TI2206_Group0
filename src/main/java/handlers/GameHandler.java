package handlers;

import enumerations.Direction;

import java.awt.Graphics2D;

public abstract class GameHandler {


  protected boolean paused;

  public GameHandler() {
    newGame();
  }

  protected abstract void newGame();

  public boolean isPaused() {
    return paused;
  }

  public void pause() {
    paused = true;
  }

  public void resume() {
    paused = false;
  }

  public abstract void update();

  public abstract void draw(Graphics2D graphic);

  public abstract void drawHud(Graphics2D graphic);

  public abstract void move(Direction drawing);

  public abstract boolean isGameOver();

  public abstract boolean isGameWon();
}
