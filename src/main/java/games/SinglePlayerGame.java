package games;

import entities.Player;
import enumerations.Directions;

/**
 * Interface for a SinglePlayerGame
 */
public interface SinglePlayerGame extends Game{
    Player getPlayer();
    void movePlayer(Directions direction);
}
