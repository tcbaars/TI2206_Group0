package game.singleplayergames;

import java.util.ArrayList;
import java.util.Iterator;

import entities.Entity;
import entityspawner.EnemySpawner;
import enumerations.GameSounds;
import game.SinglePlayerGameBase;
import states.gamestates.GameState;
import states.gamestates.singleplayergamestates.ClassicGameState;
import states.gamestates.singleplayergamestates.GameLostState;
import states.gamestates.singleplayergamestates.GameWonState;
import states.gamestates.singleplayergamestates.PausedState;
import tools.entitytools.Collisions;
import tools.resourcetools.SoundLoader;

public class ClassicGame extends SinglePlayerGameBase{

    private boolean gameWon;
    private EnemySpawner enemies;
    public ClassicGame(){
        super();
        SoundLoader.getInstance().loadSound(GameSounds.CHOMP);
    }

    @Override
    public void restart() {
        gameWon = false;
        enemies = new EnemySpawner();
        super.restart();
    }

    public void update() {
        if (!isPaused()) {
            handleCollisions();
            getPlayer().update();
            if (getPlayer().isAlive()){
                if (getPlayer().isFull()){
                    if (!enemies.isActive()){
                        gameWon = true;
                        setGameOver();
                    } else {
                        enemies.setFinalStage();
                    }
                }
            } else {
                gameWon = false;
                setGameOver();
            }
            enemies.update();
        }
    }
    private void handleCollisions(){
        Collisions.handleCollsions(getPlayer(), enemies.getEntities().iterator());
    }
    public Iterator<Entity> getEntities() {
        ArrayList<Entity> entities =  new ArrayList<Entity>();
        entities.add(getPlayer());
        entities.addAll(enemies.getEntities());
        return entities.iterator();
    }

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
