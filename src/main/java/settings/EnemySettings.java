package settings;

/**
 * The EnemySettings class is responsible for the global enemy settings.
 */
public class EnemySettings {

    private static final EnemySettings instance = new EnemySettings();
    private int spawnRate;
    private int minEnemies;
    private int maxEnemies;

    /**
     * Creates an instance of the global enemy settings.
     */
    private EnemySettings(){
        spawnRate = 100;
        minEnemies = 10;
        maxEnemies = 14;
    }

    /**
     * Returns an instance of the global enemy settings.
     * @return global enemy settings.
     */
    public static EnemySettings getInstance(){
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
        return minEnemies;
    }

    /**
     * Returns the desired maximum number of enemies.
     * Which should be spawned.
     * @return maximum number of enemies.
     */
    public int getMaxEnemies(){
        return maxEnemies;
    }

    /**
     * Updates the spawn rate.
     * @param spawnRate the spawn rate.
     */
    public void setSpawnRate(int spawnRate){
        this.spawnRate = spawnRate;
    }

    /**
     * Updates the minimum number of enemies.
     * @param minEnemies minimum number of enemies
     */
    public void setMinEnemies(int minEnemies){
        this.minEnemies = minEnemies;
    }

    /**
     * Updates the maximum number of enemies.
     * @param maxEnemies maximum number of enemies.
     */
    public void setMaxEnemies(int maxEnemies){
        this.maxEnemies = maxEnemies;
    }

}
