package gui;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * The ScreenWindowAdapter class is used to handle useful window events.
 */
public class ScreenWindowAdapter implements WindowListener{

    private Screen gameScreen;

    /**
     * Creates a new window listener which will be attached to the specified screen.
     * @param screen the screen.
     */
    public ScreenWindowAdapter(Screen screen){
        gameScreen = screen;
    }

    public void windowClosing(WindowEvent e) {
        // Notify the game screen
        gameScreen.windowClosing();
    }

    public void windowClosed(WindowEvent e) {}
    public void windowActivated(WindowEvent e) {}
    public void windowDeactivated(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowIconified(WindowEvent e) {}
    public void windowOpened(WindowEvent e) {}
}
