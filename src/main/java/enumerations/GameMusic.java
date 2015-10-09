package enumerations;

/**
 * The GameMusic enumeration is used as a way to easily reference commonly used music tracks.
 * The properties are structured in such a way that they can easily be loaded.
 */
public enum GameMusic {
    TRACK_01("Track01", "/music/track01.wav");

    private String musicKey;
    private String musicUrl;

    /**
     * The properties of the track.
     * @param musicKey unique identifier for the track.
     * @param musicUrl resource path of the track.
     */
    private GameMusic(String musicKey, String musicUrl){
        this.musicKey = musicKey;
        this.musicUrl = musicUrl;
    }

    /**
     * Returns the identifier for the track.
     * @return the music key.
     */
    public String getMusicKey(){
        return musicKey;
    }

    /**
     * Returns the location of the track.
     * @return the resource path of the track.
     */
    public String getMusicUrl(){
        return musicUrl;
    }
}
