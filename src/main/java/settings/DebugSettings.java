package settings;

/**
 * The DebugSettings class is responsible for the global debug settings.
 */
public class DebugSettings {

    private static final DebugSettings instance = new DebugSettings();
    private boolean debugMode;

    /**
     * Creates an instance of the global debug settings.
     * With the default settings.
     */
    private DebugSettings(){
        debugMode = false;
    }

    /**
     * Returns an instance of the global debug settings.
     * @return global debug settings.
     */
    public static DebugSettings getInstance(){
        return instance;
    }

    /**
     * Returns whether or not debug mode is enabled.
     * @return <code>true</code> if and only if debug mode is enabled, otherwise <code>false</code>.
     */
    public boolean isDebugModeOn(){
        return debugMode;
    }

    /**
     * Toggles whether or not debug mode is enabled.
     */
    public void toggleDebugMode(){
        debugMode = !debugMode;
    }

    /**
     * Disables debug mode.
     */
    public void disable(){
        debugMode = false;
    }

    /**
     * Enables debug mode.
     */
    public void enable(){
        debugMode = true;
    }
}
