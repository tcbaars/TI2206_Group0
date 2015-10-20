package settings;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SoundSettingsTest {
    static SoundSettings soundSettings;

    /**
     * Initialize an instance of SoundSettings.
     */
    @BeforeClass
    public static void setUp() {
        soundSettings = SoundSettings.getInstance();
    }

    /**
     * Check if the instance returned is of the type SoundSettings.
     */
    @Test
    public void getInstanceTest() {
        assertTrue(soundSettings instanceof SoundSettings);
    }

    /**
     * Check if the default is true.
     *
     * As the tests are run sequentially in name ascending order, the a_ indicates this will be ran first.
     */
    @Test
    public void a_defaultValueTest() {
        assertTrue(soundSettings.isSoundOn());
    }

    /**
     * Test if the toggleSound() switches the boolean value.
     */
    @Test
    public void toggleSoundModeTest() {
        boolean value = soundSettings.isSoundOn();
        soundSettings.toggleSound();
        assertNotSame(value, soundSettings.isSoundOn());
    }

    /**
     * Test if muteSound() switches the value to false.
     */
    @Test
    public void muteSoundTest() {
        if (!soundSettings.isSoundOn()) {
            soundSettings.toggleSound();
        }

        assertTrue(soundSettings.isSoundOn());

        soundSettings.muteSound();

        assertFalse(soundSettings.isSoundOn());
    }

    /**
     * Test if unMuteSound() switches the value to true.
     */
    @Test
    public void enableTest() {
        if (soundSettings.isSoundOn()) {
            soundSettings.toggleSound();
        }

        assertFalse(soundSettings.isSoundOn());

        soundSettings.unMuteSound();

        assertTrue(soundSettings.isSoundOn());
    }
}
