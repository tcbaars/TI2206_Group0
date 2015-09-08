package entities;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;

import animations.Animation;

public abstract class Entity {

    protected Animation animation;
    protected String animationURL;
    protected int numerFrames;
    protected int spriteWidth;
    protected int spriteHeight;
    protected int delay;

    protected boolean consumable;

    protected boolean alive;

    protected boolean visible;

    protected boolean facingRight;

    protected double targetScale;
    protected double currentScale;

    protected double entityWidth;
    protected double entityHeight;

    protected double x;
    protected double y;

    public Entity() {
        consumable = true;
        alive = true;
        visible = true;
        facingRight = true;
        setupAnimation();
        createAnimation();
        currentScale = targetScale = 100;
        createBoundingBox();
        x = 0;
        y = 0;
    }

    public void isConsumed() {
        alive = false;
        visible = false;
    }

    public boolean isConsumable() {
        return alive && consumable;
    }

    public void setConsumable(boolean consumable) {
        this.consumable = consumable;
    }

    public abstract void consume(Entity food);

    public abstract boolean canConsume(Entity food);

    public boolean isAlive() {
        return alive;
    }

    public void kill() {
        alive = false;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setFacingRight(boolean facingRight) {
        this.facingRight = facingRight;
    }

    public boolean isFacingRight() {
        return facingRight;
    }

    public double getRatio() {
        return currentScale / targetScale;
    }

    public double getCurrentScale() {
        return currentScale;
    }

    public double getTargetScale() {
        return targetScale;
    }

    public void setTargetScale(int scale) {
        this.targetScale = scale;
    }

    public void setCurrentScale(int scale) {
        currentScale = scale;
    }

    public double getEntityWidth() {
        return entityWidth;
    }

    public void setEntityWidth(int entityWidth) {
        this.entityWidth = entityWidth;
    }

    public double getGlobalWidth() {
        return getRatio() * entityWidth;
    }

    public double getEntityHeight() {
        return entityHeight;
    }

    public void setEntityHeight(int entityHeight) {
        this.entityHeight = entityHeight;
    }

    public double getGlobalHeight() {
        return getRatio() * entityHeight;
    }

    public double getEntityX() {
        return x;
    }

    public int getGlobalX() {
        return (int) x;
    }

    public void setEntityX(double x) {
        this.x = x;
    }

    public double getEntityY() {
        return y;
    }

    public int getGlobalY() {
        return (int) y;
    }

    public void setEntityY(double y) {
        this.y = y;
    }

    public abstract void createBoundingBox();

    public Rectangle getBoundingBox() {
        return new Rectangle(getGlobalX(), getGlobalY(), (int) getGlobalWidth(), (int) getGlobalHeight());
    }

    private double calculateGlobalArea() {
        return getGlobalWidth() * getGlobalHeight();
    }

    public boolean isLargerThan(Entity entity) {
        double a1 = calculateGlobalArea();
        double a2 = entity.calculateGlobalArea();
        return a1 > a2;
    }

    public void createAnimation() {
        try {
            animation = Animation.createAnimation(animationURL, numerFrames, spriteWidth, spriteHeight);
            animation.setDely(delay);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected int getGlobalSpriteWidth() {
        return (int) (spriteWidth * getRatio());
    }

    protected int getGlobalSpriteHeight() {
        return (int) (spriteHeight * getRatio());
    }

    protected void drawFrame(Graphics2D g) {
        g.drawImage(animation.getCurrentFrame(), getGlobalX(), getGlobalY(), getGlobalSpriteWidth(),
                getGlobalSpriteHeight(), null);
    }

    protected abstract void setupAnimation();

    public abstract void update();

    public abstract void draw(Graphics2D g);

}
