package tools.resourcetools;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import enumerations.GameImages;
import exceptions.GameException;
import exceptions.ImageLoaderException;
import gui.DialogBox;
import util.Logger;

/**
 * The ImageLoader class is responsible for loading commonly used images for quick access.
 */
public class ImageLoader {

    private static final ImageLoader instance = new ImageLoader();
    private HashMap<String, BufferedImage> imageLoader;

    /**
     * Creates a global image loader.
     */
    private ImageLoader(){
        imageLoader = new HashMap<String, BufferedImage>();
    }

    /**
     * Returns an instance of the global image loader.
     * @return image loader.
     */
    public static ImageLoader getInstance(){
        return instance;
    }

    /**
     * Loads the specified image.
     * @param image the image.
     */
    public void loadImage(GameImages image){
        loadImage(image.getKey(), image.getUrl());
    }

    /**
     * Loads the specified image.
     * @param imageKey a unique identifier of the image.
     * @param imageUrl the resource path of the image.
     */
    public void loadImage(String imageKey, String imageUrl){
        if (!imageLoader.containsKey(imageKey)) {
            try {
                BufferedImage image = ImageIO.read(ImageLoader.class.getResourceAsStream(imageUrl));
                imageLoader.put(imageKey, image);
            } catch(IOException e){
                String desciption = "The specified image " + imageKey + " could not been loaded.";
                String message = "IOException occurred while reading the specified image: " + imageKey + " at " + imageUrl + ".";
                GameException exception = new ImageLoaderException(desciption, message);
                Logger.error("GameException Occured: " + exception.getMessage());
                DialogBox.displayError(exception);
                e.printStackTrace();
            }
        }
    }

    /**
     * Returns the specified image.
     * Only returns the specified image, if the image has been loaded.
     * @param image the image.
     * @return the specified image if the image has been loaded, otherwise <code>null</code>.
     */
    public BufferedImage getImage(GameImages image){
        return getImage(image.getKey());
    }

    /**
     * Returns the image with the specified image identifier.
     * Only returns the specified image, if the image has been loaded.
     * @param imageKey the image identifier.
     * @return the specified image if the image has been loaded, otherwise <code>null</code>.
     */
    public BufferedImage getImage(String imageKey){
        BufferedImage image = imageLoader.get(imageKey);
        if (image == null) {
            String desciption = "The specified image " + imageKey + " has not been loaded.";
            String message = "An error occurred while getting the specified image: " + imageKey + ".";
            GameException exception = new ImageLoaderException(desciption, message);
            Logger.error("GameException Occured: " + exception.getMessage());
            DialogBox.displayError(exception);
        }
        return imageLoader.get(imageKey);
    }
}
