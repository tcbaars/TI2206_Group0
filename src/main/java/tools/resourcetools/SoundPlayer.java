package tools.resourcetools;

import javax.sound.sampled.Clip;

import enumerations.GameSounds;
import exceptions.GameException;
import exceptions.SoundPlayerException;
import gui.DialogBox;
import settings.SoundSettings;
import util.Logger;

/**
 * The SoundPlayer class is responsible for playing sounds.
 */
public class SoundPlayer {

    private static final SoundPlayer instance = new SoundPlayer();

    /**
     * Creates a global sound player.
     */
    private SoundPlayer(){
    }

    /**
     * Returns an instance of the global sound player.
     * @return global sound player.
     */
    public static SoundPlayer getInstance(){
        return instance;
    }

    /**
     * Plays the specified sound.
     * @param sound the sound.
     */
    public void playSound(GameSounds sound){
        playSound(sound.getSoundKey());
    }

    /**
     * Plays the specified sound.
     * @param soundKey the sound identifier.
     */
    public void playSound(String soundKey){
        if (SoundSettings.getInstance().isSoundOn()) {
            Clip sound = SoundLoader.getInstance().getSound(soundKey);
            if (sound != null) {
                // Rewinds the sound file to the beginning
                sound.setFramePosition(0);
                // The sound should only play once
                sound.loop(0);
                // Play the sound
                sound.start();
            } else {
                String description = "There was an error while trying to play the sound " + soundKey + ".";
                description += ". The sound has not been loaded.";
                String message = "An error occurred while trying to play the specified sound.";
                message += ". The sound has not been loaded.";
                GameException exception = new SoundPlayerException(description, message);
                Logger.error("GameException Occured: " + exception.getMessage());
                DialogBox.displayWarning(exception);
            }
        }
    }
}
