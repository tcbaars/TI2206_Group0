package states.gamestates.singleplayergamestates;

import java.awt.Graphics2D;

import game.singleplayergames.ClassicGame;
import layers.gamelayers.singleplayergamelayers.ClassicGameLayer;
import states.gamestates.SinglePlayerGameBaseState;

public class ClassicGameState extends SinglePlayerGameBaseState{

    private ClassicGameLayer gameLayer;

    public ClassicGameState(ClassicGame game) {
        super(game);
        gameLayer = new ClassicGameLayer(game);
    }

    public void drawToScreen(Graphics2D screen) {
        gameLayer.drawToScreen(screen);
    }

}
