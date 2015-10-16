package sprites;

import enumerations.GameSprites;
import tools.entitytools.Generator;

public class EnemySprite extends BaseSprite{

    private double minSpriteWidth;
    private double currentSpriteWidth;
    private double maxSpriteWidth;

    public EnemySprite(GameSprites sprite){
        super(sprite);
        minSpriteWidth = sprite.getMinWidth();
        maxSpriteWidth = sprite.getMaxWidth();
        if (Generator.generateBoolean(75)) {
            currentSpriteWidth = Generator.generateInteger((int)minSpriteWidth, (int)(maxSpriteWidth / 2));
        } else {
            currentSpriteWidth = Generator.generateInteger((int)minSpriteWidth, (int)maxSpriteWidth);
        }
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
}
