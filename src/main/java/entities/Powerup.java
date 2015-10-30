package entities;

import enumerations.GameEntities;
import settings.PowerupSettings;
import settings.ScreenSettings;
import sprites.PowerupSprite;
import sprites.Sprite;
import tools.Timer;
import tools.entitytools.Generator;
import util.Logger;

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
    private Timer powerupDelay;
    private Timer despawnDelay;

    public Powerup(GameEntities entity){
        sprite = new PowerupSprite(entity.getSprite());
        localEntityWidth = entity.getEntityWidth();
        localEntityHeight = entity.getEntityHeight();
        scoreIncrementScalingFactor = entity.getScoreScalingFactor();
        movementSpeedScalingFactor = entity.getMovementSpeedScalingFactor();
        spawn();
        consumable = entity.isConsumable();
        powerupDelay = new Timer(PowerupSettings.getInstance().getPowerupRate());
        despawnDelay = new Timer(PowerupSettings.getInstance().getDespawnRate());
    }

    public void update() {
        getSprite().update();

        if (PowerupSettings.getInstance().powerupIsActive()) {
            powerupDelay.tick();
            if (powerupDelay.hasCompleted()) {
                PowerupSettings.getInstance().togglePowerup();
                powerupDelay.reset();
            }
        }

        despawnDelay.tick();
        if (despawnDelay.hasCompleted()) {
            kill();
        }
    }

    protected void spawn(){
        double screenHeight = ScreenSettings.getInstance().getHeight();
        double height = getEntityHeight();
        double screenWidth = ScreenSettings.getInstance().getWidth();
        double width = getEntityWidth();
        int y = Generator.generateInteger((int)(screenHeight - (height + 10)));
        int x = Generator.generateInteger((int)(screenWidth - (width + 10)));
        sprite.translateSpriteY(y);
        sprite.translateSpriteX(x);
        Logger.info("Powerup Spawned!");
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
