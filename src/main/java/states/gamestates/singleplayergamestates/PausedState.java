package states.gamestates.singleplayergamestates;

import java.awt.Graphics2D;

import enumerations.GameSounds;
import enumerations.States;
import game.Game;
import keys.Key;
import layers.gamelayers.PauseLayer;
import states.gamestates.GameState;
import tools.resourcetools.SoundLoader;
import tools.resourcetools.SoundPlayer;

public class PausedState  extends GameState{

    private Game game;
    private PauseLayer pauseLayer;
    public PausedState(Game game) {
        super(game);
        this.game = game;
        pauseLayer = new PauseLayer();
        SoundLoader.getInstance().loadSound(GameSounds.NAVIGATE);
        SoundLoader.getInstance().loadSound(GameSounds.SELECT);
    }

    public void drawToScreen(Graphics2D screen) {
        pauseLayer.drawToScreen(screen);
    }
    private void navigateUp(){
        SoundPlayer.getInstance().playSound(GameSounds.NAVIGATE);
        pauseLayer.navigateUp();
    }
    private void navigateDown(){
        SoundPlayer.getInstance().playSound(GameSounds.NAVIGATE);
        pauseLayer.navigateDown();
    }
    private void select(){
        SoundPlayer.getInstance().playSound(GameSounds.SELECT);
        switch (pauseLayer.getSelection()) {
            case RESUME:
                resume();
                break;
            case TITLE_SCREEN:
                setCurrentState(States.TITLE_SCREEN);
                break;
            case EXIT:
                setCurrentState(States.EXIT_SCREEN);
                break;
            default:
                break;
        }
    }
    private void resume(){
        game.resume();
    }
    public void handleKeyReleased(Key key){
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
                resume();
                break;
            default:
                super.handleKeyReleased(key);
        }
    }
}
