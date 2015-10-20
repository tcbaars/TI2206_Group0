package games;

import entities.Player;
import enumerations.Directions;

public interface SinglePlayerGame extends Game{
    Player getPlayer();
    void movePlayer(Directions direction);
}
