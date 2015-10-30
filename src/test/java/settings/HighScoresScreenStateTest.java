package settings;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;
import statemanagers.StateManager;
import states.HighScoresScreenState;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Adriaan on 30-10-2015.
 */
public class HighScoresScreenStateTest {
    static StateManager manager;
    static HighScoresScreenState state;

    @BeforeClass
    public static void setUp() {
        manager = new StateManager();
        state = new HighScoresScreenState(manager);
    }

    @Test
    public void HighScoresScreenStateTest() {
        assertTrue(state instanceof HighScoresScreenState);
    }

    @Test
    public void drawToScreenTest() {
        state.drawToScreen((Graphics2D)new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB).getGraphics());
    }
}
