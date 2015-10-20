package entityspawner;

import java.util.ArrayList;
import java.util.Arrays;

import entities.Enemy;
import entities.Entity;
import entities.enemies.Dunkleosteus;
import entities.enemies.Shark;
import entities.enemies.Stingray;
import entities.enemies.Swordfish;
import entities.enemies.Trout;
import settings.EnemySettings;
import tools.Timer;
import tools.entitytools.Generator;

public class EnemySpawner implements EntitySpawner{
    private boolean finalStage;
    private Entity[] enemies;
    private int min;
    private int max;
    private int currentNumber;
    private Timer spawnDelay;

    public EnemySpawner(){
        finalStage = false;
        min = EnemySettings.getInstance().getMinEnemies();
        max = EnemySettings.getInstance().getMaxEnemies();
        enemies = new Enemy[max + 1];
        currentNumber = 0;
        spawnDelay = new Timer(EnemySettings.getInstance().getSpawnRate());
    }

    public void update(){
        for (int i = 0; i < max; i++) {
            if (enemies[i] != null) {
                enemies[i].update();
                if (!enemies[i].isAlive() && !enemies[i].hasSubEntities()) {
                    enemies[i] = null;
                    currentNumber--;
                }
            }
        }
        spawnDelay.tick();
        if (spawnDelay.hasCompleted()) {
            spawnDelay.reset();
            if (!finalStage) {
                if (currentNumber < getDesiredNumber()) {
                    spawnEnemy();
                }
            }
        }
    }
    public int getNumberEnemies(){
        return currentNumber;
    }
    public boolean isFinalStage(){
        return finalStage;
    }
    public void setFinalStage(){
        finalStage = true;
    }
    public ArrayList<Entity> getEntities(){
        return new ArrayList<Entity>(Arrays.asList(enemies));
    }
    private int getDesiredNumber(){
        return Generator.generateInteger(min, max);
    }
    private void spawnEnemy(){
        for (int i = 0; i < max; i++){
            if (enemies[i] == null) {
                enemies[i] = generateEnemy();
                currentNumber++;
                return;
            }
        }
    }
    private Enemy generateEnemy(){
        Enemy enemy;
        switch (Generator.generateInteger(4)) {
            case 0:
                enemy = new Trout();
                break;
            case 1:
                enemy = new Dunkleosteus();
                break;
            case 2:
                enemy = new Shark();
                break;
            case 3:
                enemy = new Stingray();
                break;
            default:
                enemy = new Swordfish();
                break;
        }
        return enemy;
    }

    public boolean isActive() {
        return !(finalStage && (currentNumber == 0));
    }
}
