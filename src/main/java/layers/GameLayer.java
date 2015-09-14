package layers;

import java.awt.Graphics2D;

import enumerations.Direction;
import enumerations.Key;
import handlers.GameHandler;
import handlers.SinglePlayerGameHandler;

public class GameLayer extends Layer {

    private GameHandler game;
    private Boolean keys[] = {false, false, false, false}; // up, left, down, right

    public GameLayer() {
        game = new SinglePlayerGameHandler();
    }

    @Override
    public void update() {
        if (!isActive()) {
            if (game.isPaused()) {
                return;
            } else {
                activate();
                setVisible(true);
            }
        }

        if (game.isPaused()) {
            if (game.isGameOver()) {
                addLayer(new FinishLayer(game.getScore(), game.isGameWon()));
                removeLayer();
            }
            deactivate();
            setVisible(false);
            addLayer(new PauseLayer(game));
        } else {
            if (keys[0]) {
                game.move(Direction.UP);
            }
            if (keys[1]) {
                game.move(Direction.LEFT);
            }
            if (keys[2]) {
                game.move(Direction.DOWN);
            }
            if (keys[3]) {
                game.move(Direction.RIGHT);
            }
            game.update();
        }
    }

    @Override
    public Graphics2D draw(Graphics2D graphic) {
        game.draw(graphic);
        return graphic;
    }

    @Override
    public void keyPressed(Key key) {
        switch (key) {
            case UP:
                keys[0] = true;
                break;
            case DOWN:
                keys[2] = true;
                break;
            case LEFT:
                keys[1] = true;
                break;
            case RIGHT:
                keys[3] = true;
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(Key key) {
        switch (key) {
            case UP:
                keys[0] = false;
                break;
            case DOWN:
                keys[2] = false;
                break;
            case LEFT:
                keys[1] = false;
                break;
            case RIGHT:
                keys[3] = false;
                break;
            case ESC:
                game.pause();
                break;
            default:
                break;
        }
    }
}
