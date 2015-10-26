package entityspawner;

import java.util.ArrayList;
import java.util.Arrays;

import entities.Entity;
import entities.bubbles.Bubble;
import tools.Timer;
import tools.entitytools.Generator;

public class BubbleSpawner implements EntitySpawner{
    private Entity entity;
    private boolean noBubblesBlown;
    private int maxBubbles;
    private int currentNumberBubbles;
    private Bubble[] bubbles;
    private Timer spawnDelay;

    public BubbleSpawner(Entity entity){
        this.entity = entity;
        noBubblesBlown = true;
        maxBubbles = 3;
        currentNumberBubbles = 0;
        bubbles = new Bubble[maxBubbles];
        spawnDelay = new Timer(Generator.generateInteger(100, 400));
    }

    public void update(){
        for (int i = 0; i < currentNumberBubbles; i++){
            bubbles[i].update();
        }
        spawnDelay.tick();
        if (spawnDelay.hasCompleted() && currentNumberBubbles < maxBubbles) {
            spawnDelay.reset();
            if (entity.isAlive()){
                if (noBubblesBlown) {
                    noBubblesBlown = false;
                    spawnDelay = new Timer(50);
                }
                bubbles[currentNumberBubbles] = new Bubble(entity);
                currentNumberBubbles++;
            } else {
                maxBubbles = currentNumberBubbles;
            }
        }
    }
    public boolean hasBlown(){
        if (maxBubbles == currentNumberBubbles) {
            if (currentNumberBubbles != 0) {
                if (bubbles[currentNumberBubbles - 1] != null){
                    return !(bubbles[currentNumberBubbles - 1].isAlive());
                }
            } else {
                return true;
            }
        }
        return false;
    }

    public boolean isActive() {
        return !hasBlown();
    }

    public ArrayList<Entity> getEntities() {
        return new ArrayList<Entity>(Arrays.asList(bubbles));
    }
}
