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
}
