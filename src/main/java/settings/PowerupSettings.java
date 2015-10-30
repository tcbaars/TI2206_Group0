package settings;

/**
 * Created by Adriaan on 23-10-2015.
 */
public class PowerupSettings {

    private static final PowerupSettings instance = new PowerupSettings();
    private int spawnRate;
    private static final int minPowerups = 0;
    private static final int maxPowerups = 2;
    private static final int increment = 2;
    private static boolean powerupIsActive = false;
    private static final int powerupRate = 60*10;
    private static final int despawnRate = 60*5;

    /**
     * Creates an instance of the global enemy settings.
     */
    private PowerupSettings(){
        spawnRate = 60*20;
    }

    /**
     * Returns an instance of the global enemy settings.
     * @return global enemy settings.
     */
    public static PowerupSettings getInstance(){
        return instance;
    }

    /**
     * Returns the enemy spawn rate.
     * Which is the desired number of ticks that is needed to pass,
     * for an enemy to be spawned.
     * @return enemy spawn rate.
     */
    public int getSpawnRate(){
        return spawnRate;
    }

    /**
     * Returns the desired minimum number of enemies.
     * Which should be spawned.
     * @return minimum number of enemies.
     */
    public int getMinEnemies(){
        return minPowerups;
    }

    /**
     * Returns the desired maximum number of enemies.
     * Which should be spawned.
     * @return maximum number of enemies.
     */
    public int getMaxEnemies(){
        return maxPowerups;
    }

    public boolean powerupIsActive() {
        return powerupIsActive;
    }

    public void togglePowerup() {
        powerupIsActive = !powerupIsActive;
    }

    public int getIncrement() {
        return increment;
    }

    public int getPowerupRate(){
        return powerupRate;
    }

    public int getDespawnRate(){
        return despawnRate;
    }
}
