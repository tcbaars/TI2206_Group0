package sprites;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

import animations.Animation;
import enumerations.GameSprites;

public abstract class BaseSprite implements Sprite{

    private Animation animation;
    private boolean facingLeft;
    private boolean flippedHorizontally;
    private double spriteX;
    private double spriteY;

    public BaseSprite(GameSprites sprite){
        animation = new Animation(sprite.getGameAnimation());
        facingLeft = animation.isFacingLeft();
        flippedHorizontally = false;
        spriteX = 0;
        spriteY = 0;
    }
    public double getSpriteX(){
        return spriteX;
    }
    public double getSpriteY(){
        return spriteY;
    }
    public void setSpriteX(double x){
        this.spriteX = x;
    }
    public void setSpriteY(double y){
        this.spriteY = y;
    }
    public void translateSpriteX(double dX){
        this.spriteX += dX;
    }
    public void translateSpriteY(double dY){
        this.spriteY += dY;
    }
    public Ellipse2D getSpriteBoundingBox(){
        return new Ellipse2D.Double(spriteX, spriteY, getSpriteWidth(), getSpriteHeight());
    }
    public double getFrameWidth(){
        return animation.getFrameWidth();
    }
    public double getFrameHeight(){
        return animation.getFrameHeight();
    }
    public double getSpriteScalingFactor(){
        return getSpriteWidth() / getFrameWidth();
    }
    public void update(){
        animation.update();
    }
    public void drawSpriteToScreen(Graphics2D screen){
        BufferedImage currentFrame = animation.getCurrentFrame();
        double x = getSpriteX();
        double y = getSpriteY();
        double width = getSpriteWidth();
        if (isFlippedHorizontally()) {
            x += width;
            width = (-1) * width;
        }
        screen.drawImage(currentFrame, (int)x, (int)y, (int)width, (int)getSpriteHeight(), null);
    }
    public boolean isSpriteFacingLeft(){
        return facingLeft;
    }
    public void flipHorizontally(){
        flippedHorizontally = !flippedHorizontally;
    }
    public boolean isFlippedHorizontally(){
        return flippedHorizontally;
    }
}
