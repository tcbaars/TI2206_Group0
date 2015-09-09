package handlers;

import entities.Player;
import enumerations.Direction;

import java.awt.Graphics2D;

public class SinglePlayerGameHandler extends GameHandler {

  private boolean gameOver;
  private boolean gameWon;

  private Player player;
  private EnemyHandler enemies;

  public SinglePlayerGameHandler() {
    super();
  }

  protected void newGame() {
    player = new Player();
    gameOver = gameWon = false;
    paused = false;
    enemies = new EnemyHandler();
  }

  private void handleCollisions() {
    CollisionHandler.handlecollisions(player, enemies.getEnemies());
  }

  public boolean isGameOver() {
    return gameOver;
  }

  public boolean isGameWon() {
    return gameWon;
  }

  public void gameOver() {
    pause();
    gameOver = true;
  }

  public void gameLost() {
    gameOver();
    gameWon = false;
  }

  public void gameWon() {
    gameOver();
    gameWon = true;
  }

  @Override
  public void update() {
    if (!paused) {
      handleCollisions();
      player.updateEntity();
      enemies.update(player);

      if (!player.isAlive()) {
        gameLost();
      } else {
        if (player.isFull()) {
          if (enemies.getNumberEnemies() == 0) {
            gameWon();
          }
        }
      }

    }
  }

  @Override
  public void draw(Graphics2D graphic) {
    player.drawEntity(graphic);
    enemies.draw(graphic);
    drawHud(graphic);
  }

  @Override
  public void drawHud(Graphics2D graphic) {
    //System.out.println(player.getScore());
  }

  @Override
  public void move(Direction direction) {
    player.move(direction);
  }
}
