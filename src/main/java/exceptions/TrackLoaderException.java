package exceptions;

/**
 * The TrackLoaderException class represents an exception which occurs during loading of the background music.
 */
public class TrackLoaderException extends GameException{

    /**
     * Creates a new track loader error.
     * @param description a description of the error in natural language.
     * @param message the detail message.
     */
    public TrackLoaderException(String description, String message) {
        super("Track Loader Error", description, message);
    }
}
