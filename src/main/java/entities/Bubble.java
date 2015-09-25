package entities;

import java.awt.Graphics2D;
import java.util.Random;

import animations.Animation;

/**
 * The Bubble class represents a bubble entity.
 */
public class Bubble extends EntityBase {

    /*
     * Randomise bubble movement
     */
    private static final Random generator = new Random(System.currentTimeMillis());

    /*
     * Bubble properties
     */
    private final double dy = 2;
    private double dx;
    private final static double entitywidth = 1000;
    private final static double entityheight = 1000;

    /*
     * Sprite and animation properties
     */
    private final static double spritewidth = 1000;
    private final static double spriteheight = 1000;
    private final static String animationkey = "Bubble";
    private final static String animationurl = "/sprites/Bubble.png";
    private final static int numberframes = 1;
    private final static int animationdelay = 0;

    /*
     * Starting size
     */
    private final static double initialscale = 300;
    private final static double targetscale = 10000;

    /*
     * Shrinking and movement timers
     */
    private final static double scaleStep = 25;
    private final static double minimumScale = 200;
    private int updateCount;
    private int delay = 100;
    private int updateCountX;
    private int delayX;

    /**
     * Creates a bubble at the specified location.
     *
     * @param x the x-coordinate of the bubble
     * @param y the y-coordinate of the bubble
     */
    public Bubble(double x, double y){
        super();
        topLeftX = x;
        topLeftY = y;
        updateCount = 0;
        updateCountX = 0;

        // Randomise the movement delay
        delayX = generator.nextInt(100) + 100;
        // Randomise the initial movement direction
        if ((delayX % 2) == 0) {
            dx = 0.5;
        } else {
            dx = -0.5;
        }
    }

    /**
     * Applies the character properties.
     */
    @Override
    protected void initialiseEntity() {
        entityWidth = entitywidth;
        entityHeight = entityheight;
    }

    /**
     * Applies the sprite properties.
     */
    @Override
    protected void initialiseSprite() {
        spriteWidth = spritewidth;
        spriteHeight = spriteheight;
        currentScale = initialscale;
        targetScale = targetscale;
    }

    /**
     * Creates the animation based on the sprite properties.
     *
     * @return the created animation.
     */
    @Override
    protected Animation createAnimation() {
        return Animation.createAnimation(animationkey, animationurl, numberframes, (int) spriteWidth,
                (int) spriteHeight, animationdelay);
    }

    /**
     * Updates the bubble.
     */
    @Override
    protected void update() {
        // Increment the counters
        updateCount++;
        updateCountX++;

        // If the size counter has passed
        if (updateCount >= delay) {
            // reset
            updateCount = 0;
            // decrement bubble size
            currentScale -= scaleStep;
            if (currentScale < minimumScale){
                // if the bubble is smaller than the minimum then kill it
                kill();
            }
        }

        // If the move counter has passed
        if (updateCountX >= delayX){
            // reset
            updateCountX = 0;
            // change direction of movement
            dx = (-1) * dx;
        }

        // Update the bubble location
        topLeftX += dx;
        topLeftY -= dy;
        // If the bubble has gone off the screen
        if(topLeftY < 0 - getGlobalEntityWidth()){
            // kill it
            kill();
        }
    }

    /**
     * Draws any necessary additions to the specified 2-Dimensional image.
     *
     * @param graphic the 2-Dimensional image.
     */
    @Override
    protected void draw(Graphics2D graphic) {
    }

    /**
     * Handles the consumption of the specified entity.
     *
     * @param the entity to be consumed.
     */
    @Override
    public void consume(Entity food) {
    }

    /**
     * Handles the consumption by the specified entity.
     *
     * @param the entity that consumed this.
     */
    @Override
    public int consumedBy(Entity eater) {
        kill();
        return 0;
    }
}
