package exceptions;

public class SoundLoaderException extends GameException{

    public SoundLoaderException(String description, String message) {
        super("Sound Loader Error", description, message);
    }
    public SoundLoaderException(String title, String description, String message) {
        super(title, description, message);
    }

}
