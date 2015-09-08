package gui;

import javax.swing.JFrame;

import layers.HeadLayer;

public class MainFrame extends JFrame {

    private static MainFrame instance = new MainFrame();

    private MainFrame() {
        super("Fishy Game");
    }

    public static MainFrame getInstance() {
        return instance;
    }

    public void display() {
        add(new HeadLayer());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setVisible(true);
    }
}
