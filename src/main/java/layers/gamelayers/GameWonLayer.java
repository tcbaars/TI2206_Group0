package layers.gamelayers;

import games.SinglePlayerGame;

/**
 * The GameWonLayer class is an extension of the GameOverLayer class,
 * and it represents the specific set of graphic elements that make up the game over screen,
 * when the player has won the game.
 */
public class GameWonLayer extends GameOverLayer{

    /**
     * Initialises the game won screen.
     * @param singlePlayerGame the game won.
     */
    public GameWonLayer(SinglePlayerGame singlePlayerGame) {
        super("GAME WON", singlePlayerGame);
    }
}
