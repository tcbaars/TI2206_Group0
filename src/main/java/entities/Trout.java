package entities;

import java.awt.Graphics2D;
import java.util.Random;

import animations.Animation;
import enumerations.Direction;


public class Trout extends Enemy {

	private final double MOVESPEED = 10;
	private final double BASEVALUE = 10;
    
    private final double ENTITYWIDTH = 100;
    private final double ENTITYHEIGHT = 50;
    
    private final double SPRITEWIDTH = 300;
    private final double SPRITEHEIGHT = 300;
    private final String ANIMATIONKEY = "Trout";
    private final String ANIMATIONURL = "/sprites/Trout.png";
    private final int NUMBERFRAMES = 2;
    private final int ANIMATIONDELAY = 10;
    
    private final double MINSCALE = 20;
    private final double MAXSCALE = 100;
    
    private Direction movingDirection;
    
    public Trout() {
        super();
    }

	@Override
	protected void initialiseEntity() {
		entityWidth = ENTITYWIDTH;
		entityHeight = ENTITYHEIGHT;
		moveSpeed = MOVESPEED;
	}

	@Override
	protected void initialiseSprite() {
		generator = new Random(System.currentTimeMillis());
		spriteWidth = SPRITEWIDTH;
    	spriteHeight = SPRITEHEIGHT;
    	setRandomSide();
    	setRandomDepth();
    	setRandomScale(MINSCALE, MAXSCALE);
	}

	@Override
	protected Animation createAnimation() {
		return Animation.createAnimation(ANIMATIONKEY, ANIMATIONURL, NUMBERFRAMES, (int)SPRITEWIDTH, (int)SPRITEHEIGHT, ANIMATIONDELAY);
	}
	
	public void setDirection(Direction direction){
		this.movingDirection = direction;
	}

	@Override
	protected void update() {
		move(movingDirection);
	}

	@Override
	protected void draw(Graphics2D g) {}

	
	public double getBaseValue(){
		return BASEVALUE;
	}
	
	@Override
	protected void consume(Entity food) {
		food.consumedBy(this);
	}

	@Override
	protected int consumedBy(Entity eater) {
		kill();
		return calculateValue();
	}
	
	
}
