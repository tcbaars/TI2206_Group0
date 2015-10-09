package enumerations;

/**
 * The GameSprites enumeration is used as a way to easily reference the initial sprite properties for each entity.
 * The dimensions of the sprite are based on the associated animation's aspect ratio.
 * The dimensions of the sprite are used to scale the frame width and frame height to a more appropriate size for the screen dimensions.
 */
public enum GameSprites {
    GUPPY(GameAnimations.GUPPY, 125, 350),
    TROUT(GameAnimations.TROUT, 50, 600),
    DUNKLEOSTEUS(GameAnimations.DUNKLEOSTEUS, 45, 400),
    SWORDFISH(GameAnimations.SWORDFISH, 200, 450),
    STINGRAY(GameAnimations.STINGRAY, 100, 450),
    SHARK(GameAnimations.SHARK, 300, 450),
    BUBBLE(GameAnimations.BUBBLE, 20, 50);

    private GameAnimations animation;
    private double minWidth;
    private double maxWidth;

    /**
     * The initial sprite properties for each entity.
     * The dimensions of the sprite is based on the associated animation's aspect ratio.
     * Which means that only the width of the sprite is needed.
     * Where the width of the sprite represents the desired width of the frame that contains the entity.
     * @param animation the initial animation properties.
     * @param minWidth the minimum width the sprite can be.
     * @param maxWidth the maximum width the sprite can be.
     */
    private GameSprites(GameAnimations animation, double minWidth, double maxWidth){
        this.animation = animation;
        this.minWidth = minWidth;
        this.maxWidth = maxWidth;
    }

    /**
     * Returns the initial animation properties of the sprite.
     * @return the animation properties.
     */
    public GameAnimations getGameAnimation(){
        return animation;
    }

    /**
     * Returns the minimum width of the sprite.
     * @return the minimum width.
     */
    public double getMinWidth(){
        return minWidth;
    }

    /**
     * Returns the maximum width of the sprite.
     * @return the maximum width.
     */
    public double getMaxWidth(){
        return maxWidth;
    }

    /**
     * Returns the minimum height of the sprite.
     * Using the aspect ratio of the sprite.
     * @return the minimum height.
     */
    public double getMinHeight(){
        return minWidth * getMinSpriteScaling();
    }

    /**
     * Returns the maximum height of the sprite.
     * Using the aspect ratio of the sprite.
     * @return the maximum height.
     */
    public double getMaxHeight(){
        return maxWidth * getMaxSpriteScaling();
    }

    /**
     * Returns the scaling factor necessary to be applied to the frame to get the minimum sprite dimensions.
     * @return the scaling factor.
     */
    public double getMinSpriteScaling(){
        return minWidth / animation.getFrameWidth();
    }

    /**
     * Returns the scaling factor necessary to be applied to the frame to get the maximum sprite dimensions.
     * @return the scaling factor.
     */
    public double getMaxSpriteScaling(){
        return maxWidth / animation.getFrameWidth();
    }
}
