package handlers;

import java.awt.Graphics2D;
import java.util.Random;

import entities.Enemy;
import entities.Player;
import entities.Trout;
import entities.Stingray;
import entities.Shark;
import entities.Dunkleosteus;
import entities.Swordfish;

public class EnemyHandler {

    private Random generator;
    private Enemy[] enemies;
    private int max;
    private int currentNumber;

    private int updateCount;
    private int delay;

    /**
     * Handle enemey spawning.
     */
    public EnemyHandler() {
        generator = new Random(System.currentTimeMillis());
        max = OptionsHandler.getInstance().getMaxEnemies() + 1;
        enemies = new Enemy[max];
        currentNumber = 0;
        updateCount = 0;
        delay = 10;
    }

    /**
     * Update enemies if player not full.
     */
    public void update(Player player) {
        for (int i = 0; i < max; i++) {
            if (enemies[i] != null) {
                enemies[i].updateEntity();
                if (!enemies[i].isAlive()) {
                    if (currentNumber <= 1) { // If last fish dont check for
                                              // bubbles
                        enemies[i] = null;
                        currentNumber--;
                    } else if (!enemies[i].hasBubbles()) {
                        enemies[i] = null;
                        currentNumber--;
                    }
                }
            }
        }

        updateCount++;
        if (updateCount >= delay) {
            updateCount = 0;
            if (currentNumber <= desiredNumber() && !player.isFull()) {
                spawnEnemy();
            }
        }
    }

    /**
     * Draw.
     */
    public void draw(Graphics2D graphic) {
        for (int i = 0; i < max; i++) {
            if (enemies[i] != null) {
                enemies[i].drawEntity(graphic);
            }
        }
    }

    protected void spawnEnemy() {
        for (int i = 0; i < max; i++) {
            if (enemies[i] == null) {
                enemies[i] = generateEnemy();
                currentNumber++;
                return;
            }
        }
    }

    protected int desiredNumber() {
        int min = OptionsHandler.getInstance().getMinEnemies();
        int max = OptionsHandler.getInstance().getMaxEnemies() + 1;
        int number = generator.nextInt(max - min) + min; // 0 inclusive and max
        // exclusive
        return number;
    }

    protected Enemy generateEnemy() {
        Enemy enemy = new Trout();
        Random rn = new Random();
        int i = rn.nextInt(32)+1;
        if(16 < i && i <= 20){
            enemy = new Stingray();
        }
        if(20 < i && i <= 25){
            enemy = new Shark();
        }
        if(i == 26){
            enemy = new Swordfish();
        }
        if(26 < i && i <= 32){
            enemy = new Dunkleosteus();
        }
        return enemy;
    }

    public Enemy[] getEnemies() {
        return enemies;
    }

    public int getNumberEnemies() {
        return currentNumber;
    }

}
