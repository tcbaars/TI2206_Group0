package states.gamestates.singleplayergamestates;

import java.awt.Graphics2D;

import games.singleplayergames.ClassicGame;
import layers.gamelayers.singleplayergamelayers.ClassicGameLayer;
import states.gamestates.SinglePlayerGameState;

public class ClassicGameState extends SinglePlayerGameState{

    private ClassicGameLayer gameLayer;

    public ClassicGameState(ClassicGame game) {
        super(game);
        gameLayer = new ClassicGameLayer(game);
    }

    public void drawToScreen(Graphics2D screen) {
        gameLayer.drawToScreen(screen);
    }

}
