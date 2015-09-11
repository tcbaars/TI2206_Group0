package entities;

import java.awt.Graphics2D;
import java.util.Random;

import animations.Animation;
import enumerations.Direction;

public class Trout extends Enemy {

    private final double movespeed = 10;
    private final double basevalue = 10;

    private final double entitywidth = 1300;
    private final double entityheight = 524;

    private final double spritewidth = 1300;
    private final double spriteheight = 600;
    private final String animationkey = "Trout";
    private final String animationurl = "/sprites/Trout.png";
    private final int numberframes = 12;
    private final int animationdelay = 10;

    private final double minscale = 750;
    private final double maxscale = 2000;
    private final double targetscale = 10000;

    private Direction movingDirection;

    public Trout() {
        super();
    }

    @Override
    protected void initialiseEntity() {
        entityWidth = entitywidth;
        entityHeight = entityheight;
        moveSpeed = movespeed;
        targetScale = targetscale;
    }

    @Override
    protected void initialiseSprite() {
        generator = new Random(System.currentTimeMillis());
        spriteWidth = spritewidth;
        spriteHeight = spriteheight;
        setRandomSide();
        setRandomDepth();
        setRandomScale(minscale, maxscale);
    }

    @Override
    protected Animation createAnimation() {
        return Animation.createAnimation(animationkey, animationurl, numberframes, (int) spritewidth,
                (int) spriteheight, animationdelay);
    }

    public void setDirection(Direction direction) {
        this.movingDirection = direction;
    }

    @Override
    protected void update() {
        move(movingDirection);
    }

    @Override
    protected void draw(Graphics2D graphics) {
    }

    public double getBaseValue() {
        return basevalue;
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
