package settings;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DebugSettingsTest {
    static DebugSettings debugSettings;

    /**
     * Initialize an instance of DebugSettings before each test.
     */
    @BeforeClass
    public static void setup() {
        debugSettings = DebugSettings.getInstance();
    }

    /**
     * Check if the instance returned is of the type DebugSettings.
     */
    @Test
    public void getInstanceTest() {
        assertTrue(debugSettings instanceof DebugSettings);
    }

    /**
     * Check if the default is false.
     *
     * As the tests are run sequentially in name ascending order, the a_ indicates this will be ran first.
     */
    @Test
    public void a_defaultValueTest() {
        assertFalse(debugSettings.isDebugModeOn());
    }

    /**
     * Test if the toggleDebugMode() switches the boolean value.
     */
    @Test
    public void toggleDebugModeTest() {
        boolean value = debugSettings.isDebugModeOn();
        debugSettings.toggleDebugMode();
        assertTrue(value != debugSettings.isDebugModeOn());
    }

    /**
     * Test if disable() switches the value to false.
     */
    @Test
    public void disableTest() {
        if (!debugSettings.isDebugModeOn()) {
            debugSettings.toggleDebugMode();
        }

        assertTrue(debugSettings.isDebugModeOn());

        debugSettings.disable();

        assertFalse(debugSettings.isDebugModeOn());
    }

    /**
     * Test if enable() switches the value to true.
     */
    @Test
    public void enableTest() {
        if (debugSettings.isDebugModeOn()) {
            debugSettings.toggleDebugMode();
        }

        assertFalse(debugSettings.isDebugModeOn());

        debugSettings.enable();

        assertTrue(debugSettings.isDebugModeOn());
    }
}
