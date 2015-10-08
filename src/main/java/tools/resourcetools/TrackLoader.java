package tools.resourcetools;

import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import enumerations.GameMusic;

/**
 * The TrackLoader class is responsible for loading the background music.
 */
public class TrackLoader {

    private static final TrackLoader instance = new TrackLoader();
    private Clip nextTrack;

    /**
     * Creates a global track loader.
     */
    private TrackLoader(){
        loadNextTrack();
    }

    /**
     * Returns an instance of the global track loader.
     * @return the global track loader.
     */
    public static TrackLoader getInstance(){
        return instance;
    }

    /**
     * Returns the next track in the background music playlist.
     * @return the next track.
     */
    public Clip getNextTrack(){
        return nextTrack;
    }

    /**
     * Loads the next track in the background music playlist.
     * The returned boolean is used to indicate if something went wrong with loading the track,
     * or if the track was already loaded, which means the track does not have to be read again.
     * @return <code>true</code> if and only if the track was read (and loaded), otherwise <code>false</code>.
     */
    public boolean loadNextTrack(){
        if (nextTrack == null){
            try {
                String path = GameMusic.TRACK_01.getMusicUrl();
                // Get the audio input stream
                InputStream musicFile = TrackLoader.class.getResourceAsStream(path);
                AudioInputStream musicStream = AudioSystem.getAudioInputStream(musicFile);

                // Load the audio track into memory
                nextTrack = AudioSystem.getClip();
                nextTrack.open(musicStream);
                nextTrack.setFramePosition(0);
                return true;
            } catch (UnsupportedAudioFileException e){
                e.printStackTrace();
            } catch (LineUnavailableException e){
                e.printStackTrace();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }
}
