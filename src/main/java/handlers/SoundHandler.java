package handlers;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundHandler {

    private static SoundHandler instance = new SoundHandler();
    private static HashMap<String, Clip> soundLoader;

    private SoundHandler(){
        soundLoader = new HashMap<String, Clip>();
    }

    public static SoundHandler getInstance(){
        return instance;
    }

    public static void loadSound(String soundKey, String soundPath){
        if(!soundLoader.containsKey(soundKey)){
            try {
                File file = new File(SoundHandler.class.getResource(soundPath).getFile());
                if(file.exists()){
                    AudioInputStream sound = AudioSystem.getAudioInputStream(file);
                    Clip clip = AudioSystem.getClip();
                    clip.open(sound);
                    soundLoader.put(soundKey, clip);
                }
            } catch (IllegalArgumentException e){
                e.printStackTrace();
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            }catch (UnsupportedAudioFileException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static void playSound(String soundKey){
        if(soundLoader.containsKey(soundKey)){
            soundLoader.get(soundKey).setFramePosition(0);
            soundLoader.get(soundKey).loop(0);
            soundLoader.get(soundKey).start();
        }
    }

}
