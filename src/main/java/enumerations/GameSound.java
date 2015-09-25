package enumerations;

public enum GameSound {
    SELECT("/sounds/select.wav", "select"),
    NAVIGATE("/sounds/navigate.wav", "navigate"),
    CHOMP("/sounds/chomp.wav", "chomp"),
    LOSE("/sounds/lose.wav", "lose"),
    WIN("/sounds/win.wav", "win"),
    BUBBLE("/sounds/bubble.wav", "bubble");

    private String url;
    private String key;

    private GameSound(String url, String key){
        this.url = url;
        this.key = key;
    }

    public String getURL(){
        return url;
    }

    public String getKey(){
        return key;
    }
}
