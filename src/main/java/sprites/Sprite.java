package sprites;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

/**
 * Interface for a generic Sprite
 */
public interface Sprite {

    /**
     * Moves the x-coordinate of the top-left corner of the sprite in the specified direction.
     * @param dX the desired direction.
     */
    void translateSpriteX(double dX);

    /**
     * Moves the y-coordinate of the top-left corner of the sprite in the specified direction.
     * @param dY the desired direction.
     */
    void translateSpriteY(double dY);

    /**
     * Increases the scale of the sprite's dimensions by the specified value.
     * @param incrementValue the desired size change.
     */
    void incrementSprite(double incrementValue);
    /**
     * Decreases the scale of the sprite's dimensions by the specified value.
     * @param decrementValue the desired size change.
     */
    void decrementSprite(double decrementValue);

    /**
     * Returns the ellipse which represents the boundary of the sprite.
     * @return the sprite's bounding box.
     */
    Ellipse2D getSpriteBoundingBox();

    /**
     * Returns the ratio of the sprite's width to the frame's width.
     * In other terms, the scaling which is applied to the frame dimensions to achieve the desired sprite dimensions.
     * @return sprite width:frame width.
     */
    double getSpriteScalingFactor();

    /**
     * Updates the sprite.
     */
    void update();

    /**
     * Draws the sprite to the specified screen.
     * @param screen the screen.
     */
    void drawSpriteToScreen(Graphics2D screen);

    /**
     * Flips which direction the sprite is facing.
     */
    void flipHorizontally();

    /**
     * Returns whether or not the direction which the sprite is facing has been flipped.
     * @return <code>true</code> if and only if the sprite has been flipped, otherwise <code>false</code>.
     */
    boolean isFlippedHorizontally();

    /**
     * Returns whether or not the sprite contained in the sprite sheet is facing left.
     * @return <code>true</code> if and only if the sprite in the sprite sheet is facing left, otherwise <code>false</code>.
     */
    boolean isSpriteFacingLeft();
}
