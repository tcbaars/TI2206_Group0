package settings;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EnemySettingsTest {
    static EnemySettings enemySettings;
    static Random random;

    /**
     * Initialize the EnemySettings instance and a random number generator.
     */
    @BeforeClass
    public static void setup() {
        enemySettings = EnemySettings.getInstance();
        random = new Random();
    }

    /**
     * Check if the instance returned is of type EnemySettings
     */
    @Test
    public void getInstanceTest() {
        assertTrue(enemySettings instanceof EnemySettings);
    }

    /**
     * Check default value for spawnRate
     */
    @Test
    public void a_defaultValueSpawnRateTest() {
        assertEquals(100, enemySettings.getSpawnRate());
    }

    /**
     * Check default value for minEnemies
     */
    @Test
    public void a_defaultValueMinEnemiesTest() {
        assertEquals(10, enemySettings.getMinEnemies());
    }

    /**
     * Check default value for maxEnemies
     */
    @Test
    public void a_defaultValueMaxEnemiesTest() {
        assertEquals(14, enemySettings.getMaxEnemies());
    }

    /**
     * Check if values put in setSpawnRate are stored.
     */
    @Test
    public void setSpawnRateTest() {
        int value = random.nextInt();

        enemySettings.setSpawnRate(value);

        assertEquals(value, enemySettings.getSpawnRate());
    }

    /**
     * Check if values put in setMinEnemies are stored.
     */
    @Test
    public void setMinEnemiesTest() {
        int value = random.nextInt();

        enemySettings.setMinEnemies(value);

        assertEquals(value, enemySettings.getMinEnemies());
    }

    /**
     * Check if values put in setMaxEnemies are stored.
     */
    @Test
    public void setMaxEnemiesTest() {
        int value = random.nextInt();

        enemySettings.setMaxEnemies(value);

        assertEquals(value, enemySettings.getMaxEnemies());
    }
}