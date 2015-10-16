package settings;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ScreenSettingsTest {
    static ScreenSettings screenSettings;
    static Random random;

    /**
     * Initialize an instance of ScreenSettings and Random.
     */
    @BeforeClass
    public static void setup() {
        screenSettings = ScreenSettings.getInstance();
        random = new Random();
    }

    /**
     * Check if the instance returned is of the type ScreenSettings.
     */
    @Test
    public void getInstanceTest() {
        assertTrue(screenSettings instanceof ScreenSettings);
    }

    /**
     * Check if the default is 60.
     *
     * As the tests are run sequentially in name ascending order, the a_ indicates this will be ran first.
     */
    @Test
    public void a_defaultFpsValueTest() {
        assertEquals(60, screenSettings.getTargetFps());
    }

    /**
     * Check if the default is 2.
     *
     * As the tests are run sequentially in name ascending order, the a_ indicates this will be ran first.
     */
    @Test
    public void a_defaultScaleValueTest() {
        assertEquals(2, screenSettings.getScaling());
    }

    /**
     * Check if the default is 360 * scaling.
     *
     * As the tests are run sequentially in name ascending order, the a_ indicates this will be ran first.
     */
    @Test
    public void a_defaultHeightValueTest() {
        assertEquals(360 * screenSettings.getScaling(), screenSettings.getHeight());
    }

    /**
     * Check if the default is 640 * scaling.
     *
     * As the tests are run sequentially in name ascending order, the a_ indicates this will be ran first.
     */
    @Test
    public void a_defaultWidthValueTest() {
        assertEquals(640 * screenSettings.getScaling(), screenSettings.getWidth());
    }


    /**
     * Check if getScaling returns the same value as was put into setScaling.
     */
    @Test
    public void setScalingTest() {
        int value = random.nextInt();

        screenSettings.setScaling(value);

        assertEquals(value, screenSettings.getScaling());
    }
}
