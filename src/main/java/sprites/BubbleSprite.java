package sprites;

import enumerations.GameSprites;

public class BubbleSprite extends BaseSprite{

    private double minSpriteWidth;
    private double currentSpriteWidth;
    private double maxSpriteWidth;

    public BubbleSprite(GameSprites sprite){
        super(sprite);
        minSpriteWidth = sprite.getMinWidth();
        maxSpriteWidth = sprite.getMaxWidth();
        currentSpriteWidth = maxSpriteWidth;
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
            decrementSprite((-1) * incrementValue);
        }
    }

    public void decrementSprite(double decrementValue) {
        if (decrementValue >= 0) {
            currentSpriteWidth -= decrementValue;
            if (currentSpriteWidth < minSpriteWidth) {
                currentSpriteWidth = minSpriteWidth;
            }
        } else {
            incrementSprite((-1) * decrementValue);
        }
    }

    public boolean hasPopped(){
        return currentSpriteWidth <= minSpriteWidth;
    }
}
