package entities;

import java.awt.Graphics2D;

import animations.Animation;
import enumerations.Direction;
import handlers.OptionsHandler;

public class Player extends Entity {

    private final int SCOREINCREMENT = 10;
    private final int SCALEINCREMENT = 10;
   
    private final double MOVESPEED = 10;
    
    private final double ENTITYWIDTH = 100;
    private final double ENTITYHEIGHT = 50;
    
    private final double SPRITEWIDTH = 300;
    private final double SPRITEHEIGHT = 300;
    private final String ANIMATIONKEY = "Player";
    private final String ANIMATIONURL = "/sprites/Player.png";
    private final int NUMBERFRAMES = 2;
    private final int ANIMATIONDELAY = 10;
    
    private final double STARTSCALE = 20;
    private final double TARGETSCALE = 100;
   
    private int currentScore;
    private int numberFishEaten;

    public Player() {
        super();
        currentScore = 0;
        numberFishEaten = 0;
    }
    
    @Override
    protected void initialiseEntity(){
    	entityWidth = ENTITYWIDTH;
        entityHeight = ENTITYHEIGHT;
    }
    
    @Override
    protected void initialiseSprite(){
    	x = OptionsHandler.getInstance().getWidth() / 2;
        y = OptionsHandler.getInstance().getHeight() / 2;
    	spriteWidth = SPRITEWIDTH;
    	spriteHeight = SPRITEHEIGHT;
        currentScale = STARTSCALE;
        targetScale = TARGETSCALE;
    }
    
    @Override
    protected Animation createAnimation() {
        return Animation.createAnimation(ANIMATIONKEY, ANIMATIONURL, NUMBERFRAMES, (int)SPRITEWIDTH, (int)SPRITEHEIGHT, ANIMATIONDELAY);
    }

    @Override
    protected void update() {}

    @Override
    protected void draw(Graphics2D g) {}
    
    public void moveUp() {
        y = 0;
    }

    public void moveDown() {
        y = 50;
    }

    public void moveLeft() {
        x = 0;
    }

    public void moveRight() {
        x = 150;
    }

    public void move(Direction d) {
        switch (d) {
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

    /**
     * Assuming not null and consumable
     */
	@Override
	protected void consume(Entity food) {
		numberFishEaten++;
		currentScore+=food.consumedBy(this);
	}

	@Override
	protected int consumedBy(Entity eater) {
		kill();
		return 0;
	}
	
	public boolean isFull(){
		return currentScale >= targetScale;
	}
	
	public int getScore(){
		return currentScore;
	}



}
