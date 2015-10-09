package states;

import java.awt.Graphics2D;

import enumerations.GameSounds;
import enumerations.States;
import keys.Key;
import layers.InstructionsLayer;
import statemanagers.StateManager;
import tools.resourcetools.SoundLoader;
import tools.resourcetools.SoundPlayer;

public class InstructionsScreenState extends ScreenState{

    private InstructionsLayer instructionsLayer;

    public InstructionsScreenState(StateManager stateManager) {
        super(stateManager);
        instructionsLayer = new InstructionsLayer();
        SoundLoader.getInstance().loadSound(GameSounds.SELECT);
    }

    public void drawToScreen(Graphics2D screen) {
        instructionsLayer.drawToScreen(screen);
    }

    private void select(){
        switch (instructionsLayer.getSelection()) {
            case TITLE_SCREEN:
                back();
                break;
            default:
                break;
        }
    }
    private void back(){
        SoundPlayer.getInstance().playSound(GameSounds.SELECT);
        setCurrentState(States.TITLE_SCREEN);
    }
    public void handleKeyReleased(Key key){
        switch (key.getKey()) {
            case ENTER:
                select();
                break;
            case ESC:
                back();
                break;
            default:
                super.handleKeyReleased(key);
                break;
        }
    }

}
