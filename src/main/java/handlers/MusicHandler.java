
package handlers;

import javax.sound.sampled.*;
import javax.sound.sampled.LineEvent.Type;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;


public class MusicHandler {
    
    private final static String musicPath = "/music/Fishy_1.wav";
    private Clip song;
    
    public static MusicHandler instance = new MusicHandler();
    
    private MusicHandler () {
         
    }
    
    //  returns an instance of MusicHandler
    public static MusicHandler getInstance() {
        return instance;
    }
    
    
    public void LoadSong() {
        try {
            //  get the audio input stream
            InputStream musicFile = MusicHandler.class.getResourceAsStream(musicPath);
            AudioInputStream music = AudioSystem.getAudioInputStream(musicFile);

            //  load the sound into memory as a clip
            song = AudioSystem.getClip();
            song.open(music);
            
            //  set the start point to the beginning of the clip
            Rewind();
            
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
            throw new RuntimeException("MusicHandler: Unsupported Audio File: " + e);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
            throw new RuntimeException("MusicHandler: line Unavailable Exception Error: " + e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void PlayMusic() {
        if(OptionsHandler.getInstance().musicOn()) {
            
            //  play the song continuously from the most recent clip position
            song.loop(Clip.LOOP_CONTINUOUSLY);
            song.start();
        } else {
            //  stop playback of the song
            song.stop();
        }
    }
    
    public void Rewind() {
        //  reset clip position to zero
        song.setFramePosition(0);
    }
    
    public void StopMusic() {
        song.stop();
    }

}
