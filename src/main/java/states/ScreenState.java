package states;

import enumerations.States;
import games.Game;
import keys.Key;
import keys.TypedKey;
import settings.DebugSettings;
import settings.MusicSettings;
import settings.SoundSettings;
import statemanagers.StateManager;
import tools.resourcetools.MusicPlayer;

public abstract class ScreenState implements State{
    private StateManager stateManager;
    public ScreenState(StateManager stateManager){
        this.stateManager = stateManager;
    }
    public void setCurrentState(States state){
        stateManager.setCurrentState(state);
    }
    public void launchNewGame(Game game){
        stateManager.launchNewGame(game);
    }
    public void update(){
        MusicPlayer.getInstance().playMusic();
    }
    public void handleKeyPressed(Key key) {
    }
    public void handleKeyReleased(Key key){
        switch (key.getKey()) {
            case SOUND:
                SoundSettings.getInstance().toggleSound();
                break;
            case MUSIC:
                MusicSettings.getInstance().toggleMusic();
                break;
            case DEBUG:
                DebugSettings.getInstance().toggleDebugMode();
                break;
            default:
                break;
        }
    }
    public void handleKeyTyped(TypedKey key) {
    }
}
