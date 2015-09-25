package handlers;

import util.Logger;

public class OptionsHandler {

    private static OptionsHandler instance = new OptionsHandler();

    private final static int width = 640;
    private final static int height = 360;
    private int scale;

    private final static int targetfps = 60;

    private boolean debug;
    private boolean music;
    private boolean sound;

    private int minEnemies;
    private int maxEnemies;

    private OptionsHandler() {
        scale = 2;
        music = true;
        sound = true;
        debug = false;
        minEnemies = 7;
        maxEnemies = 14;
    }

    public static OptionsHandler getInstance() {
        return instance;
    }

    public int getWidth() {
        return width * scale;
    }

    public int getHeight() {
        return height * scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public int getScale() {
        return scale;
    }

    public int getTargetFps() {
        return targetfps;
    }

    public boolean musicOn() {
        return music;
    }

    public void toggleMusic() {
        music = !music;
        Logger.info("Enable music playback: " + music);
    }

    public boolean soundOn() {
        return sound;
    }

    public boolean getDebug() { return debug; }

    public void toggleDebug() { debug = !debug;}

    public void toggleSound() {
        sound = !sound;
    }

    public void setMinEnemies(int min) {
        minEnemies = min;
    }

    public int getMinEnemies() {
        return minEnemies;
    }

    public void setMaxEnemies(int max) {
        maxEnemies = max;
    }

    public int getMaxEnemies() {
        return maxEnemies;
    }

}
