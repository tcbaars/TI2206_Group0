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

    public boolean hasPopped(){
        return currentSpriteWidth <= minSpriteWidth;
    }
}
