package gui;

import javax.swing.JFrame;

import util.Logger;

/**
 * The MainFrame class represents the global main frame of the application
 */
public class MainFrame extends JFrame{

    private static final MainFrame instance = new MainFrame();

    private boolean displayed;
    private Screen gameScreen;

    /**
     * Creates an instance of the global main frame.
     */
    private MainFrame(){
        super("Fishy Game");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        displayed = false;
    }

    /**
     * Returns an instance of the global main frame.
     * @return the global main frame.
     */
    public static MainFrame getInstance(){
        return instance;
    }

    /**
     * Displays the main frame,
     * if the main frame ha not already been displayed.
     */
    public void display(){
        if (!displayed) {
            // Add new game screen
            gameScreen = new Screen();
            add(gameScreen);
            // Visual Settings
            setResizable(false);
            pack();
            setLocationRelativeTo(null);
            setVisible(true);
            // Add window listener
            addWindowListener(new ScreenWindowAdapter(gameScreen));
            // Display
            displayed = true;
            Logger.info("Main frame is displayed.");
        }
    }

    /**
     * Exit the application.
     */
    public void exit(){
        // Notify game screen
        gameScreen.exit();
        // Exit main frame
        dispose();
        Logger.info("Main frame has been exited.");
    }
}
