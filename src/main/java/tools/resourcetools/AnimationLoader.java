package tools.resourcetools;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import enumerations.GameAnimations;

/**
 * The AnimationLoader class is responsible for loading commonly used sprite sheets to allow quick access.
 */
public class AnimationLoader {

    private static final AnimationLoader instance = new AnimationLoader();
    private HashMap<String, BufferedImage[]> animationLoader;

    /**
     * Creates a global animation loader.
     */
    private AnimationLoader(){
        animationLoader = new  HashMap<String, BufferedImage[]>();
    }

    /**
     * Returns an instance of the global animation loader.
     * @return animation loader.
     */
    public static AnimationLoader getInstance(){
        return instance;
    }

    /**
     * Loads the specified animation.
     * The returned boolean is used to indicate if something went wrong with loading the animation,
     * or if the animation was already loaded, which means the animation does not have to be read again.
     * @param animation the animation.
     * @return <code>true</code> if and only if the animation was read (and loaded), otherwise <code>false</code>.
     */
    public boolean loadAnimation(GameAnimations animation){
        return loadAnimation(animation.getAnimationkey(), animation.getAnimationUrl(), animation.getNumberFrames(), animation.getFrameWidth(), animation.getFrameHeight());
    }

    /**
     * Loads the specified animation
     * The returned boolean is used to indicate if something went wrong with loading the animation,
     * or if the animation was already loaded, which means the animation does not have to be read again.
     * @param animationKey a unique identifier for the animation.
     * @param animationUrl the resource path of the animation.
     * @param numberFrames the number of frames.
     * @param frameWidth the frame width.
     * @param frameHeight the frame height.
     * @return <code>true</code> if and only if the animation was read (and loaded), otherwise <code>false</code>.
     */
    public boolean loadAnimation(String animationKey, String animationUrl, int numberFrames, int frameWidth, int frameHeight){
        if (!animationLoader.containsKey(animationKey)) {
            try {
                BufferedImage spritesheet = ImageIO.read(AnimationLoader.class.getResourceAsStream(animationUrl));
                BufferedImage[] frames = new BufferedImage[numberFrames];
                int startX;
                // Get the array of frames contained in the sprite sheet.
                for (int i = 0; i < numberFrames; i++) {
                    startX = i * frameWidth;
                    frames[i] = spritesheet.getSubimage(startX, 0, frameWidth, frameHeight);
                }
                animationLoader.put(animationKey, frames);
                return true;
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * Returns the array of images that represent the animation specified.
     * Only returns the specified animation if the animation has been loaded.
     * @param animation the animation.
     * @return the animation.
     */
    public BufferedImage[] getAnimation(GameAnimations animation){
        return getAnimation(animation.getAnimationkey());
    }

    /**
     * Returns the array of images that represent the animation associated with the identifier specified.
     * Only returns the specified animation if the animation has been loaded.
     * @param animationKey the animation identifier.
     * @return the animation.
     */
    public BufferedImage[] getAnimation(String animationKey){
        return animationLoader.get(animationKey);
    }

}
