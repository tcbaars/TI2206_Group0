package sprites;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public interface Sprite {
    public double getSpriteX();
    public double getSpriteY();
    public void setSpriteX(double x);
    public void setSpriteY(double y);
    public void translateSpriteX(double dX);
    public void translateSpriteY(double dY);
    public double getSpriteWidth();
    public double getSpriteHeight();
    public void incrementSprite(double incrementValue);
    public void decrementSprite(double decrementValue);
    public Ellipse2D getSpriteBoundingBox();
    public double getSpriteScalingFactor();
    public void update();
    public void flipHorizontally();
    public boolean isFlippedHorizontally();
    public void drawSpriteToScreen(Graphics2D screen);
    public boolean isSpriteFacingLeft();
}
