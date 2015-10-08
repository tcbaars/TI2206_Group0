package states;

import java.awt.Graphics2D;

import games.Game;
import keys.Key;
import keys.TypedKey;
import statemanagers.StateManager;
import states.gamestates.GameState;

public class GameScreenState implements State{

    private StateManager stateManager;
    private Game game;
    private GameState currentState;

    public GameScreenState(StateManager stateManager, Game game) {
        this.stateManager = stateManager;
        this.game = game;
    }

    public void drawToScreen(Graphics2D screen) {
        game.getCurrentState().drawToScreen(screen);
    }

    public void update() {
        currentState = game.getCurrentState();
        currentState.setStateManager(stateManager);
        currentState.update();
    }

    public void handleKeyPressed(Key key) {
        if (key != null) {
            currentState.handleKeyPressed(key);
        }
    }

    public void handleKeyReleased(Key key) {
        currentState.handleKeyReleased(key);
    }

    public void handleKeyTyped(TypedKey key) {
        currentState.handleKeyTyped(key);
    }
}
