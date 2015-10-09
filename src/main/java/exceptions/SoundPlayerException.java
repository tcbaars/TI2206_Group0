package exceptions;

public class SoundPlayerException extends GameException{

    public SoundPlayerException(String description, String message) {
        super("Sound Player Error", description, message);
    }
    public SoundPlayerException(String title, String description, String message) {
        super(title, description, message);
    }

}
