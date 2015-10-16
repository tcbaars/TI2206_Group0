package sprites;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public interface Sprite {

    /**
     * Moves the x-coordinate of the top-left corner of the sprite in the specified direction.
     * @param dX the desired direction.
     */
    public void translateSpriteX(double dX);

    /**
     * Moves the y-coordinate of the top-left corner of the sprite in the specified direction.
     * @param dY the desired direction.
     */
    public void translateSpriteY(double dY);

    /**
     * Increases the scale of the sprite's dimensions by the specified value.
     * @param incrementValue the desired size change.
     */
    public void incrementSprite(double incrementValue);
    /**
     * Decreases the scale of the sprite's dimensions by the specified value.
     * @param decrementValue the desired size change.
     */
    public void decrementSprite(double decrementValue);

    /**
     * Returns the ellipse which represents the boundary of the sprite.
     * @return the sprite's bounding box.
     */
    public Ellipse2D getSpriteBoundingBox();

    /**
     * Returns the ratio of the sprite's width to the frame's width.
     * In other terms, the scaling which is applied to the frame dimensions to achieve the desired sprite dimensions.
     * @return sprite width:frame width.
     */
    public double getSpriteScalingFactor();

    /**
     * Updates the sprite.
     */
    public void update();

    /**
     * Draws the sprite to the specified screen.
     * @param screen the screen.
     */
    public void drawSpriteToScreen(Graphics2D screen);

    /**
     * Flips which direction the sprite is facing.
     */
    public void flipHorizontally();

    /**
     * Returns whether or not the direction which the sprite is facing has been flipped.
     * @return <code>true</code> if and only if the sprite has been flipped, otherwise <code>false</code>.
     */
    public boolean isFlippedHorizontally();

    /**
     * Returns whether or not the sprite contained in the sprite sheet is facing left.
     * @return <code>true</code> if and only if the sprite in the sprite sheet is facing left, otherwise <code>false</code>.
     */
    public boolean isSpriteFacingLeft();
}
