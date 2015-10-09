package exceptions;

public class ImageLoaderException extends GameException{

    public ImageLoaderException(String description, String message) {
        super("Image Loader Error", description, message);
    }
    public ImageLoaderException(String title, String description, String message) {
        super(title, description, message);
    }

}
