package states.gamestates;

import enumerations.Directions;
import games.SinglePlayerGame;
import keys.Key;

public abstract class SinglePlayerGameState extends GameState{

    private SinglePlayerGame game;
    private Boolean keys[] = {false, false, false, false}; // up, left, down, right

    public SinglePlayerGameState(SinglePlayerGame game) {
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
        if (keys[0]) {
            moveUp();
        }
        if (keys[1]) {
            moveLeft();
        }
        if (keys[2]) {
            moveDown();
        }
        if (keys[3]) {
            moveRight();
        }
        game.update();
        super.update();
    }
    public void handleKeyPressed(Key key) {
        switch (key.getKey()) {
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
                super.handleKeyPressed(key);
        }
    }
    public void handleKeyReleased(Key key){
        switch (key.getKey()) {
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
                pause();
                break;
            default:
                super.handleKeyReleased(key);
        }
    }

}
