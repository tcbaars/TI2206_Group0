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
    @Test
    public void constructorTest() {
        SinglePlayerGameHandler test = new SinglePlayerGameHandler();
        assertFalse(test.isGameOver());
        assertFalse(test.isGameWon());
        assertFalse(test.isPaused());
    }

    @Test
    public void gameOverTest() {
        SinglePlayerGameHandler test = new SinglePlayerGameHandler();
        test.gameOver();
        assertTrue(test.isPaused());
        assertTrue(test.isGameOver());
    }

    @Test
    public void gameWonLostTest() {
        SinglePlayerGameHandler test = new SinglePlayerGameHandler();

        assertFalse(test.isGameWon());
        test.gameWon();
        assertTrue(test.isGameWon());
        test.gameLost();
        assertFalse(test.isGameWon());
    }

    @Test
    public void getScoreTest() {
        SinglePlayerGameHandler test = new SinglePlayerGameHandler();
        assertEquals(0, test.getScore());
        test.move(Direction.UP);
    }

    @Test
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
    }
}
