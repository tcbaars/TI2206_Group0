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


    protected double getSpriteWidth() {
        return currentSpriteWidth;
    }

    public void incrementSprite(double incrementValue) {
        if (incrementValue >= 0) {
            currentSpriteWidth += incrementValue;
            if (currentSpriteWidth > maxSpriteWidth) {
                currentSpriteWidth = maxSpriteWidth;
            }
        } else {
            decrementSprite(incrementValue);
        }
    }

    public void decrementSprite(double decrementValue) {
        if (decrementValue <= 0) {
            currentSpriteWidth -= decrementValue;
            if (currentSpriteWidth < minSpriteWidth) {
                currentSpriteWidth = minSpriteWidth;
            }
        } else {
            incrementSprite(decrementValue);
        }
    }

    public boolean isEmpty(){
        return (currentSpriteWidth <= minSpriteWidth);
    }
    public boolean isFull(){
        return (currentSpriteWidth >= maxSpriteWidth);
    }
}
