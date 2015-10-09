package games;

import entities.Player;
import entities.players.Guppy;
import enumerations.Directions;

public abstract class SinglePlayerGameBase extends GameBase implements SinglePlayerGame{

    private Player player;

    public SinglePlayerGameBase(){
        super();
    }

    @Override
    public void restart(){
        super.restart();
        player = new Guppy();
    }

    public Player getPlayer(){
        return player;
    }

    public void movePlayer(Directions direction) {
        player.move(direction);
    }
}
