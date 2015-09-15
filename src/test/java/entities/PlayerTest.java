package entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import enumerations.Direction;

/**
 * Created by Adriaan on 11-9-2015. Test method for entities.Player class.
 */
public class PlayerTest extends EntityTest {
    private static final double DELTA = 1e-15;

    @Test
    public void constructorTest() {
        Player test = new Player();
        assertEquals(0, test.getScore());
    }

    @Test
    public void initialiseEntityTest() {
        Player test = new Player();
        test.initialiseEntity();
        assertEquals(1280, test.entityWidth, DELTA);
        assertEquals(560, test.entityHeight, DELTA);
    }

    @Test
    public void initialiseSpriteTest() {
        Player test = new Player();
        test.initialiseSprite();
        assertEquals(1300, test.spriteWidth, DELTA);
        assertEquals(600, test.spriteHeight, DELTA);
        assertEquals(100, test.currentScale, DELTA);
        assertEquals(1000, test.targetScale, DELTA);
    }

    @Test
    public void moveTest() {
        Player test = new Player();
        double y = test.topLeftY;
        double x = test.topLeftX;

        test.move(Direction.UP);
        assertNotEquals(y, test.topLeftY);
        test.move(Direction.DOWN);
        assertEquals(y, test.topLeftY, DELTA);

        test.move(Direction.LEFT);
        assertNotEquals(x, test.topLeftX);
        test.move(Direction.RIGHT);
        assertEquals(x, test.topLeftX, DELTA);
    }

}
