package entities;

import animations.Animation;
import org.junit.Test;

import java.awt.Graphics2D;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;


/**
 * Created by Adriaan on 11-9-2015.
 * Test method for entities.Entity class.
 */
public abstract class EntityTest {
    Entity test = new Entity() {
        protected void initialiseEntity() {}
        protected void initialiseSprite() {}
        protected Animation createAnimation() {
            return null;
        }
        protected void update() {
            spriteHeight = 123.0;
        }
        protected void draw(Graphics2D graphic) {}
        protected void consume(Entity food) {}
        protected int consumedBy(Entity eater) {
            return 0;
        }
    };

    @Test
    public void constructorTest() {
        assertTrue(test.isConsumable());
        assertTrue(test.isAlive());
        assertTrue(test.isVisible());
        assertTrue(test.isFacingRight());
    }

/*    @Test
    public void updateEntityTest() {
        assertNotEquals(123.0, test.entityHeight);
        test.updateEntity();
        assertEquals(123.0, test.entityHeight, 1e-15);
    }*/

    @Test
    public void killTest() {
        assertTrue(test.isConsumable());
        assertTrue(test.isVisible());
        test.kill();
        assertFalse(test.isConsumable());
        assertFalse(test.isVisible());
        test.setVisible(true);
        assertTrue(test.isVisible());
    }

    @Test
    public void facingrightTest() {
        test.setFacingRight(true);
        assertTrue(test.isFacingRight());
        test.setFacingRight(false);
        assertFalse(test.isFacingRight());
    }
}
