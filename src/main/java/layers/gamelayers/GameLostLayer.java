package layers.gamelayers;

import game.SinglePlayerGame;

public class GameLostLayer extends GameOverLayer{

    public GameLostLayer(SinglePlayerGame singlePlayerGame) {
        super("Game Lost", singlePlayerGame);
    }
}
