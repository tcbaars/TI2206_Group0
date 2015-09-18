package entities;

import java.util.Random;

import enumerations.Direction;
import handlers.OptionsHandler;

/**
 * The Enemy class outlines the basic attributes an enemy attribute must have.
 */
public abstract class Enemy extends Entity {

    /*
     * Randomises the enemy's attributes
     */
    protected Random generator;

    /*
     * Enemy properties
     */
    protected double moveSpeed;
    protected Bubbles bubbles;

    /**
     * Creates a new enemy entity.
     */
    public Enemy() {
        super();
    }

    /**
     * Spawn the enemy on the left side of the playing field.
     */
    protected void spawnLeft() {
        topLeftX = 0 - getGlobalEntityWidth();
        setDirection(Direction.RIGHT);
        setFacingRight(true);
    }

    /**
     * Spawn the enemy on the right side of the playing field.
     */
    protected void spawnRight() {
        topLeftX = OptionsHandler.getInstance().getWidth() + getGlobalEntityWidth();
        setDirection(Direction.LEFT);
        setFacingRight(false);
    }

    /**
     * Spawn the enemy on either side of the playing field.
     */
    protected void setRandomSide() {
        int side = generator.nextInt(2);
        if (side == 0) {
            spawnLeft();
        } else {
            spawnRight();
        }
    }

    /**
     * Spawn the enemy at a random depth within the playing field.
     */
    protected void setRandomDepth() {
        topLeftY = generator.nextInt((OptionsHandler.getInstance().getHeight() - getGlobalEntityHeight()));
    }

    /**
     * Randomly assign the enemy a random size, within the specified range
     *
     * @param minScale the minimum size.
     * @param maxScale the maximum size.
     */
    protected void setRandomScale(double minScale, double maxScale) {
        int rand = generator.nextInt((int) (maxScale - minScale));
        currentScale = rand + minScale;
    }

    /**
     * Return the enemy's move speed proportional to its current size.
     *
     * @return the move speed adjusted by the current size.
     */
    private double getGlobalMoveSpeed(){
        return moveSpeed / getScaling();
    }

    /**
     * Moves the enemy to the left of the playing field.
     */
    private void moveLeft() {
        topLeftX = topLeftX - getGlobalMoveSpeed();
        // If off-screen kill it
        if (topLeftX < (0 - getGlobalSpriteWidth())) {
            kill();
        }
    }

    /**
     * Moves the enemy to the right of the playing field.
     */
    private void moveRight() {
        topLeftX = topLeftX + getGlobalMoveSpeed();
        // If off-screen kill it
        if (topLeftX > OptionsHandler.getInstance().getWidth()) {
            kill();
        }
    }

    /**
     * Move the enemy in the specified direction.
     *
     * @param direction the desired direction.
     */
    public void move(Direction direction) {
        switch (direction) {
        case LEFT:
            moveLeft();
            break;
        case RIGHT:
            moveRight();
            break;
        default:
        }
    }

    /**
     * Returns the value of the enemy proportional to its current size.
     *
     * @return the value adjusted by the current size.
     */
    public int calculateValue() {
        return (int) (getScaling() * getBaseValue());
    }

    /**
     * Sets the direction of movement.
     *
     * @param direction the desired direction.
     */
    public abstract void setDirection(Direction direction);

    /**
     * Returns the base value of the enemy.
     *
     * @return the base value.
     */
    public abstract double getBaseValue();

    /**
     * Returns whether or not the enemy still has bubbles associated with it.
     *
     * @return <code>true</code> if and only if the enemy still has bubbles, otherwise <code>false</code>.
     */
    public boolean hasBubbles() {
        if(bubbles != null){
            return bubbles.hasBubbles();
        }
        return false;
    }

}