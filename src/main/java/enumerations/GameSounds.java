package enumerations;

/**
 * The GameSounds enumeration is used as a way to easily reference commonly used sounds.
 * The properties are structured in such a way that sounds can easily be loaded,
 * and actions can easily play their associated audio cues.
 */
public enum GameSounds {
    SELECT("Select", "/sounds/select.wav"),
    NAVIGATE("Navigate", "/sounds/navigate.wav"),
    CHOMP("Chomp", "/sounds/chomp.wav"),
    LOSE("Lose", "/sounds/lose.wav"),
    WIN("Win", "/sounds/win.wav"),
    BUBBLE_POP("Bubble", "/sounds/bubble.wav");

    private String soundKey;
    private String soundUrl;

    /**
     * The properties of the audio cue.
     * @param soundKey unique identifier for the audio cue.
     * @param soundUrl the resource path of the sound.
     */
    private GameSounds(String soundKey, String soundUrl){
        this.soundKey = soundKey;
        this.soundUrl = soundUrl;
    }

    /**
     * Returns the identifier for the audio cue.
     * @return the sound key.
     */
    public String getSoundKey(){
        return soundKey;
    }

    /**
     * Returns the location of the sound.
     * @return the resource path of the sound.
     */
    public String getSoundUrl(){
        return soundUrl;
    }
}
