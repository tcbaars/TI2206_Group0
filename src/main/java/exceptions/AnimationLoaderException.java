package exceptions;

public class AnimationLoaderException extends GameException{

    public AnimationLoaderException(String description, String message) {
        super("Animation Loader Error", description, message);
    }
    public AnimationLoaderException(String title, String description, String message) {
        super(title, description, message);
    }

}
