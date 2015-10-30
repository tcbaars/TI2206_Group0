package entityspawner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import entities.Enemy;
import entityspawner.BubbleSpawner;
import enumerations.GameEntities;

public class BubbleSpawnerTest {
	
	Enemy enemy = Enemy.generate(GameEntities.TROUT);
	BubbleSpawner bubblespawner = new BubbleSpawner(enemy);
	
	/**
    * This method tests if the entity has blown.
    */
	@Test
	public void hasBlownTest() {
		assertFalse(bubblespawner.hasBlown());
	}
	
	/**
	* This method tests bubblespawner is active.
	*/
	@Test
	public void isAcitveTest() {
		assertTrue(bubblespawner.isActive());
	}	
	
	/**
	* This method tests if the entitylist is not empty.
	*/
	@Test
	public void getEntitiesTest() {
		assertTrue(bubblespawner.getEntities() != null);
	}
	
	/**
	 * this method tests the update method for looping the frames
	 */
	@Test
	public void updateTest() {
		for(int i = 0; i < 2000; i++) {
			enemyspawner.update();
		}
		assertFalse(enemyspawner.getNumberEnemies() == 0);
		enemyspawner.setFinalStage();
		for(int i = 0; i < 6000; i++) {
			enemyspawner.update();
		}
		assertTrue(enemyspawner.getNumberEnemies() == 0);
	}
}
