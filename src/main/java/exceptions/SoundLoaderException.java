package exceptions;

/**
 * The SoundLoaderException class represents an exception which occurs during loading a sound.
 */
public class SoundLoaderException extends GameException{

    /**
     * Creates a new sound loader error.
     * @param description A description of the error in natural language.
     * @param message The detail message.
     */
    public SoundLoaderException(String description, String message) {
        super("Sound Loader Error", description, message);
    }
}
