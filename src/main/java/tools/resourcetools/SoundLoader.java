package tools.resourcetools;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;

import enumerations.GameSounds;
import exceptions.GameException;
import exceptions.SoundLoaderException;
import gui.DialogBox;
import util.Logger;

/**
 * The SoundLoader class is responsible for loading the commonly used audio cues.
 */
public class SoundLoader {

    private static final String EXCEPTION = "GameException Occured: ";
    private static final SoundLoader instance = new SoundLoader();
    private HashMap<String, Clip> soundLoader;

    /**
     * Creates a global sound loader.
     */
    private SoundLoader(){
        soundLoader = new HashMap<String, Clip>();
    }

    /**
     * Returns an instance of the global sound loader.
     * @return global sound loader.
     */
    public static SoundLoader getInstance(){
        return instance;
    }

    /**
     * Loads the specified sound.
     * The returned boolean is used to indicate if something went wrong with loading the sound,
     * or if the sound was already loaded, which means the sound does not have to be read again.
     * @param sound the sound.
     */
    public void loadSound(GameSounds sound){
        loadSound(sound.getSoundKey(), sound.getSoundUrl());
    }

    /**
     * Loads the specified sound.
     * The returned boolean is used to indicate if something went wrong with loading the sound,
     * or if the sound was already loaded, which means the sound does not have to be read again.
     * @param soundKey a unique identifier of the sound.
     * @param soundUrl the resource path of the sound.
     */
    public void loadSound(String soundKey, String soundUrl){
        if (!soundLoader.containsKey(soundKey)) {
            try{
             // Get the audio input stream
                InputStream soundFile = SoundLoader.class.getResourceAsStream(soundUrl);
                AudioInputStream soundStream = AudioSystem.getAudioInputStream(soundFile);

                // Load the audio track into memory
                Clip clip = AudioSystem.getClip();
                clip.open(soundStream);
                soundLoader.put(soundKey, clip);
            } catch (NullPointerException e) {
                e.printStackTrace();
                String description = "The sound location was not specified.";
                String message = "An error occurred while loading the specified sound: " + soundKey;
                message += ". No location specified.";
                GameException exception = new SoundLoaderException(description, message);
                Logger.error(EXCEPTION + exception.getMessage());
                DialogBox.displayWarning(exception);
            } catch (UnsupportedAudioFileException e){
                e.printStackTrace();
                String description = "The sound " + soundKey + " specified is not a valid audio file.";
                String message = "An UnsupportedAudioFileException error occurred while loading the specified sound: " + soundKey;
                message += " at " + soundUrl + ".";
                GameException exception = new SoundLoaderException(description, message);
                Logger.error(EXCEPTION + exception.getMessage());
                DialogBox.displayWarning(exception);
            } catch (IOException e) {
                e.printStackTrace();
                String description = "There was an error while reading the sound " + soundKey + ".";
                String message = "An IOException error occurred while reading the specified sound: " + soundKey;
                message += " at " + soundUrl + ".";
                GameException exception = new SoundLoaderException(description, message);
                Logger.error(EXCEPTION + exception.getMessage());
                DialogBox.displayWarning(exception);
            } catch (Exception e){
                e.printStackTrace();
                String description = "There was an error while loading the sound " + soundKey + " due to system restrictions.";
                String message = "An error occurred while loading the specified sound: " + soundKey;
                message += " at " + soundUrl + ". Due to system restrictions.";
                GameException exception = new SoundLoaderException(description, message);
                Logger.error(EXCEPTION + exception.getMessage());
                DialogBox.displayWarning(exception);
            }
        }
    }

    /**
     * Returns the specified sound.
     * Only returns the specified sound if the sound has been loaded.
     * @param sound the sound.
     * @return the sound.
     */
    public Clip getSound(GameSounds sound){
        return getSound(sound.getSoundKey());
    }

    /**
     * Returns the sound associated with the specified sound identifier.
     * Only returns the specified sound if the sound has been loaded.
     * @param soundKey the sound identifier.
     * @return the sound.
     */
    public Clip getSound(String soundKey){
        return soundLoader.get(soundKey);
    }
}
