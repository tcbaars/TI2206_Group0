package entities;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

/**
 * The entity class represents the rules an in-game object must have.
 */
public interface Entity {
    /**
     * Updates the entity.
     */
    public void updateEntity();

    /**
     * Draws the current frame adjusted by the scaling factor to the specified 2-dimensional image.
     *
     * @param graphic the 2-dimensional image.
     */
    public void drawEntity(Graphics2D graphic);

    /**
     * Returns whether or not the entity can be consumed.
     * @return <code>true</code> if and only if the entity can be consumed, otherwise <code>false</code>.
     */
    public boolean isConsumable();

    /**
     * Change whether or not the entity can be consumed.
     * @param consumable if the entity can be consumed.
     */
    public void setConsumable(boolean consumable);

    /**
     * Returns whether or not the entity is alive.
     * @return <code>true</code> if and only if the entity is alive, otherwise <code>false</code>.
     */
    public boolean isAlive();

    /**
     * Kills the entity.
     */
    public void kill();

    /**
     * Returns whether or not the entity is visible.
     * @return <code>true</code> if and only if the entity is visible, otherwise <code>false</code>.
     */
    public boolean isVisible();

    /**
     * Set whether or not the entity is visible.
     * @param visible if the entity is visible.
     */
    public void setVisible(boolean visible);

    /**
     * Set whether or not the entity is facing right.
     * @param facingRight if the entity is facing right.
     */
    public void setFacingRight(boolean facingRight);

    /**
     * Returns whether or not the sprite is facing right.
     *
     * @return <code>true</code> if and only if the sprite is facing right, other <code>false</code>.
     */
    public boolean isFacingRight();

    /**
     * Returns the scaling factor.
     * @return the scaling factor.
     */
    public double getScaling();

    /**
     * Returns the top left corner x coordinate.
     * @return top left x coordinate.
     */
    public int getGlobalSpriteX();

    /**
     * Returns the top left corner y coordinate.
     *
     * @return top left y coordinate.
     */
    public int getGlobalSpriteY();

    /**
     * Returns the entity's top left corner x coordinate.
     *
     * @param centreX the entity's centre x coordinate.
     * @return the entity's top left corner x coordinate.
     */
    public int getGlobalEntityX(double centreX);

    /**
     * Returns the entity's top left corner y coordinate.
     *
     * @param centreY the entity's centre y coordinate.
     * @return  the entity's top left corner y coordinate.
     */
    public int getGlobalEntityY(double centreY);

    /**
     * Returns the sprite width adjusted by the current size.
     * @return the global sprite width.
     */
    public int getGlobalSpriteWidth();

    /**
     * Returns the sprite height adjusted by the current size.
     * @return the global sprite height.
     */
    public int getGlobalSpriteHeight();

    /**
     * Returns the entity width adjusted by the current size.
     * @return the global entity width.
     */
    public int getGlobalEntityWidth();

    /**
     * Returns the entity height adjusted by the current size.
     * @return the global entity height.
     */
    public int getGlobalEntityHeight();

    /**
     * Returns the sprite bounding box adjusted by its size.
     *
     * @return global sprite bounding box.
     */
    public Ellipse2D getGlobalSpriteBoundingBox();

    /**
     * Returns the entity bounding box adjusted by its size.
     *
     * @return global entity bounding box.
     */
    public Ellipse2D getGlobalEntityBoundingBox();

    /**
     * Returns whether or not the two entities intersect.
     *
     * @return <code>true</code> if and only if the two entities intersect, otherwise <code>false</code>.
     */
    public boolean intersects(Entity entity);

    /**
     * Returns whether or not this entity is larger than the specified entity.
     *
     * @return <code>true</code> if and only if this entity is larger than the specified entity, otherwise <code>false</code>.
     */
    public boolean isLargerThan(Entity entity);

    /**
     * Handles the collision between this entity and the specified entity.
     *
     * @param entity the specified entity.
     */
    public void handleCollision(Entity entity);

    /**
     * Handles the necessary actions needed to be performed to consume the specified entity.
     *
     * @param food the entity to be eaten.
     */
    public void consume(Entity entity);

    /**
     * Handles the necessary actions needed to be performed when consumed by the specified entity.
     *
     * @param the entity consuming.
     * @return the value of the entity.
     */
    public int consumedBy(Entity entity);
}
