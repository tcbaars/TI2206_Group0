package exceptions;

public class GameException extends RuntimeException {

    private String title;
    private String description;

    public GameException(String title, String description, String message){
        super(message);
        this.title = title;
        this.description = description;
    }

    public String getTitle(){
        return title;
    }

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
}
