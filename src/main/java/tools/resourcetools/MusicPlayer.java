package tools.resourcetools;

import javax.sound.sampled.Clip;

import exceptions.GameException;
import exceptions.MusicPlayerException;
import gui.DialogBox;
import settings.MusicSettings;
import util.Logger;

/**
 * The MusicPlayer is responsible for playing background music.
 */
public class MusicPlayer {

    private static final MusicPlayer instance = new MusicPlayer();
    private Clip currentTrack;

    /**
     * Creates a global music player.
     */
    private MusicPlayer(){
        currentTrack = TrackLoader.getInstance().getNextTrack();
    }

    /**
     * Returns an instance of the global music player.
     * @return global music player.
     */
    public static MusicPlayer getInstance(){
        return instance;
    }

    /**
     * Plays the current background track.
     * If music is enabled.
     */
    public void playMusic(){
        if (currentTrack != null) {
            if (MusicSettings.getInstance().isMusicOn()) {
                if (!currentTrack.isRunning()) {
                    currentTrack.loop(Clip.LOOP_CONTINUOUSLY);
                    currentTrack.start();
                }
            } else {
                currentTrack.stop();
            }
        } else {
            String description = "The current music track has not been loaded.";
            String message = "An error occurred while playing the current music track.";
            GameException exception = new MusicPlayerException(description, message);
            Logger.error("GameException Occured: " + exception.getMessage());
            DialogBox.displayWarning(exception);
        }
    }

    /**
     * Pauses the current background track.
     */
    public void pauseMusic(){
        if (currentTrack != null){
            currentTrack.stop();
        }
    }
}
