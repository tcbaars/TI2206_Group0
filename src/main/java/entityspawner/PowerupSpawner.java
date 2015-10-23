package entityspawner;

import entities.Entity;
import entities.Powerup;
import entities.powerups.Speedup;
import settings.PowerupSettings;
import tools.Timer;
import tools.entitytools.Generator;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Adriaan on 23-10-2015.
 */
public class PowerupSpawner implements EntitySpawner {

    private int max;
    private Entity[] powerups;
    private int currentNumber;
    private Timer spawnDelay;

    public PowerupSpawner() {
        max = PowerupSettings.getInstance().getMaxEnemies();
        powerups = new Powerup[max + 1];
        currentNumber = 0;
        spawnDelay = new Timer(PowerupSettings.getInstance().getSpawnRate());
    }

    public void update(){
        for (int i = 0; i < max; i++) {
            if (powerups[i] != null) {
                powerups[i].update();
                if (!powerups[i].isAlive() && !powerups[i].hasSubEntities()) {
                    powerups[i] = null;
                    currentNumber--;
                }
            }
        }
        spawnDelay.tick();
        if (spawnDelay.hasCompleted()) {
            spawnDelay.reset();
            spawnPowerup();
        }
    }

    private void spawnPowerup(){
        for (int i = 0; i < max; i++){
            if (powerups[i] == null) {
                powerups[i] = generatePowerup();
                currentNumber++;
                return;
            }
        }
    }

    private Powerup generatePowerup(){
        Powerup powerup;
        switch (Generator.generateInteger(0)) {
            default:
                powerup = new Speedup();
                break;
        }
        return powerup;
    }

    public ArrayList<Entity> getEntities(){
        return new ArrayList<Entity>(Arrays.asList(powerups));
    }

    public boolean isActive() {
        return (currentNumber != 0);
    }
}
