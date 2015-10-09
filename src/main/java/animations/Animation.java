package animations;

import java.awt.image.BufferedImage;

import enumerations.GameAnimations;
import tools.Timer;
import tools.resourcetools.AnimationLoader;

public class Animation {
    private boolean isFacingLeft;
    private BufferedImage[] frames;
    private int frameWidth;
    private int frameHeight;
    private int currentFrame;
    private int numberFrames;
    private Timer frameDelay;

    public Animation(GameAnimations animation){
        AnimationLoader.getInstance().loadAnimation(animation);
        frames = AnimationLoader.getInstance().getAnimation(animation);
        frameWidth = animation.getFrameWidth();
        frameHeight = animation.getFrameHeight();
        currentFrame = 0;
        numberFrames = animation.getNumberFrames();
        frameDelay = new Timer(animation.getFrameDelay());
        isFacingLeft = animation.isFacingLeft();
    }
    public void update(){
        frameDelay.tick();
        if (frameDelay.hasCompleted()) {
            currentFrame = (currentFrame + 1) % numberFrames;
            frameDelay.reset();
        }
    }
    public BufferedImage getCurrentFrame(){
        return frames[currentFrame];
    }
    public double getFrameWidth(){
        return frameWidth;
    }
    public double getFrameHeight(){
        return frameHeight;
    }
    public int getNumberFrames(){
        return numberFrames;
    }
    public boolean isFacingLeft(){
        return isFacingLeft;
    }
}
