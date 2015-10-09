package exceptions;

public class TrackLoaderException extends GameException{

    public TrackLoaderException(String description, String message) {
        super("Track Loader Error", description, message);
    }
    public TrackLoaderException(String title, String description, String message) {
        super(title, description, message);
    }

}
