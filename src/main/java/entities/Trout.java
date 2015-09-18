package entities;

import java.awt.Graphics2D;
import java.util.Random;

import animations.Animation;
import enumerations.Direction;

/**
 * The Trout class represents a basic enemy type.
 */
public class Trout extends Enemy {

    /*
     * Character properties
     */
    private final static double movespeed = 0.2;
    private Direction movingDirection;
    private final static double basevalue = 100;
    private final static double entitywidth = 1280;
    private final static double entityheight = 510;

    /*
     * Sprite and animation properties
     */
    private final static double spritewidth = 1300;
    private final static double spriteheight = 600;
    private final static String animationkey = "Trout";
    private final static String animationurl = "/sprites/Trout.png";
    private final static int numberframes = 12;
    private final static int animationdelay = 10;
    private final static double minscale = 750;
    private final static double maxscale = 2000;
    private final static double targetscale = 10000;

    /**
     * Creates a new Trout enemy.
     */
    public Trout() {
        super();
    }

    /**
     * Applies the character properties.
     */
    @Override
    protected void initialiseEntity() {
        entityWidth = entitywidth;
        entityHeight = entityheight;
        moveSpeed = movespeed;
        targetScale = targetscale;
    }

    /**
     * Applies the sprite properties.
     */
    @Override
    protected void initialiseSprite() {
        generator = new Random(System.currentTimeMillis());
        spriteWidth = spritewidth;
        spriteHeight = spriteheight;
        setRandomSide();
        setRandomDepth();
        setRandomScale(minscale, maxscale);
        int rand = generator.nextInt(101);
        if(rand >= 75){
            bubbles = new Bubbles(this);
        }
    }

    /**
     * Creates an animation based on the specified sprite information.
     *
     * @return the animation.
     */
    @Override
    protected Animation createAnimation() {
        return Animation.createAnimation(animationkey, animationurl, numberframes, (int) spritewidth,
                (int) spriteheight, animationdelay);
    }

    /**
     * Sets the direction of movement.
     *
     * @param direction the desired direction of movement.
     */
    public void setDirection(Direction direction) {
        this.movingDirection = direction;
    }

    /**
     * Handles the updates of the trout each tick.
     */
    @Override
    protected void update() {
        // Moves the trout in the desired direction
        move(movingDirection);
        // Update the associated bubbles
        if (hasBubbles()){
            bubbles.updateBubbles();
        }
    }

    /**
     * Draws the trout specific graphics on the specified 2-Dimensional image.
     *
     *  @param graphic the 2-Dimensional image.
     */
    @Override
    protected void draw(Graphics2D graphic) {
        if(hasBubbles()){
            bubbles.drawBubbles(graphic);
        }
    }

    /**
     * Returns the base value of a trout entity.
     *
     * @return the base value.
     */
    public double getBaseValue() {
        return basevalue;
    }

    /**
     * Handles the necessary actions needed to be performed to consume the specified entity.
     *
     * @param food the entity to be consumed.
     */
    @Override
    protected void consume(Entity food) {
        food.consumedBy(this);
    }

    /**
     * Handles the necessary actions needed to be performed when consumed by the specified entity.
     *
     * @param the entity consuming.
     * @return the value of the trout adjusted by its current size.
     */
    @Override
    protected int consumedBy(Entity eater) {
        kill();
        return calculateValue();
    }
}
