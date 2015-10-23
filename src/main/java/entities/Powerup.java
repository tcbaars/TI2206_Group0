package entities;

import enumerations.GameEntities;
import settings.ScreenSettings;
import sprites.PowerupSprite;
import sprites.Sprite;
import tools.entitytools.Generator;

import java.util.ArrayList;

/**
 * Created by Adriaan on 23-10-2015.
 */
public class Powerup extends EntityBase {

    private Sprite sprite;
    private boolean consumable;
    private double scoreIncrementScalingFactor;
    private double movementSpeedScalingFactor;
    private double localEntityWidth;
    private double localEntityHeight;

    public Powerup(GameEntities entity){
        sprite = new PowerupSprite(entity.getSprite());
        localEntityWidth = entity.getEntityWidth();
        localEntityHeight = entity.getEntityHeight();
        scoreIncrementScalingFactor = entity.getScoreScalingFactor();
        movementSpeedScalingFactor = entity.getMovementSpeedScalingFactor();
        spawn();
        consumable = entity.isConsumable();
    }

    protected void spawn(){
        double screenHeight = ScreenSettings.getInstance().getHeight();
        double height = getEntityHeight();
        int y = Generator.generateInteger((int)(screenHeight - (height + 10)));
        sprite.translateSpriteY(y);
        double width = getEntityWidth();
        double x = 0 - width;
        sprite.translateSpriteX(x);
    }

    public boolean isConsumable() {
        return consumable;
    }

    public double getScoreScalingFactor() {
        return scoreIncrementScalingFactor;
    }

    public double getMovementSpeedScalingFactor() {
        return movementSpeedScalingFactor;
    }

    public boolean consume(Entity entity) {
        return false;
    }

    protected double getLocalEntityWidth() {
        return localEntityWidth;
    }

    protected double getLocalEntityHeight() {
        return localEntityHeight;
    }

    public ArrayList<Entity> getSubEntities() {
        return null;
    }

    public boolean hasSubEntities() {
        return false;
    }

    public Sprite getSprite() {
        return sprite;
    }
}
