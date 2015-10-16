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
}