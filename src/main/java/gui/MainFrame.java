package gui;

import javax.swing.JFrame;

import util.Logger;

/**
 * The MainFrame class
 */
public class MainFrame extends JFrame{
    private static final MainFrame instance = new MainFrame();
    private boolean displayed;
    private Screen gameScreen;
    private MainFrame(){
        super("Fishy Game");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        displayed = false;
    }
    public static MainFrame getInstance(){
        return instance;
    }

    public void display(){
        if (!displayed) {
            gameScreen = new Screen();
            add(gameScreen);
            setResizable(false);
            pack();
            setLocationRelativeTo(null);
            setVisible(true);
            addWindowListener(new ScreenWindowAdapter(gameScreen));
            displayed = true;
            Logger.info("Main frame is displayed.");
        }
    }

    public void exit(){
        gameScreen.exit();
        dispose();
        Logger.info("Main frame has been exited.");
    }

}
