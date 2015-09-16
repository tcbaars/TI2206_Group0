package entities;

import java.util.Random;

import enumerations.Direction;
import handlers.OptionsHandler;

public abstract class Enemy extends Entity {

    protected Random generator;
    protected double moveSpeed;

    //private boolean bubblesBlown;
    //private int maxBubbles;
    //private int currentBubbles;
    //private Bubble[] bubbles;

    /**
     * enemy.
     */
    public Enemy() {
        super();
        //maxBubbles = 3;
        //bubblesBlown = false;
        //currentBubbles = 0;
        //bubbles = new Bubble[maxBubbles];
    }

    protected void spawnLeft() {
        topLeftX = 0 - getGlobalEntityWidth();
        setDirection(Direction.RIGHT);
        setFacingRight(true);
    }

    protected void spawnRight() {
        topLeftX = OptionsHandler.getInstance().getWidth() + getGlobalEntityWidth();
        setDirection(Direction.LEFT);
        setFacingRight(false);
    }

    protected void setRandomSide() {
        int side = generator.nextInt(2);
        if (side == 0) {
            spawnLeft();
        } else {
            spawnRight();
        }
    }

    protected void setRandomDepth() {
        topLeftY = generator.nextInt((OptionsHandler.getInstance().getHeight() - getGlobalEntityHeight()));
    }

    protected void setRandomScale(double minScale, double maxScale) {
        int rand = generator.nextInt((int) (maxScale - minScale));
        currentScale = rand + minScale;
    }

    private double getGlobalMoveSpeed(){
        return moveSpeed / getScaling();
    }

    private void moveLeft() {
        topLeftX = topLeftX - getGlobalMoveSpeed();
        // If off-screen kill it
        if (topLeftX < (0 - getGlobalSpriteWidth())) {
            kill();
        }
    }

    private void moveRight() {
        topLeftX = topLeftX + getGlobalMoveSpeed();
        // If off-screen kill it
        if (topLeftX > OptionsHandler.getInstance().getWidth()) {
            kill();
        }
    }

    /**
     * Move.
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

    public int calculateValue() {
        return (int) (getScaling() * getBaseValue());
    }

    public abstract void setDirection(Direction direction);

    public abstract double getBaseValue();

    public boolean hasBubbles() {
        return false;
    }

}
