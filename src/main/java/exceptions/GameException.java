package exceptions;

/**
 * The GameException class represents a general game exception.
 */
public class GameException extends RuntimeException {

    private String title;
    private String description;

    /**
     * Creates a new general game exception.
     * Which can be displayed via a dialog box.
     * @param title the root of the error.
     * @param description a description of the error in natural language.
     * @param message the detail message.
     */
    public GameException(String title, String description, String message){
        super(message);
        this.title = title;
        this.description = description;
    }

    /**
     * Returns the specified root of the error.
     * To be displayed via the dialog box.
     * @return the title of the error.
     */
    public String getTitle(){
        return title;
    }

    /**
     * Returns a description of the error in natural language.
     * To be displayed via the dialog box.
     * @return the description of the error.
     */
    public String getDescription(){
        return description;
    }

    @Override
    public boolean equals(Object exception){
        if (exception != null && exception instanceof GameException) {
            GameException error = (GameException) exception;
            return description.equals(error.getDescription());
        }
        return false;
    }

    /**
     * Implemented as recommended by the FindBug report.
     * The hashCode should not be used, and will throw a AssertionError when used.
     * @return an arbitrary constant, (42).
     */
    @Override
    public int hashCode() {
        // throw a AssertionError
        assert false : "hashCode not designed";
        // return an arbitrary constant
        return 42;
    }
}
