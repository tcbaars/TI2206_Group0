package tools.resourcetools;

import java.io.InputStream;
import java.util.HashMap;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import enumerations.GameSounds;

/**
 * The SoundLoader class is responsible for loading the commonly used audio cues.
 */
public class SoundLoader {

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
     * @return <code>true</code> if and only if the sound was read (and loaded), otherwise <code>false</code>.
     */
    public boolean loadSound(GameSounds sound){
        return loadSound(sound.getSoundKey(), sound.getSoundUrl());
    }

    /**
     * Loads the specified sound.
     * The returned boolean is used to indicate if something went wrong with loading the sound,
     * or if the sound was already loaded, which means the sound does not have to be read again.
     * @param soundKey a unique identifier of the sound.
     * @param soundUrl the resource path of the sound.
     * @return
     */
    public boolean loadSound(String soundKey, String soundUrl){
        if (!soundLoader.containsKey(soundKey)) {
            try{
             // Get the audio input stream
                InputStream soundFile = SoundLoader.class.getResourceAsStream(soundUrl);
                AudioInputStream soundStream = AudioSystem.getAudioInputStream(soundFile);

                // Load the audio track into memory
                Clip clip = AudioSystem.getClip();
                clip.open(soundStream);
                soundLoader.put(soundKey, clip);
                return true;
            } catch (UnsupportedAudioFileException e){
                e.printStackTrace();
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return false;
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
