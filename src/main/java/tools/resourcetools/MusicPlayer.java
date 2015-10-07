package tools.resourcetools;

import javax.sound.sampled.Clip;

import settings.MusicSettings;

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
