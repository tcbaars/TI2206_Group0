package enumerations;

/**
 * The GameIcons enumeration is used as a way to easily reference the properties of images commonly used as icons.
 * The properties are structured in such a way that they can easily be loaded and retrieved.
 */
public enum GameImages {
    BACKGROUND("Background", "/backgrounds/Background.png", 1920, 1080),
    MUSIC_OFF("MusicOff","/icons/musicOff.png", 30, 30),
    MUSIC_ON("MusicOn","/icons/musicOn.png", 30, 30),
    SOUND_OFF("SoundOff", "/icons/soundOff.png", 30, 30),
    SOUND_ON("SoundOn", "/icons/soundOn.png", 30, 30),
    ARROW("Arrow", "/icons/arrow.png", 0, 0),
    SELECTED_ARROW("SelectedArrow", "/icons/arrowSelected.png", 0, 0),
    DIRECTION_KEYS("ArrowKeys", "/icons/arrowKeys.png", 332, 227),
    ESC_KEY("Esc", "/icons/escKey.png", 559, 527),
    M_KEY("M", "/icons/mKey.png", 559, 527),
    S_KEY("S","/icons/sKey.png", 559, 527);

    private String imageKey;
    private String imageUrl;
    private int imageWidth;
    private int imageHeight;

    /**
     * The properties of the image.
     * @param imageKey unique identifier for the image.
     * @param imageUrl resource path of the image.
     * @param imageWidth width of the image.
     * @param imageHeight height of the image.
     */
    private GameImages(String imageKey, String imageUrl, int imageWidth, int imageHeight){
        this.imageKey = imageKey;
        this.imageUrl = imageUrl;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
    }

    /**
     * Returns the unique identifier for the image.
     * @return the image key.
     */
    public String getKey(){
        return imageKey;
    }

    /**
     * Returns the location of the image.
     * @return the resource path of the image.
     */
    public String getUrl(){
        return imageUrl;
    }

    /**
     * Returns the width of the image.
     * @return image width.
     */
    public int getWidth(){
        return imageWidth;
    }

    /**
     * Returns the height of the image.
     * @return image height.
     */
    public int getHeight(){
        return imageHeight;
    }
}
