package games;

import entities.Player;
import enumerations.Directions;

public interface SinglePlayerGame extends Game{
    public Player getPlayer();
    public void movePlayer(Directions direction);
}
