package tools.resourcetools;

import javax.sound.sampled.Clip;

import enumerations.GameSounds;
import settings.SoundSettings;

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
     * @return <code>true</code> if and only if the specified sound was played, otherwise <code>false</code>.
     */
    public boolean playSound(GameSounds sound){
        return playSound(sound.getSoundKey());
    }

    /**
     * Plays the specified sound.
     * @param soundKey the sound identifier.
     * @return <code>true</code> if and only if the specified sound was played, otherwise <code>false</code>.
     */
    public boolean playSound(String soundKey){
        if (SoundSettings.getInstance().isSoundOn()) {
            Clip sound = SoundLoader.getInstance().getSound(soundKey);
            if (sound != null) {
                // Rewinds the sound file to the beginning
                sound.setFramePosition(0);
                // The sound should only play once
                sound.loop(0);
                // Play the sound
                sound.start();
                return true;
            }
        }
        return false;
    }
}
