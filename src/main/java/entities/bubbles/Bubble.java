package entities.bubbles;

import java.util.ArrayList;

import entities.Entity;
import entities.EntityBase;
import enumerations.GameEntities;
import enumerations.GameSounds;
import sprites.BubbleSprite;
import sprites.Sprite;
import tools.Timer;
import tools.entitytools.Generator;
import tools.resourcetools.SoundLoader;
import tools.resourcetools.SoundPlayer;

public class Bubble extends EntityBase{

    private double localEntityWidth;
    private double localEntityHeight;
    private BubbleSprite sprite;
    private boolean consumable;
    private double scoreIncrementScalingFactor;
    private double movementSpeedScalingFactor;
    private double dX;
    private double dY;
    private double decrementValue;
    private Timer delayScale;
    private Timer delayX;

    public Bubble(Entity entity){
        super();
        SoundLoader.getInstance().loadSound(GameSounds.BUBBLE_POP);
        localEntityWidth = GameEntities.BUBBLE.getEntityWidth();
        localEntityHeight  = GameEntities.BUBBLE.getEntityHeight();
        sprite = new BubbleSprite(GameEntities.BUBBLE.getSprite());
        double centreX = entity.getEntityBoundingBox().getCenterX();
        double centreY = entity.getEntityBoundingBox().getCenterY();
        double x;
        if (entity.isEntityFacingLeft()) {
            x = centreX - (entity.getEntityWidth() / 2);
        } else {
            x = centreX + (entity.getEntityWidth() / 2);
        }
        sprite.setSpriteX(x);
        sprite.setSpriteY(centreY);
        consumable = GameEntities.BUBBLE.isConsumable();
        scoreIncrementScalingFactor = GameEntities.BUBBLE.getScoreScalingFactor();
        movementSpeedScalingFactor = GameEntities.BUBBLE.getMovementSpeedScalingFactor();
        decrementValue = 1;
        delayScale = new Timer(5);
        int randDelayX = Generator.generateInteger(300);
        delayX = new Timer(randDelayX);
        dY = -2;
        dX = 0.5;
        if (Generator.generateBoolean()) {
            dX = -0.5;
        }
    }
    public boolean isConsumable() {
        return consumable;
    }

    public boolean hasSubEntities() {
        return false;
    }

    public ArrayList<Entity> getSubEntities() {
        return null;
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }

    @Override
    public double getScoreScalingFactor() {
        return scoreIncrementScalingFactor;
    }

    @Override
    public double getMovementSpeedScalingFactor() {
        return movementSpeedScalingFactor;
    }

    @Override
    public void update() {
        delayScale.tick();
        delayX.tick();
        if (delayScale.hasCompleted()) {
            delayScale.reset();
            sprite.decrementSprite(decrementValue);
            if (sprite.hasPopped()) {
                kill();
                return;
            }
        }
        if (delayX.hasCompleted()) {
            delayX.reset();
            // Change direction
            dX = (-1) * dX;
        }
        // Update location
        sprite.translateSpriteX(dX);
        sprite.translateSpriteY(dY);
        if (getEntityY() < (0 - getEntityHeight())){
            kill();
            return;
        }
    }
    @Override
    public void kill(){
        if (isAlive()) {
            SoundPlayer.getInstance().playSound(GameSounds.BUBBLE_POP);
            super.kill();
        }
    }
    @Override
    protected double getLocalEntityWidth() {
        return localEntityWidth;
    }
    @Override
    protected double getLocalEntityHeight() {
        return localEntityHeight;
    }
    public boolean consume(Entity entity) {
        return false;
    }

}
