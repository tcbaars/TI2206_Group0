package entities;

import java.awt.Graphics2D;
import java.util.Random;

/**
 * The Bubbles class manages the bubbles blown by the specified entity.
 */
public class Bubbles {

    /*
     * To randomise the bubbles
     */
    private final static Random generator = new Random(System.currentTimeMillis());

    /*
     * The entity associated with the bubbles
     */
    private Entity entity;

    /*
     * Bubbles state
     */
    private boolean blown;
    private int maxBubbles;
    private int numberBubbles;
    private Bubble[] bubbles;

    /*
     * Time management
     */
    private int updateCount;
    private int initDelay;
    private int delay = 50;

    /**
     * Create a set of bubbles to be associated with the specified entity.
     *
     * @param entity the entity.
     */
    public Bubbles(Entity entity) {
        this.entity = entity;
        maxBubbles = 3;
        blown = false;
        numberBubbles = 0;
        bubbles = new Bubble[maxBubbles];

        updateCount = 0;
        // Set the initial time for the first bubble to be blown to a random amount between 99 and 500
        initDelay = generator.nextInt(400) + 100;
    }

    /**
     * Update the bubbles managed.
     */
    public void updateBubbles() {
        // Update the bubbles already spawned
        for(int i = 0; i < numberBubbles; i++){
            bubbles[i].updateEntity();
        }

        // Increment the update counter
        updateCount++;
        if (updateCount >= delay) {
            // If desired of bubbles have not been reachede
            if (numberBubbles < maxBubbles) {
                if(entity.isAlive()){
                    // If first bubble
                    if(numberBubbles == 0){
                        // And the initial time has not been passed
                        if(updateCount < initDelay){
                            // Then exit
                            return;
                        }
                    }
                    // Otherwise reset time
                    updateCount = 0;
                    // Get fish's mouth location
                    double x = entity.getGlobalEntityBoundingBox().getCenterX();
                    double y =  entity.getGlobalEntityBoundingBox().getCenterY();
                    if(entity.isFacingRight()){
                        x += entity.getGlobalEntityWidth()/ 2;
                    } else {
                        x -= entity.getGlobalEntityWidth() / 2;
                    }
                    // Spawn a fish at the mouth location
                    bubbles[numberBubbles] = new Bubble(x, y);
                    numberBubbles++;
                }
            } else {
                // Otherwise check if the last bubble still exists
                if (!bubbles[numberBubbles - 1].isAlive()) {
                    // If the last bubble has popped then all bubbles have been 'blown'
                    blown = true;
                }
            }
        }
    }

    /**
     * Draw all the bubbles on the specified 2-Dimensional image.
     *
     * @param graphic the 2-Dimensional image.
     */
    public void drawBubbles(Graphics2D graphic) {
        for(int i = 0; i < numberBubbles; i++){
            bubbles[i].drawEntity(graphic);
        }
    }

    /**
     * Return whether or not there are bubbles left.
     *
     * @return <code>true</code> if and only if there are no bubbles left to be blown, otherwise <code>false</code>.
     */
    public boolean hasBubbles(){
        return !blown;
    }
}
