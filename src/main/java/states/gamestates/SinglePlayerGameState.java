package states.gamestates;

import enumerations.Directions;
import games.SinglePlayerGameBase;
import keys.Key;

public abstract class SinglePlayerGameState extends GameState{

    private SinglePlayerGameBase game;

    public SinglePlayerGameState(SinglePlayerGameBase game) {
        super();
        this.game = game;
    }
    private void moveUp(){
        game.getPlayer().move(Directions.UP);
    }
    private void moveDown(){
        game.getPlayer().move(Directions.DOWN);
    }
    private void moveLeft(){
        game.getPlayer().move(Directions.LEFT);
    }
    private void moveRight(){
        game.getPlayer().move(Directions.RIGHT);
    }
    private void pause(){
        game.pause();
    }
    public void update(){
        game.update();
        super.update();
    }
    public void handleKeyPressed(Key key) {
        switch (key.getKey()) {
            case UP:
                moveUp();
                break;
            case DOWN:
                moveDown();
                break;
            case LEFT:
                moveLeft();
                break;
            case RIGHT:
                moveRight();
                break;
            default:
                super.handleKeyPressed(key);
        }
    }
    public void handleKeyReleased(Key key){
        switch (key.getKey()) {
            case ESC:
                pause();
                break;
            default:
                super.handleKeyReleased(key);
        }
    }

}
