package states;

import gui.MainFrame;
import org.junit.BeforeClass;
import org.junit.Test;
import statemanagers.StateManager;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Adriaan on 30-10-2015.
 */
public class ExitScreenStateTest {
    static StateManager manager;
    static ExitScreenState state;

    @BeforeClass
    public static void setUp() {
        manager = new StateManager();
        state = new ExitScreenState(manager);
        MainFrame.getInstance().display();
    }

    @Test
    public void ExitScreenStateTest() {
        state.drawToScreen((Graphics2D)new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB).getGraphics());
    }

    @Test
    public void updateTest() {
        state.update();
    }
}
