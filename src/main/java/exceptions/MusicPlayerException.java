package exceptions;

public class MusicPlayerException extends GameException{

    public MusicPlayerException(String description, String message) {
        super("Music Player Error", description, message);
    }

    public MusicPlayerException(String title, String description, String message) {
        super(title, description, message);
    }

}
