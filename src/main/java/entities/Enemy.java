package entities;

import java.util.Random;

import enumerations.Direction;
import handlers.OptionsHandler;

public abstract class Enemy extends Entity {

	protected Random generator;
    protected double moveSpeed;

    private boolean bubblesBlown;
    private int maxBubbles;
    private int currentBubbles;
    private Bubble[] bubbles;

    public Enemy() {
        super();
        bubblesBlown = false;
        maxBubbles = 3;
        currentBubbles = 0;
        bubbles = new Bubble[maxBubbles];
    }
    
    protected void spawnLeft(){
    	x = 0 - getGlobalSpriteWidth();
    	setDirection(Direction.RIGHT);
    	setFacingRight(true);
    }
    
    protected void spawnRight(){
    	x = OptionsHandler.getInstance().getWidth();
    	setDirection(Direction.LEFT);
    	setFacingRight(false);
    }
    
    protected void setRandomSide(){
    	int side = generator.nextInt(2);
    	if(side == 0){
    		spawnLeft();
    	} else {
    		spawnRight();
    	}
    }
    
    protected void setRandomDepth(){
    	y = generator.nextInt((OptionsHandler.getInstance().getHeight() - getGlobalSpriteHeight()));
    }
    
    protected void setRandomScale(double minScale, double targetScale){
    	this.targetScale = targetScale;
    	int rand = generator.nextInt((int)(targetScale - minScale));
    	currentScale = rand + minScale;
    }

    private void moveLeft() {
        x = x - moveSpeed;
        // If off-screen kill it
        if (x < (0 - getGlobalSpriteWidth())) {
            kill();
        }
    }

    private void moveRight() {
        x = x + moveSpeed;
        // If off-screen kill it
        if (x > OptionsHandler.getInstance().getWidth()) {
            kill();
        }
    }

    public void move(Direction direction) {
        switch (direction) {
        case LEFT:
            moveLeft();
            break;
        case RIGHT:
            moveRight();
            break;
        }
    }
    
    public int calculateValue(){
    	return (int)(getScaling() * getBaseValue());
    }
    public abstract void setDirection(Direction direction);
    public abstract double getBaseValue();
    
    public boolean hasBubbles() {
        return false;
    }

}
