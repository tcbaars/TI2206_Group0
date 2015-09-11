package handlers;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import entities.Player;
import enumerations.Direction;

public class SinglePlayerGameHandler extends GameHandler {

    private boolean gameOver;
    private boolean gameWon;

    private Player player;
    private EnemyHandler enemies;

    private final Color scorefill = Color.WHITE;
    private final Color scoreoutline = Color.BLACK;
    private final float scoreoutlinesize = 2;
    private final Font scorefont = new Font("Times New Roman", Font.BOLD, 70);

    private final int yscore = 100;

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
        String scoretext = "Score: " + player.getScore();
        FontOutlineHandler.drawTextCenterWidth(graphic, scorefont, scoretext, scorefill, scoreoutline, scoreoutlinesize, yscore);
    }

    @Override
    public void move(Direction direction) {
        player.move(direction);
    }
}
