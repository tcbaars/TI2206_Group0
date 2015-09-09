package layers;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import enumerations.Key;
import handlers.KeyHandler;
import handlers.OptionsHandler;

public class HeadLayer extends Layer implements Runnable, KeyListener {

    private Thread thread;
    private boolean running;
    private long targetTime = 1000 / OptionsHandler.getInstance().getTargetFps();

    private BufferedImage image;
    private Graphics2D graphic;

    /**
     * Head of all layers.
     */
    public HeadLayer() {
        super();
        running = false;
        setFocusable(true);
        requestFocus();
    }

    private void init() {
        image = new BufferedImage(OptionsHandler.getInstance().getWidth(), OptionsHandler.getInstance().getHeight(),
                BufferedImage.TYPE_INT_RGB);
        graphic = (Graphics2D) image.getGraphics();
        running = true;
        addLayer(new BackgroundLayer());
    }

    /**
     * Notify.
     */
    public void addNotify() {
        super.addNotify();
        if (thread == null) {
            thread = new Thread(this);
            addKeyListener(this);
            thread.start();
        }
    }

    /**
     * run thread.
     */
    public void run() {
        init();

        long start;
        long elapsed;
        long wait;

        // game loop
        while (running) {

            start = System.nanoTime();
            updateLayer();
            drawLayer(graphic);
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

    @Override
    protected void update() {
    }

    @Override
    protected Graphics2D draw(Graphics2D graphic) {
        return this.graphic;
    }

    /**
     * Draw final image to screen.
     */
    public void drawToScreen() {
        Graphics g2 = getGraphics();
        g2.drawImage(image, 0, 0, OptionsHandler.getInstance().getWidth(), OptionsHandler.getInstance().getHeight(),
                null);
        g2.dispose();
    }

    public void keyPressed(KeyEvent keyEvent) {
        handleKeyPress(KeyHandler.convertToKey(keyEvent));
    }

    @Override
    protected void keyPressed(Key key) {
    }

    public void keyReleased(KeyEvent keyEvent) {
        handleKeyRelease(KeyHandler.convertToKey(keyEvent));
    }

    @Override
    protected void keyReleased(Key key) {
    }

    public void keyTyped(KeyEvent keyEvent) {
    }

}
