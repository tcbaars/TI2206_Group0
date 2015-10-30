package exceptions;

/**
 * The SoundPlayerException class represents an exception which occurs during playing a sound.
 */
public class SoundPlayerException extends GameException{

    /**
     * Creates a new sound player error.
     * @param description A description of the error in natural language.
     * @param message The detail message.
     */
    public SoundPlayerException(String description, String message) {
        super("Sound Player Error", description, message);
    }
}
