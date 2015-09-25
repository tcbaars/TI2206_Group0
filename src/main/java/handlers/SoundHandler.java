package handlers;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * The SoundHandler class allows for sound files to be pre-loaded for quicker access time.
 */
public class SoundHandler {

    private static SoundHandler instance = new SoundHandler();
    private static HashMap<String, Clip> soundLoader;

    /**
     * Creates a new SoundHandler.
     */
    private SoundHandler(){
        soundLoader = new HashMap<String, Clip>();
    }

    /**
     * Returns an instance of the SoundHandler.
     *
     * @return an instance.
     */
    public static SoundHandler getInstance(){
        return instance;
    }

    /**
     * Loads the audio cue, at the specified file path, with the specified sound key.
     * If the sound file exists at the specified file path.
     *
     * @param soundKey the sound key to be associated with the audio cue.
     * @param soundPath the location of the sound file.
     */
    public void loadSound(String soundKey, String soundPath){
        if(!soundLoader.containsKey(soundKey)){
            try {
                File file = new File(SoundHandler.class.getResource(soundPath).getFile());
                if(file.exists()){
                    // Open an audio input stream
                    AudioInputStream sound = AudioSystem.getAudioInputStream(file);
                    // Allocate memory space for audio file
                    Clip clip = AudioSystem.getClip();
                    // Load sound from audio input
                    clip.open(sound);
                    // Save loaded clip for quick access
                    soundLoader.put(soundKey, clip);
                }
            } catch (IllegalArgumentException e){
                e.printStackTrace();
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            }catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Plays the audio cue associated with the specified sound key.
     * If the sound has been loaded.
     *
     * @param soundKey the specified sound.
     * @return <code>true</code> if and only if the specified sound was loaded, otherwise <code>false</code>.
     */
    public boolean playSound(String soundKey){
        if (soundLoader.containsKey(soundKey)) {
            if (OptionsHandler.getInstance().soundOn()) {
                // Rewinds the sound file to the beginning
                soundLoader.get(soundKey).setFramePosition(0);
                // The sound should only play once
                soundLoader.get(soundKey).loop(0);
                // Play the sound
                soundLoader.get(soundKey).start();
            }
            return true;
        }
        return false;
    }

}
