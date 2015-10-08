package states.gamestates;

import enumerations.States;
import game.Game;
import keys.Key;
import keys.TypedKey;
import settings.DebugSettings;
import settings.MusicSettings;
import settings.SoundSettings;
import statemanagers.StateManager;
import states.State;
import tools.resourcetools.MusicPlayer;

public abstract class GameState implements State{
    private StateManager stateManager;
    public GameState(){
    }
    public void setStateManager(StateManager stateManager){
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
