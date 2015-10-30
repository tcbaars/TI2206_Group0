package exceptions;

/**
 * The MusicPlayerException class represents an exception which occurs during playing background music.
 */
public class MusicPlayerException extends GameException{

    /**
     * Creates a new music player error.
     * @param description a description of the error in natural language.
     * @param message the detail message.
     */
    public MusicPlayerException(String description, String message) {
        super("Music Player Error", description, message);
    }
}
