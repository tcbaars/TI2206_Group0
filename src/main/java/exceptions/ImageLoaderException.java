package exceptions;

/**
 * The ImageLoaderException class represents an exception which occurs during the loading of an image.
 */
public class ImageLoaderException extends GameException{

    /**
     * Creates a new image loader error.
     * @param description A description of the error in natural language.
     * @param message The detail message.
     */
    public ImageLoaderException(String description, String message) {
        super("Image Loader Error", description, message);
    }
}
