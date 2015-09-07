package Entities;

import java.awt.Graphics2D;
import java.io.IOException;

import Animations.Animation;
import Enumerations.Direction;
import Handlers.OptionsHandler;

public class Player extends Entity{

	private final int SCORE_INCREMENT = 10;
	private final int SCALE_INCREMENT = 10;
	private int score;
	private int numberFishEaten;

	protected double moveSpeed;

	public Player(){
		super();
		score = 0;
		numberFishEaten = 0;
		x = OptionsHandler.getInstance().getWidth() / 2;
		y = OptionsHandler.getInstance().getHeight() / 2;
		currentScale = 20;
		targetScale = 100;
	}
		
	@Override
	public void update() {
		animation.update();
	}

	@Override
	public void draw(Graphics2D g) {
		if(alive)
		drawFrame(g);
	}
	
	public void moveUp(){
		y = 0;
	}
	
	public void moveDown(){
		y = 50;
	}
	
	public void moveLeft(){
		x = 0;
	}
	
	public void moveRight(){
		x = 150;
	}
	
	public void move(Direction d){
		switch(d){
			case UP:
				moveUp();
				break;
			case DOWN:
				moveDown();
				break;
			case LEFT:
				moveLeft();
				break;
			case RIGHT:
				moveRight();
				break;
		}
	}
	
	@Override
	protected void setupAnimation() {
		animationURL = "/sprites/Player.png";
		numerFrames = 2;
		spriteWidth = 300;
		spriteHeight = 300;
		delay = 10;
	}

	@Override
	public boolean canConsume(Entity food) {
		return isLargerThan(food);
	}
	
	private void increaseScore(Entity food){
		score+= (int)(food.getRatio() * SCORE_INCREMENT);
	}
	
	private void increaseSize(Entity food){
		currentScale+= (int)(food.getRatio() * SCALE_INCREMENT);  
	}
	
	@Override
	public void consume(Entity food) {
		increaseScore(food);
		increaseSize(food);
		food.isConsumed();
	}

	@Override
	public void createBoundingBox() {
		entityWidth = 100;
		entityHeight = 50;
	}


}
