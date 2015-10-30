package layers.gamelayers.singleplayergamelayers;

import java.util.Iterator;

import entities.Entity;
import games.singleplayergames.ClassicGame;
import layers.gamelayers.SinglePlayerGameLayer;

/**
 * The ClassicGameLayer class represents the default set of graphic elements for a single player game of the classic game mode.
 */
public class ClassicGameLayer extends SinglePlayerGameLayer{

    private ClassicGame game;

    /**
     * Creates a new classic game layer for the specified classic game.
     * @param game the classic game.
     */
    public ClassicGameLayer(ClassicGame game){
        super();
        this.game = game;
    }

    @Override
    public int getNumberFishEaten() {
        return game.getPlayer().getNumberFishEaten();
    }

    @Override
    public int getScore() {
        return game.getPlayer().getCurrentScore();
    }

    @Override
    public Iterator<Entity> getEntities() {
        return game.getEntities();
    }

}
