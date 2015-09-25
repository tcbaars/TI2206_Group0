package entities;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import animations.Animation;
import handlers.OptionsHandler;
import util.Geo2D;

/**
 * The entity class represents the rules an in-game object must have.
 */
public abstract class EntityBase implements Entity {

    /*
     * The current game settings
     */
    private OptionsHandler _optionsHandler = OptionsHandler.getInstance();

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
    public EntityBase() {
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
        }
        update();
    }

    /**
     * Draws the current frame adjusted by the scaling factor to the specified 2-dimensional image.
     *
     * @param graphic the 2-dimensional image.
     */
    public void drawEntity(Graphics2D graphic) {
        if (isAlive() && isVisible()) {
            int x = getGlobalSpriteX();
            int w = getGlobalSpriteWidth();
            // If the entity is facing right
            if (facingRight){
                // then horizontally flip the sprite
                x = x + w;
                w = -w;
            }
            graphic.drawImage(animation.getCurrentFrame(), x, getGlobalSpriteY(), w, getGlobalSpriteHeight(), null);

            // If in debugging mode
            if (_optionsHandler.getDebug()) {
                // then draw bounding boxes
                Shape boundingBox = getGlobalEntityBoundingBox();
                graphic.draw(boundingBox);
                graphic.drawString(String.valueOf((int) currentScale), (int) boundingBox.getBounds2D().getX(), (int) boundingBox.getBounds2D().getY());
            }
        }
        draw(graphic);
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
     * Returns the top left corner x coordinate.
     * @return top left x coordinate.
     */
    public int getGlobalSpriteX() {
        return (int)topLeftX;
    }

    /**
     * Returns the top left corner y coordinate.
     *
     * @return top left y coordinate.
     */
    public int getGlobalSpriteY() {
        return (int) topLeftY;
    }

    /**
     * Returns the entity's top left corner x coordinate.
     *
     * @param centreX the entity's centre x coordinate.
     * @return the entity's top left corner x coordinate.
     */
    public int getGlobalEntityX(double centreX) {
        return (int) (centreX - (getGlobalEntityWidth() / 2.0));
    }

    /**
     * Returns the entity's top left corner y coordinate.
     *
     * @param centreY the entity's centre y coordinate.
     * @return  the entity's top left corner y coordinate.
     */
    public int getGlobalEntityY(double centreY) {
        return (int) (centreY - (getGlobalEntityHeight() / 2.0));
    }

    /**
     * Returns the sprite width adjusted by the current size.
     * @return the global sprite width.
     */
    public int getGlobalSpriteWidth() {
        return (int) (spriteWidth * getScaling());
    }

    /**
     * Returns the sprite height adjusted by the current size.
     * @return the global sprite height.
     */
    public int getGlobalSpriteHeight() {
        return (int) (spriteHeight * getScaling());
    }

    /**
     * Returns the entity width adjusted by the current size.
     * @return the global entity width.
     */
    public int getGlobalEntityWidth() {
        return (int) (entityWidth * getScaling());
    }

    /**
     * Returns the entity height adjusted by the current size.
     * @return the global entity height.
     */
    public int getGlobalEntityHeight() {
        return (int) (entityHeight * getScaling());
    }

    /**
     * Returns the sprite bounding box adjusted by its size.
     *
     * @return global sprite bounding box.
     */
    public Ellipse2D getGlobalSpriteBoundingBox() {
        return new Ellipse2D.Double(getGlobalSpriteX(), getGlobalSpriteY(), getGlobalSpriteWidth(),
                getGlobalSpriteHeight());
    }

    /**
     * Returns the entity bounding box adjusted by its size.
     *
     * @return global entity bounding box.
     */
    public Ellipse2D getGlobalEntityBoundingBox() {
        Ellipse2D globalSprite = getGlobalSpriteBoundingBox();
        double centreX = globalSprite.getCenterX();
        double centreY = globalSprite.getCenterY();
        return new Ellipse2D.Double(getGlobalEntityX(centreX), getGlobalEntityY(centreY), getGlobalEntityWidth(),
                getGlobalEntityHeight());
    }

    /**
     * Returns whether or not the two entities intersect.
     *
     * @return <code>true</code> if and only if the two entities intersect, otherwise <code>false</code>.
     */
    public boolean intersects(Entity entity) {
        if (entity != null) {
            Ellipse2D thisBox = getGlobalEntityBoundingBox();
            Ellipse2D thatBox = entity.getGlobalEntityBoundingBox();
            return Geo2D.intersection(thisBox, thatBox);
        }
        return false;
    }

    /**
     * Returns whether or not this entity is larger than the specified entity.
     *
     * @return <code>true</code> if and only if this entity is larger than the specified entity, otherwise <code>false</code>.
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
     * Handles the collision between this entity and the specified entity.
     *
     * @param entity the specified entity.
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

    /**
     * Handles the necessary actions needed to be performed to consume the specified entity.
     *
     * @param food the entity to be eaten.
     */
    public abstract void consume(Entity food);

    /**
     * Handles the necessary actions needed to be performed when consumed by the specified entity.
     *
     * @param the entity consuming.
     * @return the value of the entity.
     */
    public abstract int consumedBy(Entity eater);

}
