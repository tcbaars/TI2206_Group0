package entities;

import java.awt.Graphics2D;
import java.util.Random;

public class Bubbles {

    private final static Random generator = new Random(System.currentTimeMillis());

    private Entity entity;

    private boolean blown;
    private int maxBubbles;
    private int numberBubbles;
    private Bubble[] bubbles;

    private int updateCount;
    private int initDelay;
    private int delay = 50;

    public Bubbles(Entity entity) {
        this.entity = entity;
        maxBubbles = 3;
        blown = false;
        numberBubbles = 0;
        bubbles = new Bubble[maxBubbles];

        updateCount = 0;
        initDelay = generator.nextInt(400) + 100;
    }

    public void updateBubbles() {
        for(int i = 0; i < numberBubbles; i++){
            bubbles[i].updateEntity();
        }

        updateCount++;
        if (updateCount >= delay) {
            if (numberBubbles < maxBubbles) {
                if(entity.isAlive()){
                    if(numberBubbles == 0){
                        if(updateCount < initDelay){
                            return;
                        }
                    }
                    updateCount = 0;
                    double x = entity.getGlobalEntityBoundingBox().getCenterX();
                    double y =  entity.getGlobalEntityBoundingBox().getCenterY();
                    if(entity.isFacingRight()){
                        x += entity.getGlobalEntityWidth()/ 2;
                    } else {
                        x -= entity.getGlobalEntityWidth() / 2;
                    }
                    bubbles[numberBubbles] = new Bubble(x, y);
                    numberBubbles++;
                }
            } else {
                if (!bubbles[numberBubbles - 1].isAlive()) {
                    blown = true;
                }
            }
        }
    }

    public void drawBubbles(Graphics2D graphic) {
        for(int i = 0; i < numberBubbles; i++){
            bubbles[i].drawEntity(graphic);
        }
    }

    public boolean hasBubbles(){
        return !blown;
    }
}
