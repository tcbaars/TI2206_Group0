package settings;

/**
 * The ScreenSettings class is responsible for handling the global screen settings.
 * The screen has a fixed aspect ratio, so the dimensions of the screen are adjusted by changing the scaling.
 */
public class ScreenSettings {

    private static final ScreenSettings instance = new ScreenSettings();
    private final static int fps = 60;
    private final static int width = 640;
    private final static int height = 360;
    private int scale;


    /**
     * Creates an instance of the global screen settings.
     * With the default screen settings.
     */
    private ScreenSettings(){
        scale = 2;
    }

    /**
     * Returns an instance of the global screen settings.
     * @return global screen settings.
     */
    public static ScreenSettings getInstance(){
        return instance;
    }

    /**
     * Returns the scaled width of the screen.
     * @return the width of the screen.
     */
    public int getWidth(){
        return width * scale;
    }

    /**
     * Returns the scaled height of the screen.
     * @return the height of the screen.
     */
    public int getHeight(){
        return height * scale;
    }

    /**
     * Returns the scaling applied to the screen's dimensions.
     * @return the scaling factor.
     */
    public int getScaling(){
        return scale;
    }

    /**
     * Sets the new scaling factor to be applied to the screen's dimensions.
     * @param scale the scaling factor.
     */
    public void setScaling(int scale){
        this.scale = scale;
    }

    /**
     * Returns the desired frames per second.
     * @return target frames per second.
     */
    public int getTargetFps(){
        return fps;
    }

}
