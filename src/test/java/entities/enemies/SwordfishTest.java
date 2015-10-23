package enemies.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import entities.Enemy;
import entities.enemies.Swordfish;
import enumerations.GameEntities;

/**
 *  Testing class for the swordfish properties
 * @author Dylan
 */
public class SwordfishTest {
	
	Enemy enemy = Enemy.generate(GameEntities.SWORDFISH);
    
    /**
     * This method tests the scorescalingfactor.
     */
    @Test
    public void getScoreScalingFactorTest() {
    	assertEquals(GameEntities.SWORDFISH.getScoreScalingFactor(), enemy.getScoreScalingFactor(), 0);
    }
    
    /**
     * This method tests the movementspeedscalingfactor.
     */
    @Test
    public void getmovementSpeedScalingFactorTest() {
    	assertEquals(GameEntities.SWORDFISH.getMovementSpeedScalingFactor(),enemy.getMovementSpeedScalingFactor(),0);
    }
    
    
    /**
     * This method tests if it is consumable.
     */
    @Test
    public void isConsumableTest() {
    	assertTrue(enemy.isConsumable());
    }
    
    
    /**
     * This method tests if there are subentities, bubbles.
     */
    @Test
    public void hasSubEntitiesTest() {
    	assertTrue(enemy.hasSubEntities());
    }
    
    /**
     * This method tests if there are more than 0 bubbles.
     */
    @Test
    public void getSubEntitiesTest() {
    	assertFalse(enemy.getSubEntities() == null);
    }

    /**
     * This method tests if it can consume (can always consume self because of size).
     */
    @Test
    public void consumeTest() {
    	assertTrue(enemy.consume(enemy));
    }

}
