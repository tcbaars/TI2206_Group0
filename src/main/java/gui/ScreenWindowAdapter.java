package gui;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ScreenWindowAdapter implements WindowListener{

    private Screen gameScreen;

    public ScreenWindowAdapter(Screen screen){
        gameScreen = screen;
    }
    public void windowClosing(WindowEvent e) {
        gameScreen.windowClosing();
    }

    public void windowClosed(WindowEvent e) {}
    public void windowActivated(WindowEvent e) {}

    public void windowDeactivated(WindowEvent e) {}

    public void windowDeiconified(WindowEvent e) {}

    public void windowIconified(WindowEvent e) {}

    public void windowOpened(WindowEvent e) {}

}
