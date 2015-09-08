package animations;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Animation {

    private BufferedImage[] frames;
    private int numberFrames;
    private int startFrame;
    private int currentFrame;

    private int updateCount;
    private int delay;

    private int timesLooped;

    public Animation(BufferedImage[] frames) {
        this.frames = frames;
        numberFrames = frames.length;
        startFrame = currentFrame = 0;
        updateCount = 0;
        delay = 0;
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

    public boolean hasLooped(int times) {
        if (delay != 0) {
            return timesLooped >= times;
        }
        return true;
    }

    public static Animation createAnimation(String animationURL, int numberFrames, int frameWidth, int frameHeight)
            throws IOException {

        BufferedImage image = ImageIO.read(Animation.class.getResourceAsStream(animationURL));

        BufferedImage[] frames = new BufferedImage[numberFrames];
        for (int i = 0; i < numberFrames; i++) {
            frames[i] = getSubimage(image, i, 0, 0, frameWidth, frameHeight);

        }
        return new Animation(frames);
    }

    private static BufferedImage getSubimage(BufferedImage image, int index, int startX, int startY, int frameWidth,
            int frameHeight) {
        int tempStartX = startX + (index * frameWidth);
        return image.getSubimage(tempStartX, startY, frameWidth, frameHeight);
    }

}
