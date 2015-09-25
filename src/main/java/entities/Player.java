package entities;

import java.awt.Graphics2D;

import animations.Animation;
import enumerations.Direction;
import handlers.OptionsHandler;

/**
 * The Player class represents the player controlled entity.
 */
public class Player extends EntityBase {

    /*
     * The current game settings
     */
    private OptionsHandler _optionsHandler = OptionsHandler.getInstance();

    /*
     * Character properties
     */
    private final static double movespeed = 10;
    private final static double entitywidth = 1280;
    private final static double entityheight = 560;

    /*
     * Sprite and animation properties
     */
    private final static double spritewidth = 1300;
    private final static double spriteheight = 600;
    private final static String animationkey = "Player";
    private final static String animationurl = "/sprites/Player.png";
    private final static int numberframes = 12;
    private final static int animationdelay = 10;

    /*
     * Starting size
     */
    private final static double initialscale = 2000;
    private final static double targetscale = 10000;

    /*
     * Player information
     */
    private int currentScore;
    private int numberFishEaten;
    private Bubbles bubbles;

    /**
     * Creates a new player.
     */
    public Player() {
        super();
        currentScore = 0;
        numberFishEaten = 0;
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
     * Applies the sprite and animation properties.
     */
    @Override
    protected void initialiseSprite() {
        topLeftX = OptionsHandler.getInstance().getWidth() / 2.0;
        topLeftY = OptionsHandler.getInstance().getHeight() / 2.0;
        spriteWidth = spritewidth;
        spriteHeight = spriteheight;
        currentScale = initialscale;
        targetScale = targetscale;
    }

    /**
     * Creates the animation using the specified sprite and animation properties.
     *
     * @return the animation.
     */
    @Override
    protected Animation createAnimation() {
        return Animation.createAnimation(animationkey, animationurl, numberframes, (int) spriteWidth,
                (int) spriteHeight, animationdelay);
    }

    /**
     * Initialises a new set of bubbles to be associated with the player.
     */
    private void initBubbles(){
        bubbles = new Bubbles(this);
    }

    /**
     * Handles the updates of the player each tick.
     */
    @Override
    protected void update() {
        /*
         *If there are no bubbles currently associated with the player
         *Then associate a new set of bubbles
         */
        if(bubbles != null){
            if(bubbles.hasBubbles()){
                bubbles.updateBubbles();
            } else {
                initBubbles();
            }
        } else {
            initBubbles();
        }
    }

    /**
     * Draws any necessary player specific graphics on the specified 2-dimensional image.
     *
     * @param graphic the 2-dimensional image.
     */
    @Override
    protected void draw(Graphics2D graphic) {
        // If the player has bubbles associated with it
        if(bubbles != null){
            // Then draw the bubbles
            bubbles.drawBubbles(graphic);
        }
    }

    /**
     * Move the player up.
     */
    public void moveUp() {
        if ((topLeftY - movespeed) > -10) {
            topLeftY -= movespeed;
        }
    }

    /**
     * Move the player down.
     */
    public void moveDown() {
        if ((topLeftY + movespeed) < _optionsHandler.getHeight() - getGlobalEntityHeight()) {
            topLeftY += movespeed;
        }
    }

    /**
     * Move the player left.
     */
    public void moveLeft() {
        if ((topLeftX - movespeed) > -10) {
            topLeftX -= movespeed;
        }
    }

    /**
     * Move the player right.
     */
    public void moveRight() {
        if ((topLeftX + movespeed) < _optionsHandler.getWidth() - getGlobalEntityWidth()) {
            topLeftX += movespeed;
        }
    }

    /**
     * Move the player in the specified direction.
     *
     * @param direction the desired direction.
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
            setFacingRight(false);
            moveLeft();
            break;
        case RIGHT:
            setFacingRight(true);
            moveRight();
            break;
        default:
        }
    }

    /**
     * Handles the necessary actions needed to be performed to consume the specified entity.
     *
     * @param food the entity to be eaten.
     */
    @Override
    public void consume(Entity food) {
        if (food != null){
            // Notify the food that it has been eaten
            food.consumedBy(this);
            // Update the player progress information
            numberFishEaten++;
            currentScale += food.getScaling() * 500;
            currentScore = (int) currentScale - 1000;
        }
    }

    /**
     * Handles the necessary actions that need to be performed when the player is consumed.
     *
     * @param eater the entity consuming the player.
     * @return the value of the player.
     */
    @Override
    public int consumedBy(Entity eater) {
        kill();
        return 0;
    }

    /**
     * Returns whether or not the player has reached the target size.
     *
     * @return <code>true</code> if and only if the current size of the player is equal to or greater than the target size, otherwise <code>false</code>.
     */
    public boolean isFull() {
        return currentScale >= targetScale;
    }

    /**
     * Returns the current score achieved by the player.
     *
     * @return the current score.
     */
    public int getScore() {
        return currentScore;
    }

    /**
     * Returns the current number of fish eaten.
     *
     * @return the number of fish eaten.
     */
    public int getFishEaten(){
        return numberFishEaten;
    }
}
