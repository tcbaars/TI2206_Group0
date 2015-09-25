package entities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Graphics2D;

import org.junit.Test;

import animations.Animation;


/**
 * Created by Adriaan on 11-9-2015.
 * Test method for entities.Entity class.
 */
public abstract class EntityTest {
    Entity test = new EntityBase() {
        @Override
        protected void initialiseEntity() {}
        @Override
        protected void initialiseSprite() {}
        @Override
        protected Animation createAnimation() {
            return null;
        }
        @Override
        protected void update() {
            spriteHeight = 123.0;
        }
        @Override
        protected void draw(Graphics2D graphic) {}
        @Override
        public void consume(Entity food) {}
        @Override
        public int consumedBy(Entity eater) {
            return 0;
        }
    };
    /*
    @Test
    public void constructorTest() {
        assertTrue(test.isConsumable());
        assertTrue(test.isAlive());
        assertTrue(test.isVisible());
        assertTrue(test.isFacingRight());
    }*/

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
