package states;

import java.awt.Graphics2D;

import gui.MainFrame;
import keys.Key;
import keys.TypedKey;
import statemanagers.StateManager;

public class ExitScreenState extends ScreenState{

    public ExitScreenState(StateManager gameStateManager){
        super(gameStateManager);
    }

    public void drawToScreen(Graphics2D screen) {
    }

    public void update() {
        MainFrame.getInstance().exit();
    }

    public void handleKeyPressed(Key key) {
    }

    public void handleKeyTyped(TypedKey key) {
    }

}
