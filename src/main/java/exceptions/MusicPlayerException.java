package exceptions;

/**
 * The MusicPlayerException class represents an exception which occurs during playing background music.
 */
public class MusicPlayerException extends GameException{

    /**
     * Creates a new music player error.
     * @param description A description of the error in natural language.
     * @param message The detail message.
     */
    public MusicPlayerException(String description, String message) {
        super("Music Player Error", description, message);
    }
}
