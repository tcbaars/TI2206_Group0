package layers.gamelayers;

import games.SinglePlayerGame;

public class GameLostLayer extends GameOverLayer{

    public GameLostLayer(SinglePlayerGame singlePlayerGame) {
        super("Game Lost", singlePlayerGame);
    }
}
