package entities;

import java.awt.Graphics2D;

import animations.Animation;
import enumerations.Direction;
import handlers.OptionsHandler;

public class Player extends Entity {

    private final double movespeed = 75;

    private final double entitywidth = 1280;
    private final double entityheight = 570;

    private final double spritewidth = 1300;
    private final double spriteheight = 600;
    private final String animationkey = "Player";
    private final String animationurl = "/sprites/Player.png";
    private final int numberframes = 12;
    private final int animationdely = 10;

    private final double startscale = 100;
    private final double targetscale = 1000;

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
        entityWidth = entitywidth;
        entityHeight = entityheight;
    }

    @Override
    protected void initialiseSprite() {
        topLeftX = OptionsHandler.getInstance().getWidth() / 2;
        topLeftY = OptionsHandler.getInstance().getHeight() / 2;
        spriteWidth = spritewidth;
        spriteHeight = spriteheight;
        currentScale = startscale;
        targetScale = targetscale;
        System.out.println("Sclaing: " + this.getScaling());
    }

    @Override
    protected Animation createAnimation() {
        return Animation.createAnimation(animationkey, animationurl, numberframes, (int) spritewidth,
                (int) spriteheight, animationdely);
    }

    @Override
    protected void update() {
    }

    @Override
    protected void draw(Graphics2D graphic) {
    }

    public void moveUp() {
        topLeftY -= movespeed;
    }

    public void moveDown() {
        topLeftY += movespeed;
    }

    public void moveLeft() {
        topLeftX -= movespeed;
    }

    public void moveRight() {
        topLeftX += movespeed;
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
