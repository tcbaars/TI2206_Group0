package layers;

import enumerations.Key;
import handlers.OptionsHandler;
import org.junit.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Adriaan on 17-9-2015.
 * Test method for layers.Layer class.
 */
public class InstructionsLayerTest extends LayerTest {
    /**
     * This method tests the constructor.
     */
    @Test
    public void constructorTest() {
        InstructionsLayer test = new InstructionsLayer();
        assertNull(test.getPrevious());
        assertTrue(test.isActive());
        assertTrue(test.isVisible());
        test.update();
    }

    /**
     * This method tests the key input and the currentlayer class
     */
    @Test
    public void selectTest() {
        Layer test = new InstructionsLayer();
        assertEquals("class layers.InstructionsLayer", test.getClass().toString());

        test.keyPressed(Key.ESC);
        test.keyReleased(Key.ESC);

        assertNull(test.getPrevious());
    }

    /**
     * To make this test useful we need to implement a method to test the drawing.
     * Make an exepectedRender and then compare each pixel? What about different fonts?
     */
    @Test
    public void drawTest() {
        BufferedImage image = new BufferedImage(OptionsHandler.getInstance().getWidth(), OptionsHandler.getInstance().getHeight(),
                BufferedImage.TYPE_INT_RGB);
        Graphics2D graphic = (Graphics2D) image.getGraphics();

        InstructionsLayer test = new InstructionsLayer();
        test.draw(graphic);
    }
}
