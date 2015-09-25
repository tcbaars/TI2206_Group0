package entities;

import java.awt.Graphics2D;
import java.util.Random;

import animations.Animation;
import enumerations.Direction;

/**
 * The Swordfish class represents a fast enemy type.
 */
public class Swordfish extends Enemy {

    /*
     * Character properties
     */
    private final static double movespeed = 0.35;
    private Direction movingDirection;
    private final static double basevalue = 339;
    private final static double entitywidth = 91;
    private final static double entityheight = 37;

    /*
     * Sprite and animation properties
     */
    private final static double spritewidth = 91;
    private final static double spriteheight = 74;
    private final static String animationkey = "Swordfish";
    private final static String animationurl = "/sprites/Swordfish.png";
    private final static int numberframes = 2;
    private final static int animationdelay = 10;
    private final static double minscale = 750;
    private final static double maxscale = 2000;
    private final static double targetscale = 10000;

    /**
     * Creates a new Swordfish enemy.
     */
    public Swordfish() {
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
    @Override
    public void setDirection(Direction direction) {
        this.movingDirection = direction;
    }

    /**
     * Handles the updates of the swordfish each tick.
     */
    @Override
    protected void update() {
        // Moves the swordfish in the desired direction
        move(movingDirection);
        // Update the associated bubbles
        if (hasBubbles()){
            bubbles.updateBubbles();
        }
    }

    /**
     * Draws the swordfish specific graphics on the specified 2-Dimensional image.
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
     * Returns the base value of a swordfish entity.
     *
     * @return the base value.
     */
    @Override
    public double getBaseValue() {
        return basevalue;
    }

    /**
     * Handles the necessary actions needed to be performed to consume the specified entity.
     *
     * @param food the entity to be consumed.
     */
    @Override
    public void consume(Entity food) {
        food.consumedBy(this);
    }

    /**
     * Handles the necessary actions needed to be performed when consumed by the specified entity.
     *
     * @param the entity consuming.
     * @return the value of the swordfish adjusted by its current size.
     */
    @Override
    public int consumedBy(Entity eater) {
        kill();
        return calculateValue();
    }
}
