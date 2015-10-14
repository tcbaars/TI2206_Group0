package settings;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MusicSettingsTest {
    static MusicSettings musicSettings;

    /**
     * Initialize an instance of MusicSettings.
     */
    @BeforeClass
    public static void setup() {
        musicSettings = MusicSettings.getInstance();
    }

    /**
     * Check if the instance returned is of the type MusicSettings.
     */
    @Test
    public void getInstanceTest() {
        assertTrue(musicSettings instanceof MusicSettings);
    }

    /**
     * Check if the default is true.
     *
     * As the tests are run sequentially in name ascending order, the a_ indicates this will be ran first.
     */
    @Test
    public void a_defaultValueTest() {
        assertTrue(musicSettings.isMusicOn());
    }

    /**
     * Test if the toggleMusic() switches the boolean value.
     */
    @Test
    public void toggleMusicModeTest() {
        boolean value = musicSettings.isMusicOn();
        musicSettings.toggleMusic();
        assertTrue(value != musicSettings.isMusicOn());
    }

    /**
     * Test if muteMusic() switches the value to false.
     */
    @Test
    public void muteMusicTest() {
        if (!musicSettings.isMusicOn()) {
            musicSettings.toggleMusic();
        }

        assertTrue(musicSettings.isMusicOn());

        musicSettings.muteMusic();

        assertFalse(musicSettings.isMusicOn());
    }

    /**
     * Test if unMuteMusic() switches the value to true.
     */
    @Test
    public void enableTest() {
        if (musicSettings.isMusicOn()) {
            musicSettings.toggleMusic();
        }

        assertFalse(musicSettings.isMusicOn());

        musicSettings.unMuteMusic();

        assertTrue(musicSettings.isMusicOn());
    }
}
