package tools.resourcetools;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import enumerations.GameImages;

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
     * The returned boolean is used to indicate if something went wrong with loading the image,
     * or if the image was already loaded, which means the image does not have to be read again.
     * @param image the image.
     * @return <code>true</code> if and only if the image was read (and loaded), otherwise <code>false</code>.
     */
    public boolean loadImage(GameImages image){
        return loadImage(image.getKey(), image.getUrl());
    }

    /**
     * Loads the specified image.
     * The returned boolean is used to indicate if something went wrong with loading the image,
     * or if the image was already loaded, which means the image does not have to be read again.
     * @param imageKey a unique identifier of the image.
     * @param imageUrl the resource path of the image.
     * @return <code>true</code> if and only if the image was read (and loaded), otherwise <code>false</code>.
     */
    public boolean loadImage(String imageKey, String imageUrl){
        if (!imageLoader.containsKey(imageKey)) {
            try {
                BufferedImage image = ImageIO.read(ImageLoader.class.getResourceAsStream(imageUrl));
                imageLoader.put(imageKey, image);
                return true;
            } catch(IOException e){
                e.printStackTrace();
            }
        }
        return false;
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
        return imageLoader.get(imageKey);
    }
}
