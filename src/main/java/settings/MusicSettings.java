package settings;

/**
 * The MusicSettings class is responsible of the global music settings.
 */
public class MusicSettings {

    private static final MusicSettings instance = new MusicSettings();
    private boolean musicOn;

    /**
     * Creates an instance of the global music settings.
     * With the default settings.
     */
    private MusicSettings(){
        musicOn = true;
    }

    /**
     * Returns an instance of the global music settings.
     * @return global music settings.
     */
    public static MusicSettings getInstance(){
        return instance;
    }

    /**
     * Returns whether or not music is enabled.
     * @return <code>true</code> if and only if music is enabled, otherwise <code>false</code>.
     */
    public boolean isMusicOn(){
        return musicOn;
    }

    /**
     * Toggles whether or not music is enabled.
     */
    public void toggleMusic(){
        musicOn = !musicOn;
    }

    /**
     * Disables music.
     */
    public void muteMusic(){
        musicOn = false;
    }

    /**
     * Enables music.
     */
    public void unMuteMusic(){
        musicOn = true;
    }
}
