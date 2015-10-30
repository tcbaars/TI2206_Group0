package games.singleplayergames;

import java.util.ArrayList;
import java.util.Iterator;

import entities.Entity;
import entityspawner.EnemySpawner;
import entityspawner.PowerupSpawner;
import enumerations.GameSounds;
import games.SinglePlayerGameBase;
import states.gamestates.GameState;
import states.gamestates.singleplayergamestates.ClassicGameState;
import states.gamestates.singleplayergamestates.GameLostState;
import states.gamestates.singleplayergamestates.GameWonState;
import states.gamestates.singleplayergamestates.PausedState;
import tools.entitytools.Collisions;
import tools.resourcetools.SoundLoader;

/**
 * The ClassicGame class represents a single player classic game mode.
 */
public class ClassicGame extends SinglePlayerGameBase{

    private boolean gameWon;
    private EnemySpawner enemies;
    private PowerupSpawner powerups;

    /**
     * Create a new single player classic game.
     */
    public ClassicGame(){
        super();
        // Load resources
        SoundLoader.getInstance().loadSound(GameSounds.CHOMP);
    }

    @Override
    public void restart() {
        gameWon = false;
        enemies = new EnemySpawner();
        powerups = new PowerupSpawner();
        super.restart();
    }

    public void update() {
        // Only perform operations if the game is running
        if (!isPaused()) {
            // Handle collisions
            handleCollisions();
            // Update player
            getPlayer().update();
            if (getPlayer().isAlive()){
                if (getPlayer().isFull()){
                    if (!enemies.isActive()){
                        /*
                         * If the player is alive,
                         * and full,
                         * and all the enemies are gone,
                         * then game has been won
                         */
                        gameWon = true;
                        setGameOver();
                    } else {
                        /*
                         * If the player is full,
                         * then enter the final stage
                         */
                        enemies.setFinalStage();
                    }
                }
            } else {
                /*
                 *  If the player has been eaten,
                 *  then then game is lost
                 */
                gameWon = false;
                setGameOver();
            }
            // Update the enemies
            enemies.update();
            // Update the power-ups
            powerups.update();
        }
    }

    /**
     * Handles the collisions between the player and the other entities.
     */
    private void handleCollisions(){
        Collisions.handleCollsions(getPlayer(), enemies.getEntities().iterator());
        Collisions.handleCollsions(getPlayer(), powerups.getEntities().iterator());
    }

    public Iterator<Entity> getEntities() {
        ArrayList<Entity> entities =  new ArrayList<Entity>();
        // Add player
        entities.add(getPlayer());
        // Add enemies
        entities.addAll(enemies.getEntities());
        // Add power-ups
        entities.addAll(powerups.getEntities());
        return entities.iterator();
    }

    /**
     * Whether or not the game has been won.
     * @return <code>true</code> if and only if the game has been won, otherwise <code>false</code>.
     */
    public boolean isGameWon(){
        return gameWon;
    }

    @Override
    protected GameState runningGameState() {
        return new ClassicGameState(this);
    }

    @Override
    protected GameState pausedGameState() {
        return new PausedState(this);
    }

    @Override
    protected GameState gameOverGameState() {
        if (isGameWon()) {
            return new GameWonState(this);
        } else {
            return new GameLostState(this);
        }
    }
}
