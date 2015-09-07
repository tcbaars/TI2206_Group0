package Entities;

import java.awt.Graphics2D;
import java.util.ArrayList;

import Enumerations.Direction;
import Handlers.OptionsHandler;

public abstract class Enemy extends Entity{
	
	protected double moveSpeed;
	
	protected boolean bubblesBlown;
	protected int maxBubbles;
	protected int currentBubbles;
	protected Bubble[] bubbles;
	
	public Enemy(){
		super();
		moveSpeed = 0;
		consumable = false;
		bubblesBlown = false;
		maxBubbles = 3;
		currentBubbles = 0;
		bubbles = new Bubble[maxBubbles];
	}
	
	private void moveLeft(){
		x = x - moveSpeed;
		// If off-screen kill it
		if(x < (0 - getGlobalSpriteWidth())){
			kill();
		}
	}
	
	private void moveRight(){
		x = x + moveSpeed;
		// If off-screen kill it
		if(x > OptionsHandler.getInstance().getWidth()){
			kill();
		}
	}
	
	public void move(Direction direction){
		switch(direction){
			case LEFT:	
				moveLeft();
				break;
			case RIGHT:
				moveRight();
				break;
		}
	}
	
	public boolean hasBubbles(){
		return false;
	}
	

}
