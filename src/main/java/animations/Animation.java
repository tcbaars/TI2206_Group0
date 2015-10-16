package animations;

import java.awt.image.BufferedImage;

import enumerations.GameAnimations;
import tools.Timer;
import tools.resourcetools.AnimationLoader;

/**
 * The Animation class is responsible for handling the array of images which represent a sprite's animation.
 */
public class Animation {

    private boolean isFacingLeft;
    private BufferedImage[] frames;
    private int frameWidth;
    private int frameHeight;
    private int currentFrame;
    private int numberFrames;
    private Timer frameDelay;

    /**
     * Creates an array of images which represents the specified animation.
     * @param animation animation information.
     */
    public Animation(GameAnimations animation){
        // Load sprite sheet
        AnimationLoader.getInstance().loadAnimation(animation);
        frames = AnimationLoader.getInstance().getAnimation(animation);
        // Sprite sheet information
        frameWidth = animation.getFrameWidth();
        frameHeight = animation.getFrameHeight();
        numberFrames = animation.getNumberFrames();
        currentFrame = 0;
        // Delay between frames
        frameDelay = new Timer(animation.getFrameDelay());
        // Direction the entity in the sprite sheet is facing
        isFacingLeft = animation.isFacingLeft();
    }

    /**
     * Updates the current frame displayed.
     */
    public void update(){
        frameDelay.tick();
        // If frame delay has passed
        if (frameDelay.hasCompleted()) {
            // Display the next frame
            currentFrame = (currentFrame + 1) % numberFrames;
            frameDelay.reset();
        }
    }

    /**
     * Returns the current frame of the animation.
     * @return the current frame.
     */
    public BufferedImage getCurrentFrame(){
        return frames[currentFrame];
    }

    /**
     * Returns the width of a single frame.
     * @return the width of the frame.
     */
    public double getFrameWidth(){
        return frameWidth;
    }

    /**
     * Returns the height of a single frame.
     * @return the height of the frame.
     */
    public double getFrameHeight(){
        return frameHeight;
    }

    /**
     * Returns the number of frames in the animation.
     * @return the number of frames.
     */
    public int getNumberFrames(){
        return numberFrames;
    }

    /**
     * Returns whether or not the entity contained in the animation sequence is facing left.
     * @return <code>true</code> if and only if the entity in the sprite sheet is facing left, otherwise <code>false</code>.
     */
    public boolean isFacingLeft(){
        return isFacingLeft;
    }
}
