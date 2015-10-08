package states;

import java.awt.Graphics2D;

import enumerations.GameSounds;
import enumerations.States;
import games.singleplayergames.ClassicGame;
import keys.Key;
import layers.TitleLayer;
import statemanagers.StateManager;
import tools.resourcetools.SoundLoader;
import tools.resourcetools.SoundPlayer;

public class TitleScreenState extends ScreenState{

    private TitleLayer titleLayer;

    public TitleScreenState(StateManager stateManager){
        super(stateManager);
        titleLayer = new TitleLayer();
        SoundLoader.getInstance().loadSound(GameSounds.NAVIGATE);
        SoundLoader.getInstance().loadSound(GameSounds.SELECT);
    }

    public void drawToScreen(Graphics2D screen) {
        titleLayer.drawToScreen(screen);
    }

    private void navigateUp(){
        SoundPlayer.getInstance().playSound(GameSounds.NAVIGATE);
        titleLayer.navigateUp();
    }

    private void navigateDown(){
        SoundPlayer.getInstance().playSound(GameSounds.NAVIGATE);
        titleLayer.navigateDown();
    }

    private void select(){
        SoundPlayer.getInstance().playSound(GameSounds.SELECT);
        switch (titleLayer.getSelection()) {
            case CLASSIC:
                launchNewGame(new ClassicGame());
                break;
            case HIGH_SCORES:
                setCurrentState(States.SCORE_SCREEN);
                break;
            case INSTRUCTIONS:
                setCurrentState(States.INSTRUCTIONS_SCREEN);
                break;
            case EXIT:
                exit();
                break;
            default:
                break;
        }
    }

    private void exit(){
        setCurrentState(States.EXIT_SCREEN);
    }

    @Override
    public void handleKeyReleased(Key key) {
        switch (key.getKey()) {
            case UP: case LEFT:
                navigateUp();
                break;
            case DOWN: case RIGHT:
                navigateDown();
                break;
            case ENTER:
                select();
                break;
            case ESC:
                exit();
                break;
            default:
                super.handleKeyReleased(key);
                break;
        }
    }
}
