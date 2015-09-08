package layers;

import java.awt.Graphics2D;

import enumerations.Direction;
import enumerations.Key;
import handlers.GameHandler;
import handlers.SinglePlayerGameHandler;

public class GameLayer extends Layer {

    // private ArrayList<Entity> entities = new ArrayList<Entity>();

    private GameHandler game;

    public GameLayer() {
        game = new SinglePlayerGameHandler();
    }

    @Override
    public void update() {
        game.update();
        updateNext();

    }

    @Override
    public void draw(Graphics2D g) {
        game.draw(g);
        drawNext(g);

    }

    @Override
    public void keyPressed(Key e) {
        switch (e) {
        case UP:
            game.move(Direction.UP);
            break;
        case DOWN:
            game.move(Direction.DOWN);
            break;
        case LEFT:
            game.move(Direction.LEFT);
            break;
        case RIGHT:
            game.move(Direction.RIGHT);
            break;
        }
        passKeyPress(e);

    }

    @Override
    public void keyReleased(Key e) {
        passKeyRelease(e);

    }
}
