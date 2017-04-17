package application;

import gui.MainFrame;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Adriaan on 11-9-2015.
 * Test method for application.Launcher class.
 */
public class LauncherTest {

    @Test
    public void mainTest() {
        String[] args = {};
        Launcher.main(args);

        assertEquals(MainFrame.getInstance().getLocation(), MainFrame.getInstance().getLocationOnScreen());
    }
}
