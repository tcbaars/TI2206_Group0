package entities;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import animations.Animation;

/**
 * The entity class represents the rules an in-game object must have.
 */
public abstract class Entity {

    /*
     * Properties of the entity
     */
    private boolean consumable;
    private boolean alive;
    private boolean visible;
    private boolean facingRight;

    private Animation animation;

    /*
     * Sprite dimensions
     */
    protected double spriteWidth;
    protected double spriteHeight;

    /*
     * Coordinates of top left corner of sprite
     */
    protected double topLeftX;
    protected double topLeftY;

    /*
     * Entity dimensions
     */
    protected double entityWidth;
    protected double entityHeight;

    /*
     * Scaling information
     */
    protected double targetScale;
    protected double currentScale;

    /**
     * Creates a new entity.
     */
    public Entity() {
        consumable = true;
        alive = true;
        visible = true;
        facingRight = false;

        initialiseEntity();
        initialiseSprite();
        animation = createAnimation();
    }

    /**
     * Applies the entity specific properties.
     */
    protected abstract void initialiseEntity();

    /**
     * Applies the sprite properties.
     */
    protected abstract void initialiseSprite();

    /**
     * Creates a sequence of images that represent the entity's animation.
     *
     * @return the entity's animation.
     */
    protected abstract Animation createAnimation();

    /**
     * Updates the entity.
     */
    protected abstract void update();

    /**
     * Draws on the specified 2-dimensional image.
     *
     * @param graphic the 2-dimensional image.
     */
    protected abstract void draw(Graphics2D graphic);

    /**
     * Updates the entity.
     */
    public void updateEntity() {
        // If alive
        if (isAlive()) {
            // Then handle the current frame to be displayed
            animation.update();
            update();
        }
    }

    /**
     * Draws the current frame adjusted by the scaling factor to the specified 2-dimensional image.
     *
     * @param graphic the 2-dimensional image.
     */
    public void drawEntity(Graphics2D graphic) {
        if (isAlive()) {
            if (isVisible()) {
                int x = getGlobalSpriteX();
                int w = getGlobalSpriteWidth();
                if (facingRight){
                    x = x + w;
                    w = -w;
                }
                graphic.drawImage(animation.getCurrentFrame(), x, getGlobalSpriteY(),
                        w, getGlobalSpriteHeight(), null);
                draw(graphic);
            }
        }
    }

    /**
     * Returns whether or not the entity can be consumed.
     * @return <code>true</code> if and only if the entity can be consumed, otherwise <code>false</code>.
     */
    public boolean isConsumable() {
        return isAlive() && consumable;
    }

    /**
     * Change whether or not the entity can be consumed.
     * @param consumable if the entity can be consumed.
     */
    public void setConsumable(boolean consumable) {
        this.consumable = consumable;
    }

    /**
     * Returns whether or not the entity is alive.
     * @return <code>true</code> if and only if the entity is alive, otherwise <code>false</code>.
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * Kills the entity.
     */
    public void kill() {
        alive = false;
        visible = false;
    }

    /**
     * Returns whether or not the entity is visible.
     * @return <code>true</code> if and only if the entity is visible, otherwise <code>false</code>.
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * Set whether or not the entity is visible.
     * @param visible if the entity is visible.
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * Set whether or not the entity is facing right.
     * @param facingRight if the entity is facing right.
     */
    public void setFacingRight(boolean facingRight) {
        this.facingRight = facingRight;
    }

    /**
     * Returns whether or not the sprite is facing right.
     *
     * @return <code>true</code> if and only if the sprite is facing right, other <code>false</code>.
     */
    public boolean isFacingRight(){
        return facingRight;
    }

    /**
     * Returns the scaling factor.
     * @return the scaling factor.
     */
    public double getScaling() {
        return currentScale / targetScale;
    }

    /**
     * The top left x coordinate.
     * @return top left x coordinate.
     */
    public int getGlobalSpriteX() {
        return (int)topLeftX;
    }

    public int getGlobalSpriteY() {
        return (int) topLeftY;
    }

    public int getGlobalEntityX(double centreX) {
        return (int) (centreX - (getGlobalEntityWidth() / 2.0));
    }

    public int getGlobalEntityY(double centreY) {
        return (int) (centreY - (getGlobalEntityHeight() / 2.0));
    }

    public int getGlobalSpriteWidth() {
        return (int) (spriteWidth * getScaling());
    }

    public int getGlobalSpriteHeight() {
        return (int) (spriteHeight * getScaling());
    }

    public int getGlobalEntityWidth() {
        return (int) (entityWidth * getScaling());
    }

    public int getGlobalEntityHeight() {
        return (int) (entityHeight * getScaling());
    }

    /**
     * Global bounding box.
     */
    public Rectangle getGlobalSpriteBoundingBox() {
        return new Rectangle(getGlobalSpriteX(), getGlobalSpriteY(), getGlobalSpriteWidth(),
                getGlobalSpriteHeight());
    }

    /**
     * Box.
     */
    public Rectangle getGlobalEntityBoundingBox() {
        Rectangle globalSprite = getGlobalSpriteBoundingBox();
        double centreX = globalSprite.getCenterX();
        double centreY = globalSprite.getCenterY();
        return new Rectangle(getGlobalEntityX(centreX), getGlobalEntityY(centreY), getGlobalEntityWidth(),
                getGlobalEntityHeight());
    }

    /**
     * Intersects.
     */
    public boolean intersects(Entity entity) {
        if (entity != null) {
            Rectangle thisBox = getGlobalEntityBoundingBox();
            Rectangle thatBox = entity.getGlobalEntityBoundingBox();
            return thisBox.intersects(thatBox);
        }
        return false;
    }

    /**
     * Is larger.
     */
    public boolean isLargerThan(Entity entity) {
        if (entity != null) {
            if (getGlobalEntityWidth() < entity.getGlobalEntityWidth()) {
                return false;
            }
            if (getGlobalEntityHeight() < entity.getGlobalEntityHeight()) {
                return false;
            }
        }
        return true;
    }

    /**
     * handle collison.
     */
    public void handleCollision(Entity entity) {
        if (intersects(entity)) {
            if (isLargerThan(entity)) {
                if (entity.isConsumable()) {
                    consume(entity);
                }
            } else if (isConsumable()) {
                entity.consume(this);
            }
        }
    }

    protected abstract void consume(Entity food);

    protected abstract int consumedBy(Entity eater);

}
