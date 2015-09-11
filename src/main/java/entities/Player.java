package entities;

import java.awt.Graphics2D;

import animations.Animation;
import enumerations.Direction;
import handlers.OptionsHandler;

public class Player extends Entity {

    private OptionsHandler _optionsHandler = OptionsHandler.getInstance();

    private final static double movespeed = 75;

    private final static String animationkey = "Player";
    private final static String animationurl = "/sprites/Player.png";
    private final static int numberframes = 2;
    private final static int animationdelay = 10;

    private int currentScore;
    private int numberFishEaten;

    /**
     * Player.
     */
    public Player() {
        super();
        currentScore = 0;
        numberFishEaten = 0;
    }

    @Override
    protected void initialiseEntity() {
        entityWidth = 100;
        entityHeight = 50;
    }

    @Override
    protected void initialiseSprite() {
        topLeftX = OptionsHandler.getInstance().getWidth() / 2.0;
        topLeftY = OptionsHandler.getInstance().getHeight() / 2.0;
        spriteWidth = 300;
        spriteHeight = 300;
        currentScale = 20;
        targetScale = 100;
    }

    @Override
    protected Animation createAnimation() {
        return Animation.createAnimation(animationkey, animationurl, numberframes, (int) spriteWidth,
                (int) spriteHeight, animationdelay);
    }

    @Override
    protected void update() {
    }

    @Override
    protected void draw(Graphics2D graphic) {
    }

    public void moveUp() {
        if ((topLeftY - movespeed) > 0) {
            topLeftY -= movespeed;
        }
    }

    public void moveDown() {
        if ((topLeftY + movespeed) < _optionsHandler.getHeight()) {
            topLeftY += movespeed;
        }
    }

    public void moveLeft() {
        if ((topLeftX - movespeed) > 0) {
            topLeftX -= movespeed;
        }
    }

    public void moveRight() {
        if ((topLeftX + movespeed) < _optionsHandler.getWidth()) {
            topLeftX += movespeed;
        }
    }

    /**
     * Move.
     */
    public void move(Direction direction) {
        switch (direction) {
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
        default:
        }
    }

    /**
     * Assuming not null and consumable.
     */
    @Override
    protected void consume(Entity food) {
        numberFishEaten++;
        currentScore += food.consumedBy(this);
    }

    @Override
    protected int consumedBy(Entity eater) {
        kill();
        return 0;
    }

    public boolean isFull() {
        return currentScale >= targetScale;
    }

    public int getScore() {
        return currentScore;
    }

}
