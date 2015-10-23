package sprites;

import enumerations.GameSprites;
import tools.entitytools.Generator;

/**
 * Created by Adriaan on 23-10-2015.
 */
public class PowerupSprite extends BaseSprite {

    private double minSpriteWidth;
    private double currentSpriteWidth;
    private double maxSpriteWidth;

    public PowerupSprite(GameSprites sprite){
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

    public void incrementSprite(double incrementValue) {}

    public void decrementSprite(double decrementValue) {}
}
