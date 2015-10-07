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

public class Screen extends JPanel implements Runnable, KeyListener{

    private Thread thread;
    private boolean running;
    private long targetTime;
    private int screenWidth;
    private int screenHeight;
    BufferedImage screenImage;
    private Graphics2D screen;
    private StateManager gameStateManager;

    public Screen(){
        super();
        running = false;
        targetTime = 1000 / 60;
        gameStateManager = new StateManager();
        screenWidth = ScreenSettings.getInstance().getWidth();
        screenHeight = ScreenSettings.getInstance().getHeight();
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setFocusable(true);
        requestFocus();
    }

    public void initialise(){
        screenImage = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_RGB);
        screen = (Graphics2D)screenImage.getGraphics();
        running = true;
    }

    public void addNotify(){
        super.addNotify();
        if(thread == null){
            thread = new Thread(this);
            addKeyListener(this);
            thread.start();
        }
    }

    public void keyPressed(KeyEvent e) {
        gameStateManager.handleKeyPressed(KeyAdapter.convertKeyPressed(e));
    }

    public void keyReleased(KeyEvent e) {
        gameStateManager.handleKeyReleased(KeyAdapter.convertKeyReleased(e));
    }

    public void keyTyped(KeyEvent e) {
        gameStateManager.handleKeyTyped(KeyAdapter.convertKeyTyped(e));
    }

    public void windowClosing(){
        gameStateManager.setCurrentState(States.EXIT_SCREEN);
    }

    public void run() {
        initialise();
        long start;
        long elapsed;
        long wait;
        while (running) {
            start = System.nanoTime();
            update();
            if (running) {
                drawToScreen();
                elapsed = System.nanoTime() - start;

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

    public void update(){
        gameStateManager.update();
    }

    public void drawToScreen(){
        gameStateManager.drawToScreen(screen);
        Graphics panelGraphic = getGraphics();
        panelGraphic.drawImage(screenImage, 0, 0, null);
        panelGraphic.dispose();

    }

    protected void exit(){
        running = false;
    }
}
