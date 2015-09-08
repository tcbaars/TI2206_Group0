package handlers;

import java.awt.Graphics2D;
import java.util.Random;

import entities.Enemy;
import entities.Player;
import entities.Trout;

public class EnemyHandler {

    private Random generator;
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

    public void update(Player player) {
        for (int i = 0; i < max; i++) {
            if (enemies[i] != null) {
                enemies[i].updateEntity();
                if (!enemies[i].isAlive()) {
                	if(currentNumber <= 1){ 	//If last fish dont check for bubbles
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
        	if (currentNumber <= desiredNumber()) {
        		if(!player.isFull()){
        			spawnEnemy();
        		}
            }
        }
    }

    public void draw(Graphics2D g) {
        for (int i = 0; i < max; i++) {
            if (enemies[i] != null) {
                enemies[i].drawEntity(g);
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
    
    public int getNumberEnemies(){
    	return  currentNumber;
    }

}
