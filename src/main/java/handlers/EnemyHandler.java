package handlers;

import java.awt.Graphics2D;
import java.util.Random;

import entities.Dunkleosteus;
import entities.Enemy;
import entities.Player;
import entities.Shark;
import entities.Stingray;
import entities.Swordfish;
import entities.Trout;

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
                    if (currentNumber <= 1) {
                        // If last fish dont check for bubbles
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
        Enemy enemy = null;
        switch (generator.nextInt(5)) {
        case 0:
            enemy = new Trout();
            break;
        case 1:
            enemy = new Shark();
            break;
        case 2:
            enemy = new Swordfish();
            break;
        case 3:
            enemy = new Dunkleosteus();
            break;
        case 4:
            enemy = new Stingray();
            break;

        default:
            break;
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
