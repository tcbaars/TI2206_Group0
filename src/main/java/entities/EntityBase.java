package entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Iterator;

import settings.DebugSettings;
import settings.valuesettings.BaseMovementSpeedSettings;
import settings.valuesettings.ScaleIncrementSettings;
import settings.valuesettings.ScoreIncrementSettings;
import sprites.Sprite;
import util.Geo2D;

/**
 * The EntityBase class represents the default entity.
 */
public abstract class EntityBase implements Entity{

    private boolean isAlive;

    /**
     * Initialises the default entity.
     */
    public EntityBase(){
        isAlive = true;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void kill() {
        isAlive = false;
    }

    /**
     * Returns the in-game entity's sprite information.
     * @return the entity's sprite.
     */
    abstract public Sprite getSprite();

    /**
     * Returns all the entities attached to the entity.
     * Returns <code>null</code> if the entity has no sub-entities.
     * @return the sub-entities.
     */
    abstract protected ArrayList<Entity> getSubEntities();

    public void update() {
        getSprite().update();
    }

    /**
     * Draws all the entities attached to the entity, to the specified screen.
     * @param screen the screen.
     */
    public void drawSubEntities(Graphics2D screen) {
        if (hasSubEntities()){
            Iterator<Entity> subEntites = getSubEntities().iterator();
            while (subEntites.hasNext()) {
                Entity entity = subEntites.next();
                if (entity != null) {
                    entity.drawEntityToScreen(screen);
                }
            }
        }
    }

    public void drawEntityToScreen(Graphics2D screen) {
        if (isAlive()) {
            getSprite().drawSpriteToScreen(screen);
            if(DebugSettings.getInstance().isDebugModeOn()) {
                screen.setPaint(Color.BLACK);
                Shape boundingBox = getEntityBoundingBox();
                screen.draw(boundingBox);
            }
        }
        drawSubEntities(screen);
    }

    public double getEntityX() {
        double centerX = getSprite().getSpriteBoundingBox().getCenterX();
        double x = centerX - (getEntityWidth() / 2);
        return x;
    }

    public double getEntityY() {
        double centerY = getSprite().getSpriteBoundingBox().getCenterY();
        double y = centerY - (getEntityHeight() / 2);
        return y;
    }

    public void translateSpriteX(double dX) {
        getSprite().translateSpriteX(dX);
    }

    public void translateSpriteY(double dY) {
        getSprite().translateSpriteY(dY);
    }

    /**
     * Returns the width of entity as it appears in the sprite sheet.
     * This is different from the width of the in-game entity,
     * as it is not scaled in anyway.
     * @return the width of the entity in the sprite sheet.
     */
    abstract protected double getLocalEntityWidth();

    public double getEntityWidth() {
        return getLocalEntityWidth() * getSprite().getSpriteScalingFactor();
    }

    /**
     * Returns the height of the entity as it appears in the sprite sheet.
     * This is different from the height of the in-game entity,
     * as it is not scaled in anyway.
     * @return the height of the entity in the sprite sheet.
     */
    abstract protected double getLocalEntityHeight();

    public double getEntityHeight() {
        return getLocalEntityHeight() * getSprite().getSpriteScalingFactor();
    }

    public boolean isEntityFacingLeft(){
        if (getSprite().isSpriteFacingLeft()){
            if (getSprite().isFlippedHorizontally()) {
                return false;
            }
            return true;
        } else {
            if (getSprite().isFlippedHorizontally()) {
                return true;
            }
            return false;
        }
    }

    public void flipHorizontally() {
        getSprite().flipHorizontally();
    }

    public Ellipse2D getEntityBoundingBox() {
        return new Ellipse2D.Double((int)getEntityX(), (int)getEntityY(), (int)getEntityWidth(), (int)getEntityHeight());
    }

    public boolean intersects(Entity entity) {
        if (entity != null) {
            Ellipse2D thisBox = getEntityBoundingBox();
            Ellipse2D thatBox = entity.getEntityBoundingBox();
            return Geo2D.intersection(thisBox, thatBox);
        }
        return false;
    }

    public boolean isLargerThan(Entity entity) {
        if (entity != null && getArea() < entity.getArea()) {
            return false;
        }
        return true;
    }

    public void consumedBy(Entity entity) {
        if (entity != null) {
            kill();
        }
    }

    public double getArea(){
        return Math.PI * getEntityWidth() * getEntityHeight();
    }

    /**
     * Returns the score increment scaling factor of the entity.
     * This scales the base score increment by the specified value.
     * @return the scale increment scaling factor.
     */
    abstract public double getScoreScalingFactor();

    public double getScoreIncrement() {
        double baseScoreIncrement = ScoreIncrementSettings.getInstance().getBaseScoreIncrement();
        double scoreScalingFactor = getScoreScalingFactor();
        double areaScaling = getArea();
        return baseScoreIncrement * scoreScalingFactor * areaScaling;
    }

    public double getSizeIncrement() {
        double baseSizeIncrement = ScaleIncrementSettings.getInstance().getBaseScaleIncrement();
        double areaScaling = getArea();
        return baseSizeIncrement * areaScaling;
    }

    /**
     * Returns the movement speed scaling factor of the entity.
     * This scales the base movement speed by the specified value.
     * @return the movement speed scaling factor.
     */
    abstract public double getMovementSpeedScalingFactor();

    public double getMovementSpeed(){
        double baseMovingSpeed = BaseMovementSpeedSettings.getInstance().getBaseMovementSpeed();
        double movementSpeedScalingFactor = getMovementSpeedScalingFactor();
        double areaScaling = getArea() / 10;
        return baseMovingSpeed * movementSpeedScalingFactor * (1/areaScaling);
    }
}
