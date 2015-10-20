package layers;

import java.awt.Graphics2D;

/**
 * The Layer interface represents the constraints a layer object should adhere to.
 * A layer represents a set of graphical elements.
 * The graphical representation of the game state can have multiple layers,
 * for example the background image, menu, and icon overlay.
 */
public interface Layer {

    /**
     * Draws the layer to the specified screen.
     * @param screen the screen.
     */
    void drawToScreen(Graphics2D screen);

}
