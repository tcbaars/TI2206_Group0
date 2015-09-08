package layers;

import java.awt.Graphics2D;

import enumerations.Direction;
import enumerations.Key;
import handlers.GameHandler;
import handlers.SinglePlayerGameHandler;

public class GameLayer extends Layer {

    private GameHandler game;

    public GameLayer() {
        game = new SinglePlayerGameHandler();
    }

    @Override
    public void update() {
    	if(!isActive()){
    		if(game.isPaused()){
    			return;
    		} else {
    			activate();
    			setVisible(true);
    		}
    	}
    	
        if(game.isPaused()){
           	if(game.isGameOver()){
        		if(game.isGameWon()){
        			addLayer(new WinLayer(game));
        			removeLayer();
        		} else {
        			addLayer(new LoseLayer(game));
        			removeLayer();
        		}
        	}     
           	deactivate();
        	setVisible(false);
            addLayer(new PauseLayer(game));	
        } else {
        	game.update();
        }     
    }

    @Override
    public Graphics2D draw(Graphics2D g) {
        game.draw(g);
        return g;

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
    }

    @Override
    public void keyReleased(Key e) {
    	 switch (e) {
         case ESC:
             game.pause();
             break;
         }
    }
}
