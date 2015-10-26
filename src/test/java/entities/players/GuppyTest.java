package entities.players;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import entities.enemies.Trout;
import enumerations.GameEntities;

public class GuppyTest {
    Guppy guppy;

    /**
     * Create a fresh Guppy before each test
     */
    @Before
    public void setup() {
        guppy = new Guppy();
    }

    /**
     * Verify that guppy is an instance of Guppy.
     */
    @Test
    public void instanceOfGuppy() {
        assertTrue(guppy instanceof Guppy);
    }

    /**
     * Check that guppy is consumable.
     */
    @Test
    public void isConsumableTest() {
        assertTrue(guppy.isConsumable());
    }

    /**
     * Check that guppy has sub entities, bubbles...
     */
    @Test
    public void hasSubEntitiesTest() {
        assertTrue(guppy.hasSubEntities());
    }

    /**
     * Check that the amount of bubbles is not null.
     */
    @Test
    public void getSubEntitiesTest() {
        assertTrue(guppy.getSubEntities() !=  null);
    }

    /**
     * At start, guppy will have a score of 0.
     */
    @Test
    public void getCurrentScoreTest() {
        assertTrue(guppy.getCurrentScore() == 0);
    }

    /**
     * At start, guppy will not have eatena ny fish.
     */
    @Test
    public void getNumberFishEatenTest() {
        assertTrue(guppy.getNumberFishEaten() == 0);
    }

    /**
     * guppy's score scaling factor is determined by GameEntities
     */
    @Test
    public void getScoreScalingFactorTest() {
        assertTrue(GameEntities.GUPPY.getScoreScalingFactor() == guppy.getScoreScalingFactor());
    }

    /**
     * guppy's movement scaling factor is determined by GameEntities
     */
    @Test
    public void getMovementScalingFactorTest() {
        assertTrue(GameEntities.GUPPY.getMovementSpeedScalingFactor() == guppy.getMovementSpeedScalingFactor());
    }

    /**
     * At start it cannot eat a default sized Trout
     */
    @Test
    public void consumeTest() {
        Trout trout = new Trout();
        assertFalse(guppy.consume(trout));
    }

    /**
     * At start guppy can eat fish
     */
    @Test
    public void isFullTest() {
        assertFalse(guppy.isFull());
    }
}
