package enumerations;

/**
 * The GameAnimations enumeration is used as a way to easily reference the initial animation properties of each sprite.
 */
public enum GameAnimations {
    GUPPY("Guppy", "/sprites/Guppy.png", 1300, 600, 12, 10, true),
    TROUT("Trout", "/sprites/Trout.png", 1300, 600, 12, 10, true),
    DUNKLEOSTEUS("Dunkleosteus", "/sprites/Dunkleosteus.png", 1300, 600, 3, 10, true),
    SWORDFISH("Swordfish", "/sprites/Swordfish.png", 1300, 600, 2, 10, true),
    STINGRAY("Stingray", "/sprites/Stingray.png", 1300, 600, 6, 10, true),
    SHARK("Shark", "/sprites/Shark.png", 1300, 600, 3, 10, true),
    BUBBLE("Bubble", "/sprites/Bubble.png", 1000, 1000, 1, 0, true),
    SPEEDUP("Speedup", "/sprites/Speedup.png", 510, 595, 1, 0, true);

    private String key;
    private String url;
    private int frameWidth;
    private int frameHeight;
    private int numberFrames;
    private int frameDelay;
    private boolean isFacingLeft;

    /**
     * The initial animation properties for the sprite.
     * @param key unique identifier for animation.
     * @param url resource path of animation.
     * @param frameWidth the width of a frame.
     * @param frameHeight the height of a frame.
     * @param numberFrames the number of frames.
     * @param frameDelay the delay between frames.
     * @param isFacingLeft whether or not the sprite is initially facing left.
     */
    private GameAnimations(String key, String url, int frameWidth, int frameHeight, int numberFrames, int frameDelay, boolean isFacingLeft){
        this.key = key;
        this.url = url;
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.numberFrames = numberFrames;
        this.frameDelay = frameDelay;
        this.isFacingLeft = isFacingLeft;
    }

    /**
     * Returns the unique identifier of the animation.
     * @return the animation key.
     */
    public String getAnimationkey(){
        return key;
    }

    /**
     * Returns the location of the animation.
     * @return the resource path of animation.
     */
    public String getAnimationUrl(){
        return url;
    }

    /**
     * Returns the width of a frame.
     * @return frame width.
     */
    public int getFrameWidth(){
        return frameWidth;
    }

    /**
     * Returns the height of a frame.
     * @return frame height.
     */
    public int getFrameHeight(){
        return frameHeight;
    }

    /**
     * Returns the number of frames in the animation.
     * @return number of frames.
     */
    public int getNumberFrames(){
        return numberFrames;
    }

    /**
     * Returns the delay between frames.
     * @return frame delay
     */
    public int getFrameDelay(){
        return frameDelay;
    }

    /**
     * Returns whether or not the sprite is facing left.
     * @return <code>true</code> if and only if the sprite is facing left, otherwise <code>false</code>.
     */
    public boolean isFacingLeft(){
        return isFacingLeft;
    }
}
