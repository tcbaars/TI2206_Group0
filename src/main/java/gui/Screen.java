package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import enumerations.States;
import keys.KeyAdapter;
import settings.ScreenSettings;
import statemanagers.StateManager;
import util.Logger;

/**
 * The Screen class represents the main screen of the application.
 * The screen is used to display the current state to the main frame.
 */
public class Screen extends JPanel implements Runnable, KeyListener{

    private Thread thread;
    private boolean running;
    private long targetTime;
    private int screenWidth;
    private int screenHeight;
    BufferedImage screenImage;
    private Graphics2D screen;
    private StateManager gameStateManager;

    /**
     * Creates a new screen.
     */
    public Screen(){
        super();
        running = false;
        targetTime = 1000 / ScreenSettings.getInstance().getTargetFps();
        gameStateManager = new StateManager();
        screenWidth = ScreenSettings.getInstance().getWidth();
        screenHeight = ScreenSettings.getInstance().getHeight();
        setPreferredSize(new Dimension(screenWidth, screenHeight));


        setFocusable(true);
        requestFocus();
    }

    /**
     * Initialises the screen when the screen thread is started.
     */
    public void initialise(){
        screenImage = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_RGB);
        screen = (Graphics2D)screenImage.getGraphics();
        running = true;
        Logger.info("The screen has been started.");
    }

    public void addNotify(){
        super.addNotify();
        /*
         * When the screen has been added to a component,
         * then start the screen thread,
         * if it has not already been started.
         */
        if(thread == null){
            thread = new Thread(this);
            addKeyListener(this);
            thread.start();
        }
    }

    public void keyPressed(KeyEvent e) {
        // Pass key press to the state manager
        gameStateManager.handleKeyPressed(KeyAdapter.convertKeyPressed(e));
    }

    public void keyReleased(KeyEvent e) {
        // Pass key release to the state manager
        gameStateManager.handleKeyReleased(KeyAdapter.convertKeyReleased(e));
    }

    public void keyTyped(KeyEvent e) {
        // Pass key typed to the state manager
        gameStateManager.handleKeyTyped(KeyAdapter.convertKeyTyped(e));
    }

    /**
     * Closes the screen.
     */
    public void windowClosing(){
        Logger.info("The user has pressed the 'Close' button.");
        gameStateManager.setCurrentState(States.EXIT_SCREEN);
    }

    public void run() {
        // Set-up the screen
        initialise();

        long start;
        long elapsed;
        long wait;
        while (running) {
            // Get the current time
            start = System.nanoTime();

            update();

            // If still running
            if (running) {
                drawToScreen();

                // Get the time passed
                elapsed = System.nanoTime() - start;

                /*
                 * If the time passed is less than the target time,
                 * then wait until the required time has passed
                 */
                wait = targetTime - (elapsed / 1000000);
                if (wait > 0) {
                    try {
                        Thread.sleep(wait);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * Performs the necessary operation to update the current state, each tick.
     */
    public void update(){
        gameStateManager.update();
    }

    /**
     * Draws the current state to screen.
     */
    public void drawToScreen(){
        gameStateManager.drawToScreen(screen);
        Graphics panelGraphic = getGraphics();
        panelGraphic.drawImage(screenImage, 0, 0, null);
        panelGraphic.dispose();

    }

    /**
     * Exit the running thread.
     */
    protected void exit(){
        running = false;
        Logger.info("The screen has been exited.");
    }
}
