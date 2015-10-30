package layers.gamelayers;

import games.SinglePlayerGame;

/**
 * The GameLostLayer class is an extension of the GameOverLayer class,
 * and it represents the specific set of graphic elements that make up the game over screen,
 * when the player has lost the game.
 */
public class GameLostLayer extends GameOverLayer{

    /**
     * Initialises the game lost screen.
     * @param singlePlayerGame the game lost.
     */
    public GameLostLayer(SinglePlayerGame singlePlayerGame) {
        super("Game Lost", singlePlayerGame);
    }
}
