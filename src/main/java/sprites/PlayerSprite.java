package sprites;

import enumerations.GameSprites;

public class PlayerSprite extends BaseSprite{

    private double minSpriteWidth;
    private double currentSpriteWidth;
    private double maxSpriteWidth;

    public PlayerSprite(GameSprites sprite){
        super(sprite);
        minSpriteWidth = sprite.getMinWidth();
        currentSpriteWidth = minSpriteWidth;
        maxSpriteWidth = sprite.getMaxWidth();
    }

    public double getSpriteWidth() {
        return currentSpriteWidth;
    }

    public double getSpriteHeight() {
        return getFrameHeight() * getSpriteScalingFactor();
    }

    public void incrementSprite(double incrementValue) {
        currentSpriteWidth += incrementValue;
        if (currentSpriteWidth > maxSpriteWidth) {
            currentSpriteWidth = maxSpriteWidth;
        }
    }


    public void decrementSprite(double decrementValue) {
        currentSpriteWidth -= decrementValue;
        if (currentSpriteWidth < minSpriteWidth) {
            currentSpriteWidth = minSpriteWidth;
        }
    }
    public boolean isEmpty(){
        return (currentSpriteWidth <= minSpriteWidth);
    }
    public boolean isFull(){
        return (currentSpriteWidth >= maxSpriteWidth);
    }
}
