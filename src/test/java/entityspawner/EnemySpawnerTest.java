//package entityspawner;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;
//
//import org.junit.Test;
//
//import entityspawner.EnemySpawner;
//
//public class EnemySpawnerTest {
//	
//	EnemySpawner enemyspawner = new EnemySpawner();
//	/**
//	* This method tests the getter for number of enemies.
//	*/
//	@Test
//	public void getNumberEnemiesTest() {
//		assertEquals(0,enemyspawner.getNumberEnemies());
//	}
//		
//	/**
//	* This method tests if it is the final stage.
//	*/
//	@Test
//	public void isFinalStageTest() {
//		assertFalse(enemyspawner.isFinalStage());
//	}	
//	
//	/**
//	* This method tests the setter for finalstage.
//	*/
//	@Test
//	public void setFinalStageTest() {
//		enemyspawner.setFinalStage();
//		assertTrue(enemyspawner.isFinalStage());
//	}
//	
//	/**
//	* This method tests if the entitylist is not empty.
//	*/
//	@Test
//	public void getEntitiesTest() {
//		assertTrue(enemyspawner.getEntities() != null);
//	}
//	
//	/**
//	* This method tests if it is the final stage.
//	*/
//	@Test
//	public void isActiveTest() {
//		assertTrue(enemyspawner.isActive());
//	}
//	
//	/**
//	 * this method tests the update method for looping the frames
//	 */
//	@Test
//	public void updateTest() {
//		while(enemyspawner.getNumberEnemies() == 0) {
//			enemyspawner.update();
//		}
//		assertFalse(enemyspawner.getNumberEnemies() == 0);
//		enemyspawner.setFinalStage();
//		while(enemyspawner.getNumberEnemies() != 0) {
//			enemyspawner.update();
//		}
//		assertFalse(enemyspawner.isActive());
//	}
//}
