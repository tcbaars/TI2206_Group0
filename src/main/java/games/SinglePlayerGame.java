package games;

import entities.Player;
import enumerations.Directions;

/**
 * The SinglePlayerGame interface represents the constraints of a generic single player game.
 */
public interface SinglePlayerGame extends Game{

    /**
     * Returns the player.
     * @return the player.
     */
    Player getPlayer();

    /**
     * Moves the player in the specified direction.
     * @param direction the desire direction.
     */
    void movePlayer(Directions direction);
}
