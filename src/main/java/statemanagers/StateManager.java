package statemanagers;

import java.awt.Graphics2D;

import enumerations.States;
import game.Game;
import keys.Key;
import keys.TypedKey;
import states.ExitScreenState;
import states.GameScreenState;
import states.HighScoresScreenState;
import states.InstructionsScreenState;
import states.State;
import states.TitleScreenState;

public class StateManager {

    private Game game;
    private State currentState;

    public StateManager(){
        setCurrentState(States.TITLE_SCREEN);
    }

    public void setCurrentState(States state){
        switch (state) {
            case TITLE_SCREEN:
                currentState = new TitleScreenState(this);
                break;
            case GAME_SCREEN:
                currentState = new GameScreenState(this, game);
                break;
            case SCORE_SCREEN:
                currentState = new HighScoresScreenState(this);
                break;
            case INSTRUCTIONS_SCREEN:
                currentState = new InstructionsScreenState(this);
                break;
            case EXIT_SCREEN:
                currentState = new ExitScreenState(this);
                break;
        }
    }

    public void launchNewGame(Game game){
        this.game = game;
        game.restart();
        setCurrentState(States.GAME_SCREEN);
    }

    public void update(){
        currentState.update();
    }

    public void drawToScreen(Graphics2D screen){
        currentState.drawToScreen(screen);
    }

    public void handleKeyPressed(Key key){
        if (key != null) {
            currentState.handleKeyPressed(key);
        }
    }

    public void handleKeyReleased(Key key){
        if (key != null) {
            currentState.handleKeyReleased(key);
        }
    }

    public void handleKeyTyped(TypedKey key){
        if (key != null) {
            currentState.handleKeyTyped(key);
        }
    }
}
