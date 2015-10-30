package settings;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PowerupSettingsTest {
    static PowerupSettings powerupSettings;

    /**
     * Initialize an instance of DebugSettings before each test.
     */
    @BeforeClass
    public static void setUp() {
        powerupSettings = PowerupSettings.getInstance();
    }

    /**
     * Check if the instance returned is of the type DebugSettings.
     */
    @Test
    public void getInstanceTest() {
        assertTrue(powerupSettings instanceof PowerupSettings);
    }

    @Test
    public void getSpawnRateTest() {
        assertEquals(powerupSettings.getSpawnRate(), 1200);
    }

    @Test
    public void getIncrementTest() {
        assertEquals(powerupSettings.getIncrement(), 2);
    }

    @Test
    public void getPowerupRateTest() {
        assertEquals(powerupSettings.getPowerupRate(), 600);
    }

    @Test
    public void getDespawnRateTest() {
        assertEquals(powerupSettings.getDespawnRate(), 300);
    }

    /**
     * Check default value for minPowerups
     */
    @Test
    public void a_defaultValueMinEnemiesTest() {
        assertEquals(0, powerupSettings.getMinEnemies());
    }

    /**
     * Check default value for maxPowerups
     */
    @Test
    public void a_defaultValueMaxEnemiesTest() {
        assertEquals(2, powerupSettings.getMaxEnemies());
    }

    /**
     * Test if the toggleDebugMode() switches the boolean value.
     */
    @Test
    public void togglePowerupTest() {
        boolean value = powerupSettings.powerupIsActive();
        powerupSettings.togglePowerup();
        assertNotSame(value, powerupSettings.powerupIsActive());
    }
}
