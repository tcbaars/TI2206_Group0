package entities.bubbles;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import entities.bubbles.Bubble;
import entities.enemies.Trout;
import enumerations.GameEntities;

public class BubbleTest {
	
	Trout entity = new Trout();
	Bubble bubble = new Bubble(entity);
	
    /**
     * This method tests if it is consumable.
     */
    @Test
    public void isConsumableTest() {
    	assertFalse(bubble.isConsumable());
    }
    
    /**
     * This method tests if there are subentities, bubbles.
     */
    @Test
    public void hasSubEntitiesTest() {
    	assertFalse(bubble.hasSubEntities());
    }
    
    /**
     * This method tests if there are more than 0 bubbles.
     */
    @Test
    public void getSubEntitiesTest() {
    	assertTrue(bubble.getSubEntities() == null);
    }
    
    /**
     * This method tests the scorescalingfactor.
     */
    @Test
    public void getScoreScalingFactorTest() {
    	assertEquals(GameEntities.BUBBLE.getScoreScalingFactor(), bubble.getScoreScalingFactor(), 0);
    }
    
    /**
     * This method tests the movementspeedscalingfactor.
     */
    @Test
    public void getmovementSpeedScalingFactorTest() {
    	assertEquals(GameEntities.BUBBLE.getMovementSpeedScalingFactor(),bubble.getMovementSpeedScalingFactor(),0);
    }
    
    /**
     * This method tests the kill.
     */
    @Test
    public void killTest() {
    	assertTrue(bubble.isAlive());
    	bubble.kill();
    	assertFalse(bubble.isAlive());
    }
    
    /**
     * This method tests if it can consume.
     */
    @Test
    public void consumeTest() {
    	assertFalse(bubble.consume(entity));
    }
}
