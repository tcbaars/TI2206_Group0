package enumerations;

/**
 * The GameEntities enumeration is used as a way to easily reference the initial entity properties in a game.
 * The dimensions of the entity are based on the associated sprite's aspect ratio.
 * The initial dimensions of the entity represent the initial dimensions of the entity contained within the frame of the sprite sheet.
 * The dimensions of the entity are used for calculating the area of the entity and handling collisions.
 * Therefore the dimensions should resemble the actual dimensions of the entity contained within the frame as closely as possible.
 * The dimensions of the frame are scaled by the sprite's scaling factor, so the entity's dimensions should be scaled by the same factor.
 * The entity's centre of mass should be at the centre of the frame which contains it.
 * The moving speed scaling factor is used to scale the base movement speed of an entity.
 * In other words, the more the moving speed factor is increased the faster the entity will be compared to other entities of the same size.
 * The score scaling factor is used to scale the base score increment.
 * In other words, the more the score scaling factor is increased the more points the entity will be worth compared to other entities of the same size.
 */
public enum GameEntities {
    GUPPY(GameSprites.GUPPY, 1280, 558, 1, true, 0.9),
    TROUT (GameSprites.TROUT, 1280, 518, 0.1, true, 0.2),
    DUNKLEOSTEUS(GameSprites.DUNKLEOSTEUS, 1200, 400, 0.1, true, 0.5),
    SWORDFISH(GameSprites.SWORDFISH, 1200, 330, 0.1, true, 0.8),
    STINGRAY(GameSprites.STINGRAY, 1075, 155, 0.1, true, 0.4),
    SHARK(GameSprites.SHARK, 1190, 452, 0.1, true, 0.9),
    BUBBLE(GameSprites.BUBBLE, 1000, 1000, 0.1, false, 0.01);

    private GameSprites sprite;
    private double entityWidth;
    private double entityHeight;
    private double movementSpeedScalingFactor;
    private boolean consumable;
    private double scoreScalingFactor;

    /**
     * The initial entity properties.
     * The dimensions of the entity is based on the associated sprite's aspect ratio.
     * Which means that only the width of the entity is needed.
     * Where the width of the entity represents the width of the entity contained in the frames of the sprite sheet.
     * @param sprite the sprite.
     * @param entityWidth the width of the entity.
     * @param movementSpeedScalingFactor the movement speed scaling factor.
     * @param consumable whether or not the entity is consumable.
     * @param scoreScalingFactor the score scaling factor.
     */
    private GameEntities(GameSprites sprite, double entityWidth, double entityHeight, double movementSpeedScalingFactor, boolean consumable, double scoreScalingFactor){
        this.sprite = sprite;
        this.entityWidth = entityWidth;
        this.entityHeight = entityHeight;
        this.movementSpeedScalingFactor = movementSpeedScalingFactor;
        this.consumable = consumable;
        this.scoreScalingFactor = scoreScalingFactor;
    }

    /**
     * Returns the sprite properties of the entity.
     * @return the sprite.
     */
    public GameSprites getSprite(){
        return sprite;
    }

    /**
     * Returns the width of the entity.
     * Where the width of the entity is the width of the entity contained in the frames of the sprite sheet.
     * @return the width of the entity.
     */
    public double getEntityWidth(){
        return entityWidth;
    }

    /**
     * Returns the height of the entity.
     * Where the height of the entity is the height of the entity contained in the frames of the sprite sheet.
     * Using the aspect ratio of the entity.
     * @return the height of the entity.
     */
    public double getEntityHeight(){
        return entityHeight;
    }

    /**
     * Returns the width of the entity when the frame is scaled to the minimum sprite dimensions.
     * Because the entity is contained within the frame, the same scaling factor should be applied to the entity's dimensions.
     * @return the width of the entity when the sprite is the smallest.
     */
    public double getMinEntityWidth(){
        return entityWidth * sprite.getMinSpriteScaling();
    }

    /**
     * Returns the width of the entity when the frame is scaled to the maximum sprite dimensions.
     * Because the entity is contained within the frame, the same scaling factor should be applied to the entity's dimensions.
     * @return the width of the entity when the sprite is the largest.
     */
    public double getMaxEntityWidth(){
        return entityWidth * sprite.getMaxSpriteScaling();
    }

    /**
     * Returns the height of the entity when the frame is scaled to the minimum sprite dimensions.
     * Using the aspect ratio of the entity.
     * @return the height of the entity when the sprite is the smallest.
     */
    public double getMinEntityHeight(){
        return entityHeight * sprite.getMinSpriteScaling();
    }

    /**
     * Returns the height of the entity when the frame is scaled to the maximum sprite dimensions.
     * Using the aspect ratio of the entity.
     * @return the height of the entity when the sprite is the largest.
     */
    public double getMaxEntityHeight(){
        return entityHeight * sprite.getMaxSpriteScaling();
    }

    /**
     * Returns the movement speed scaling factor.
     * Which is used to scale the base movement speed.
     * @return the movement speed scaling factor.
     */
    public double getMovementSpeedScalingFactor(){
        return movementSpeedScalingFactor;
    }

    /**
     * Returns whether or not the entity is consumable.
     * @return <code>true</code> if and only if the entity is consumable, otherwise <code>false</code>.
     */
    public boolean isConsumable(){
        return consumable;
    }

    /**
     * Returns the score scaling factor.
     * Which is used to scale the base score received from consuming the entity.
     * @return the score scaling factor.
     */
    public double getScoreScalingFactor(){
        return scoreScalingFactor;
    }
}
