package exceptions;

/**
 * The AnimationLoaderException class represents an exception which is occurred during the loading of an entity's sprite sheet.
 */
public class AnimationLoaderException extends GameException{

    /**
     * Creates a new animation loader error.
     * @param description a description of the error in natural language.
     * @param message the detail message.
     */
    public AnimationLoaderException(String description, String message) {
        super("Animation Loader Error", description, message);
    }
}
