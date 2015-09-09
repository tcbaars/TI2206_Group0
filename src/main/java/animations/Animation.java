package animations;

import java.awt.image.BufferedImage;

import handlers.ImageHandler;

public class Animation {

    private BufferedImage[] frames;
    private int numberFrames;
    private int startFrame;
    private int currentFrame;

    private int updateCount;
    private int delay;

    private int timesLooped;

    private Animation(String animationKey, int numberFrames, int startFrame, int delay) {
        frames = ImageHandler.getInstance().getAnimation(animationKey); // getNaimation
                                                                        // may
                                                                        // be
                                                                        // null
        this.numberFrames = numberFrames;
        this.startFrame = currentFrame = startFrame;
        this.delay = delay;
        updateCount = 0;
        timesLooped = 0;
    }

    public void setNumerFrames(int numberFrames) {
        this.numberFrames = numberFrames;
    }

    public void setStartFrame(int startFrame) {
        this.startFrame = startFrame;
    }

    public void setCurrentFrame(int currentFrame) {
        this.currentFrame = currentFrame;
    }

    public void setDely(int delay) {
        this.delay = delay;
    }

    private int getLastFrame() {
        return startFrame + numberFrames;
    }

    /**
     * Update.
     */
    public void update() {
        if (delay != 0) {
            updateCount++;
            if (updateCount >= delay) {
                currentFrame++;
                if (startFrame + currentFrame >= getLastFrame()) {
                    currentFrame = startFrame;
                    timesLooped++;
                }
                updateCount = 0;
            }
        }
    }

    public BufferedImage getCurrentFrame() {
        return frames[currentFrame];
    }

    public boolean hasLooped() {
        return hasLooped(1);
    }

    /**
     * If looped.
     */
    public boolean hasLooped(int times) {
        if (delay != 0) {
            return timesLooped >= times;
        }
        return true;
    }

    /**
     * create.
     */
    public static Animation createAnimation(String animationKey, String animationUrl, int numberFrames, int frameWidth,
            int frameHeight, int delay) {
        ImageHandler.getInstance().loadAnimation(animationKey, animationUrl, numberFrames, frameWidth, frameHeight);
        int initalFrame = 0;
        return new Animation(animationKey, numberFrames, initalFrame, delay);
    }
}
