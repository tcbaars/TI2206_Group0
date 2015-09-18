package entities;

import java.awt.Graphics2D;
import java.util.Random;

import animations.Animation;

public class Bubble extends Entity{

    private static final Random generator = new Random(System.currentTimeMillis());
    /*
     * Character properties
     */
    private final double dy = 2;
    private double dx;
    private final static double entitywidth = 1000;
    private final static double entityheight = 1000;

    /*
     * Sprite and animation properties
     */
    private final static double spritewidth = 1000;
    private final static double spriteheight = 1000;
    private final static String animationkey = "Bubble";
    private final static String animationurl = "/sprites/Bubble.png";
    private final static int numberframes = 1;
    private final static int animationdelay = 0;

    /*
     * Starting size
     */
    private final static double initialscale = 300;
    private final static double targetscale = 10000;

    private final static double scaleStep = 25;
    private final static double minimumScale = 200;
    private int updateCount;
    private int delay = 100;
    private int updateCountX;
    private int delayX;

    public Bubble(double x, double y){
        super();
        topLeftX = x;
        topLeftY = y;
        updateCount = 0;
        updateCountX = 0;

        delayX = generator.nextInt(100) + 100;
        if ((delayX % 2) == 0) {
            dx = 0.5;
        } else {
            dx = -0.5;
        }
    }

    @Override
    protected void initialiseEntity() {
        entityWidth = entitywidth;
        entityHeight = entityheight;
    }

    @Override
    protected void initialiseSprite() {
        spriteWidth = spritewidth;
        spriteHeight = spriteheight;
        currentScale = initialscale;
        targetScale = targetscale;
    }

    @Override
    protected Animation createAnimation() {
        return Animation.createAnimation(animationkey, animationurl, numberframes, (int) spriteWidth,
                (int) spriteHeight, animationdelay);
    }

    @Override
    protected void update() {
        updateCount++;
        updateCountX++;

        if (updateCount >= delay) {
            updateCount = 0;
            currentScale -= scaleStep;
            if (currentScale < minimumScale){
                kill();
            }
        }

        if (updateCountX >= delayX){
            updateCountX = 0;
            dx = (-1) * dx;
        }

        topLeftX += dx;
        topLeftY -= dy;
        if(topLeftY < 0 - getGlobalEntityWidth()){
            kill();
        }
    }

    @Override
    protected void draw(Graphics2D graphic) {
    }

    @Override
    protected void consume(Entity food) {
    }

    @Override
    protected int consumedBy(Entity eater) {
        kill();
        return 0;
    }
}
