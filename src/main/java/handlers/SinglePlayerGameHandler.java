package handlers;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import entities.Player;
import enumerations.Direction;

/**
 * The SinglePlayerGameHandler class handles a single player game.
 * This involves handling the game states, the player, the enemies and the HUD.
 */
public class SinglePlayerGameHandler extends GameHandler {

    /*
     * Game over states
     */
    private boolean gameOver;
    private boolean gameWon;

    /*
     * Player and enemies
     */
    private Player player;
    private EnemyHandler enemies;

    /*
     * Appearance of the HUD.
     * - consists of the payer's current score
     */
    private final Color scorefill = Color.WHITE;
    private final Color scoreoutline = Color.BLACK;
    private final float scoreoutlinesize = 2;
    private final Font scorefont = new Font("Times New Roman", Font.BOLD, 70);

    private final int yscore = 100;

    /**
     * Creates a new single player game.
     */
    public SinglePlayerGameHandler() {
        super();
    }

    /**
     * Sets up the new single player game.
     */
    protected void newGame() {
        // Spawn player
        player = new Player();
        // Set game state to game started
        gameOver = gameWon = false;
        paused = false;
        // Spawn enemies
        enemies = new EnemyHandler();
    }

    /**
     * Handle the collisions between all the entities in the current single player game.
     */
    private void handleCollisions() {
        CollisionHandler.handlecollisions(player, enemies.getEnemies());
    }

    /**
     * Returns whether the game is over or not.
     *
     * @return <code>true</true> if and only if the game is over, otherwise <code>false</code>.
     */
    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * Returns whether the game has been won or not.
     *
     * @return <code>true</true> if and only if the game is won, otherwise <code>false</code>.
     */
    public boolean isGameWon() {
        return gameWon;
    }

    /**
     * Sets the game state to game over.
     */
    public void gameOver() {
        pause();
        gameOver = true;
    }

    /**
     * Sets the game state to game lost.
     */
    public void gameLost() {
        gameOver();
        gameWon = false;
    }

    /**
     * Sets the game to game won.
     */
    public void gameWon() {
        gameOver();
        gameWon = true;
    }

    /**
     * Update the player and the enemies in the single player game.
     */
    @Override
    public void update() {
        // If the game is not paused
        if (!paused) {
            // Manage any collisions and update the entities
            handleCollisions();
            player.updateEntity();
            enemies.update(player);

            // If the player died
            if (!player.isAlive()) {
                // Set the game state to game lost
                gameLost();
            } else {
                // If the player is alive and reached target size
                if (player.isFull()) {
                    // And no enemies are left
                    if (enemies.getNumberEnemies() == 0) {
                        // Set the game state to game won
                        gameWon();
                    }
                }
            }

        }
    }

    /**
     * Draws the current entities in the single player game to the specified 2-dimensional 'image'.
     *
     * @param graphics the 2-dimensional image
     */
    @Override
    public void draw(Graphics2D graphic) {
        player.drawEntity(graphic);
        enemies.draw(graphic);
        drawHud(graphic);
    }

    /**
     * Draws the HUD used to display game information to the player.
     *
     * @param the 2-dimensional image
     */
    @Override
    public void drawHud(Graphics2D graphic) {
        String scoretext = "Score: " + player.getScore();
        FontOutlineHandler.drawTextCenterWidth(graphic, scorefont, scoretext, scorefill, scoreoutline, scoreoutlinesize, yscore);
    }

    /**
     * Moves the current player sprite in the direction specified.
     *
     * @param direction the direction
     */
    @Override
    public void move(Direction direction) {
        player.move(direction);
    }
}
