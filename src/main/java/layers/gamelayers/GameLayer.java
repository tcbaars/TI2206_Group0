package layers.gamelayers;

import java.awt.Graphics2D;
import java.util.Iterator;

import entities.Entity;
import layers.BackgroundLayer;

/**
 * The GameBaseLayer class represents the bare minimum set of graphic elements for the graphical representation of a game.
 * The game layer draws the game entities.
 */
public abstract class GameLayer extends BackgroundLayer{

    /**
     * Initialises the game layer.
     */
    public GameLayer(){
        // Initialises the background layer
        super();
    }

    /**
     * Returns the current game entities.
     * @return game entities.
     */
    abstract public Iterator<Entity> getEntities();

    /**
     * Draws the game layer.
     * @param screen the screen.
     */
    protected void drawLayer(Graphics2D screen){
        Iterator<Entity> entities = getEntities();
        while (entities.hasNext()) {
            Entity entity = entities.next();
            if (entity != null){
                entity.drawEntityToScreen(screen);
            }
        }
        drawHud(screen);
    }

    /**
     * Draws the game specific HUD overlay.
     * @param screen the screen.
     */
    abstract public void drawHud(Graphics2D screen);

}
