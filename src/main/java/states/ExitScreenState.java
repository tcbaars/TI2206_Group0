package states;

import java.awt.Graphics2D;

import gui.MainFrame;
import statemanagers.StateManager;

public class ExitScreenState extends ScreenState{

    public ExitScreenState(StateManager stateManager){
        super(stateManager);
    }

    public void drawToScreen(Graphics2D screen) {
    }

    @Override
    public void update() {
        MainFrame.getInstance().exit();
    }
}
