package settings;

/**
 * The SoundSettings class is responsible for the global sound settings.
 */
public class SoundSettings {

    private static final SoundSettings instance = new SoundSettings();
    private boolean soundOn;

    /**
     * Creates an instance of the global sound settings.
     * With the default settings.
     */
    private SoundSettings(){
        soundOn = true;
    }

    /**
     * Returns an instance of the global sound settings.
     * @return global sound settings.
     */
    public static SoundSettings getInstance(){
        return instance;
    }

    /**
     * Returns whether or not sound is enabled.
     * @return <code>true</code> if and only if sound is enabled, otherwise <code>false</code>.
     */
    public boolean isSoundOn(){
        return soundOn;
    }

    /**
     * Toggles whether or not sound is enabled.
     */
    public void toggleSound(){
        soundOn = !soundOn;
    }

    /**
     * Disables sound.
     */
    public void muteSound(){
        soundOn = false;
    }

    /**
     * Enables sound.
     */
    public void unMuteSound(){
        soundOn = true;
    }

}
