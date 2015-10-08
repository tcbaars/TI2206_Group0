package states.gamestates.singleplayergamestates;

import enumerations.GameSounds;
import games.SinglePlayerGame;
import games.SinglePlayerGameBase;
import layers.gamelayers.GameLostLayer;
import layers.gamelayers.GameOverLayer;
import tools.resourcetools.SoundPlayer;

public class GameLostState extends GameOverState{

    public GameLostState(SinglePlayerGameBase singlePlayerGame) {
        super(singlePlayerGame);
    }

    @Override
    protected GameOverLayer createLayer(SinglePlayerGame game){
        SoundPlayer.getInstance().playSound(GameSounds.LOSE);
        return new GameLostLayer(game);
    }
}
