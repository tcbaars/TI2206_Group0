package settings;

/**
 * Created by Adriaan on 23-10-2015.
 */
public class PowerupSettings {

    private static final PowerupSettings instance = new PowerupSettings();
    private int spawnRate;
    private static final int minPowerups = 0;
    private static final int maxPowerups = 2;

    /**
     * Creates an instance of the global enemy settings.
     */
    private PowerupSettings(){
        spawnRate = 1;
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
}
