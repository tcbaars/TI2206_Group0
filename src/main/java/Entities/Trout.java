package Entities;

import java.awt.Graphics2D;
import java.util.Random;

import Enumerations.Direction;
import Handlers.OptionsHandler;

public class Trout extends Enemy{
	
	private Random generator = new Random(System.currentTimeMillis());
	private Direction moving;
	private int minScale;
	
	public Trout(){
		super();
		minScale = 10;
		moveSpeed = 10;
		randomlyGenerate();
	}

	private void randomlyGenerate(){

		double rand = generator.nextInt(2);

		// random side of screen
		if(rand < 1){
			// Right side of screen

			x = OptionsHandler.getInstance().getWidth();
			moving = Direction.LEFT;
			setFacingRight(false);
		} else {
			// Left side

			x = 0 - getGlobalSpriteWidth();
			moving = Direction.RIGHT;
			setFacingRight(true);
		}
		
		
		
		// random depth
		rand = generator.nextInt(OptionsHandler.getInstance().getHeight() - getGlobalSpriteHeight());
		y = rand;

		// random size
		rand = generator.nextInt((int)getTargetScale() - minScale);
		setCurrentScale((int)rand+minScale);
	}
	
	@Override
	protected void setupAnimation() {
		animationURL = "/sprites/Trout.png";
		numerFrames = 2;
		spriteWidth = 300;
		spriteHeight = 300;
		delay = 10;
	}

	@Override
	public void update() {	
		move(moving);
		animation.update();
	}

	@Override
	public void draw(Graphics2D g) {
		drawFrame(g);		
	}
	
	@Override
	public void consume(Entity food) {
		food.isConsumed();
	}

	@Override
	public boolean canConsume(Entity food) {
		return isLargerThan(food);
	}
	
	@Override
	public void createBoundingBox() {
		entityWidth = 100;
		entityHeight = 50;
	}

}
