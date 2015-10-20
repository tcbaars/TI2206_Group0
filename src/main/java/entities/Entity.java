package entities;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

/**
 * The Entity interface represents constraints that an in-game entity must follow.
 */
public interface Entity {

    /**
     * Returns whether or not the entity is alive.
     * @return <code>true</code> if and only if the entity is alive, otherwise <code>false</code>.
     */
    boolean isAlive();

    /**
     * Kills the entity.
     */
    void kill();

    /**
     * Returns whether or not the entity is consumable.
     * @return <code>true</code> if and only if the entity is consumable, otherwise <code>false</code>.
     */
    boolean isConsumable();

    /**
     * Updates the entity and its sub-entities.
     */
    void update();

    /**
     * Draws the entity to the specified screen.
     * @param screen the screen.
     */
    void drawEntityToScreen(Graphics2D screen);

    /**
     * Returns the X-coordinate of the top-left corner of the entity.
     * @return the X-coordinate of the entity.
     */
    double getEntityX();

    /**
     * Returns the Y-coordinate of the top-left corner of the entity.
     * @return the Y-coordinate of the entity.
     */
    double getEntityY();

    /**
     * Moves the sprite's X-coordinate in the specified direction.
     * @param dX the desired direction.
     */
    void translateSpriteX(double dX);

    /**
     * Moves the sprite's Y-coordinate in the specified direction.
     * @param dY the desired direction.
     */
    void translateSpriteY(double dY);

    /**
     * Returns the width of the in-game entity.
     * @return the width of the entity.
     */
    double getEntityWidth();

    /**
     * Returns the height of the in-game entity.
     * @return the height of the entity.
     */
    double getEntityHeight();

    /**
     * Returns whether or not the in-game entity is facing left.
     * @return <code>true</code> if and only if the entity is facing left, otherwise <code>false</code>.
     */
    boolean isEntityFacingLeft();

    /**
     * Flips the direction the in-game entity is facing.
     */
    void flipHorizontally();

    /**
     * Returns an ellipse which represents the outer boundary of the in-game entity.
     * @return the entity's bounding box.
     */
    Ellipse2D getEntityBoundingBox();

    /**
     * Returns the elliptical area of the in-game entity.
     * @return the size of the entity.
     */
    double getArea();

    /**
     * Returns whether or not the entity intersects with the specified entity.
     * @param entity the specified entity.
     * @return <code>true</code> if and only if the entities are intersecting, otherwise <code>false</code>.
     */
    boolean intersects(Entity entity);

    /**
     * Returns whether or not the entity is larger than the specified entity.
     * @param entity the specified entity.
     * @return <code>true</code> if and only if the entity is larger than the specified entity, otherwise <code>false</code>.
     */
    boolean isLargerThan(Entity entity);

    /**
     * Consumes the specified entity.
     * Only if:
     * - the entity is alive.
     * - the specified entity is not null.
     * - the specified entity is alive.
     * - the specified entity is consumable.
     * - the two entities are intersecting.
     * - the entity is larger than the specified entity.
     * @param entity the specified entity.
     * @return <code>true</code> if and only if the entity consumed the specified entity, otherwise <code>false</code>.
     */
    boolean consume(Entity entity);

    /**
     * Notifies the entity that it has been consumed by the specified entity.
     * @param entity the specified entity.
     */
    void consumedBy(Entity entity);

    /**
     * Returns the increment by which the score should be increased by, upon consuming the entity.
     * @return the score increment.
     */
    double getScoreIncrement();

    /**
     * Returns the increment by which the size should be increased by, upon consuming the entity.
     * @return the size increment.
     */
    double getSizeIncrement();

    /**
     * Returns whether or not the entity has sub-entities which are still alive.
     * @return <code>true</code> if and only if the entity has sub-entities which are alive, otherwise <code>false</code>.
     */
    boolean hasSubEntities();
}
