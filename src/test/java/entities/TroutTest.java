package entities;

import enumerations.Direction;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Adriaan on 11-9-2015.
 * Test method for entities.Trout class.
 */
public class TroutTest extends EnemyTest {
    private static final double DELTA = 1e-15;

    /*@Test
    public void constructorTest() {
        Trout test = new Trout();
        assertTrue(test.isConsumable());
        assertTrue(test.isAlive());
        assertTrue(test.isVisible());
        assertTrue(test.isFacingRight());
    }*/

    @Test
    public void initialiseEntityTest() {
        Trout test = new Trout();
        assertEquals(1300, test.spriteWidth, DELTA);
        assertEquals(600, test.spriteHeight, DELTA);
    }

    @Test
    public void setDirectionTest() {
        Trout test = new Trout();
        test.setDirection(Direction.UP);
    }
}
