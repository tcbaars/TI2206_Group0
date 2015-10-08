package states.gamestates.singleplayergamestates;

import enumerations.GameSounds;
import games.SinglePlayerGame;
import layers.gamelayers.GameOverLayer;
import layers.gamelayers.GameWonLayer;
import tools.resourcetools.SoundPlayer;

public class GameWonState extends GameOverState{

    public GameWonState(SinglePlayerGame game) {
        super(game);
    }

    @Override
    protected GameOverLayer createLayer(SinglePlayerGame game){
        SoundPlayer.getInstance().playSound(GameSounds.WIN);
        return new GameWonLayer(game);
    }
}
