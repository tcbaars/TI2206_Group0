package handlers;

import enumerations.Direction;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Adriaan on 17-9-2015.
 * Test method for handlers.SinglePlayerGameHandler class.
 */
public class SinglePlayerGameHandlerTest extends GameHandlerTest {
    /**
     * Test the constructor (super())
     */
    @Test
    public void constructorTest() {
        SinglePlayerGameHandler test = new SinglePlayerGameHandler();
        assertFalse(test.isGameOver());
        assertFalse(test.isGameWon());
        assertFalse(test.isPaused());
    }

    /**
     * Test the gameover method by checking if it will pause and it will be gameover
     */
    @Test
    public void gameOverTest() {
        SinglePlayerGameHandler test = new SinglePlayerGameHandler();
        test.gameOver();
        assertTrue(test.isPaused());
        assertTrue(test.isGameOver());
    }

    /**
     * Testing the game won and game lost methods.
     */
    @Test
    public void gameWonLostTest() {
        SinglePlayerGameHandler test = new SinglePlayerGameHandler();

        assertFalse(test.isGameWon());
        test.gameWon();
        assertTrue(test.isGameWon());
        test.gameLost();
        assertFalse(test.isGameWon());
    }

    /**
     * Check if the initialscore is 0
     */
    @Test
    public void getScoreTest() {
        SinglePlayerGameHandler test = new SinglePlayerGameHandler();
        assertEquals(0, test.getScore());
        test.move(Direction.UP);
    }

    /**
     * Test if the update() method is working by running the game 2000 frames and checking if the player dies.
     */
 /*   @Test
    public void updateTest() {
        SinglePlayerGameHandler test = new SinglePlayerGameHandler();
        int i = 2000;
        while (!test.isPaused() && i > 0) {
            test.update();
            i--;
        }
        assertTrue(test.isPaused());
        assertTrue(test.isGameOver());
        assertFalse(test.isGameWon());
    }*/
}
