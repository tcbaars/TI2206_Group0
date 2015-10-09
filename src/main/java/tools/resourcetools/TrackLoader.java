package tools.resourcetools;

import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;

import enumerations.GameMusic;
import exceptions.GameException;
import exceptions.SoundLoaderException;
import gui.DialogBox;
import util.Logger;

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
     */
    public void loadNextTrack(){
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
            } catch (NullPointerException e) {
                e.printStackTrace();
                String description = "The music track location was not specified.";
                String message = "An error occurred while loading the specified track.";
                GameException exception = new SoundLoaderException(description, message);
                Logger.error("GameException Occured: " + exception.getMessage());
                DialogBox.displayWarning(exception);
            } catch (UnsupportedAudioFileException e){
                e.printStackTrace();
                String description = "The music track specified is not a valid audio file.";
                String message = "An UnsupportedAudioFileException error occurred while loading the specified music track.";
                GameException exception = new SoundLoaderException(description, message);
                Logger.error("GameException Occured: " + exception.getMessage());
                DialogBox.displayWarning(exception);
            } catch (IOException e) {
                e.printStackTrace();
                String description = "There was an error while reading the music track.";
                String message = "An IOException error occurred while reading the specified music track.";
                GameException exception = new SoundLoaderException(description, message);
                Logger.error("GameException Occured: " + exception.getMessage());
                DialogBox.displayWarning(exception);
            } catch (Exception e){
                e.printStackTrace();
                String description = "There was an error while loading the music track. Due to system restrictions.";
                String message = "There was an error while loading the music track. Due to system restrictions.";
                GameException exception = new SoundLoaderException(description, message);
                Logger.error("GameException Occured: " + exception.getMessage());
                DialogBox.displayWarning(exception);
            }
        }
    }
}
