package tools.resourcetools;

import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import enumerations.GameAnimations;
import exceptions.AnimationLoaderException;
import exceptions.GameException;
import gui.DialogBox;
import util.Logger;

/**
 * The AnimationLoader class is responsible for loading commonly used sprite sheets to allow quick access.
 */
public class AnimationLoader {

    private static final String EXCEPTION = "GameException Occured: ";
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
     * @param animation the animation.
     */
    public void loadAnimation(GameAnimations animation){
        loadAnimation(animation.getAnimationkey(), animation.getAnimationUrl(), animation.getNumberFrames(), animation.getFrameWidth(), animation.getFrameHeight());
    }

    /**
     * Loads the specified animation
     * @param animationKey a unique identifier for the animation.
     * @param animationUrl the resource path of the animation.
     * @param numberFrames the number of frames.
     * @param frameWidth the frame width.
     * @param frameHeight the frame height.
     */
    public void loadAnimation(String animationKey, String animationUrl, int numberFrames, int frameWidth, int frameHeight){
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
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                String desciption = "An error occured while trying to find " + animationKey + " sprite sheet at: " + animationUrl + ".";
                String message = "IllegalArgumentException occured while trying to located the sprite sheet at : " + animationUrl + ".";
                GameException exception = new AnimationLoaderException(desciption, message);
                Logger.error(EXCEPTION + exception.getMessage());
                DialogBox.displayError(exception);
            } catch (IOException e){
                e.printStackTrace();
                String desciption = "An error occured while reading the specified sprite sheet at: " + animationUrl + ".";
                String message = "IOException occured while reading: (" + animationKey + ", " + animationUrl + ").";
                GameException exception = new AnimationLoaderException(desciption, message);
                Logger.error(EXCEPTION + exception.getMessage());
                DialogBox.displayError(exception);
            }  catch (NullPointerException e) {
                e.printStackTrace();
                String desciption = "An error occured while subdividing the specified sprite sheet.";
                String message = "NullPointerException occured while subdividing the sprite sheet: (" + animationKey + ", " + animationUrl + ").";
                GameException exception = new AnimationLoaderException(desciption, message);
                Logger.error(EXCEPTION + exception.getMessage());
                DialogBox.displayError(exception);
            } catch (RasterFormatException e){
                e.printStackTrace();
                String desciption = "An error occured while subdividing the specified sprite sheet. The specified dimensions are incorrect.";
                String message = "RasterFormatException occured while subdividing the sprite sheet: (" + animationKey + ", " + animationUrl + ").";
                message += " The specified dimensions: (" + (frameWidth * numberFrames) + ", " + frameHeight + ").";
                GameException exception = new AnimationLoaderException(desciption, message);
                Logger.error(EXCEPTION + exception.getMessage());
                DialogBox.displayError(exception);
            }
        }
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
        BufferedImage[] animation = animationLoader.get(animationKey);
        if (animation == null) {
            String desciption = "The specified animation " + animationKey + " has not been loaded.";
            String message = "Error occured while getting the specified animation: " + animationKey + ".";
            GameException exception = new AnimationLoaderException(desciption, message);
            Logger.error(EXCEPTION + exception.getMessage());
            DialogBox.displayError(exception);
        }
        return animation;
    }

}
