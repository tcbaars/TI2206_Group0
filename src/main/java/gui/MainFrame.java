package gui;

import javax.swing.JFrame;

import layers.HeadLayer;

public class MainFrame extends JFrame {

    private static MainFrame instance = new MainFrame();
    private boolean displayed;

    private MainFrame() {
        super("Fishy Game");
        displayed = false;
    }

    public static MainFrame getInstance() {
        return instance;
    }

    /**
     * display frame.
     */
    public void display() {
        if (!displayed) {
            add(new HeadLayer());
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setResizable(false);
            pack();
            setVisible(true);
        }
    }
}
