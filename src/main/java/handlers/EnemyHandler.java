package handlers;

import java.awt.Graphics2D;
import java.util.Random;

import entities.Enemy;
import entities.Trout;

public class EnemyHandler {

    protected Random generator;
    private Enemy enemies[];
    private int max;
    private int currentNumber;

    private int updateCount;
    private int delay;

    public EnemyHandler() {
        generator = new Random(System.currentTimeMillis());
        max = OptionsHandler.getInstance().getMaxEnemies() + 1;
        enemies = new Enemy[max];
        currentNumber = 0;
        updateCount = 0;
        delay = 10;
    }

    public void update() {
        for (int i = 0; i < max; i++) {
            if (enemies[i] != null) {
                enemies[i].update();
                if (!enemies[i].isAlive()) {
                    if (!enemies[i].hasBubbles()) {
                        enemies[i] = null;
                        currentNumber--;
                    }
                }
            }
        }

        updateCount++;
        if (updateCount >= delay) {
            updateCount = 0;
            if (currentNumber <= desiredNumber()) {
                spawnEnemy();
            }
        }
    }

    public void draw(Graphics2D g) {
        for (int i = 0; i < max; i++) {
            if (enemies[i] != null) {
                enemies[i].draw(g);
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
        int x = generator.nextInt(max - min) + min; // 0 inclusive and max
                                                    // exclusive
        return x;
    }

    protected Enemy generateEnemy() {
        Enemy enemy = new Trout();
        return enemy;
    }

    public Enemy[] getEnemies() {
        return enemies;
    }

}
