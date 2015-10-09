package layers.gamelayers.singleplayergamelayers;

import java.util.Iterator;

import entities.Entity;
import games.singleplayergames.ClassicGame;
import layers.gamelayers.SinglePlayerGameLayer;

public class ClassicGameLayer extends SinglePlayerGameLayer{

    private ClassicGame game;

    public ClassicGameLayer(ClassicGame game){
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
