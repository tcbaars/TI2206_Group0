package layers;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import enumerations.Key;
import handlers.OptionsHandler;

public class BackgroundLayer extends Layer {

    private BufferedImage backgroundimage;
    private final String backgroundimageURL = "/backgrounds/Background.png";

    public BackgroundLayer() {
        super();
        try {
            backgroundimage = ImageIO.read(BackgroundLayer.class.getResourceAsStream(backgroundimageURL));
        } catch (IOException e) {
            e.printStackTrace();
        }
        addLayer(new TitleLayer());
    }

    @Override
    public void update() {
        updateNext();
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawImage(backgroundimage, 0, 0, OptionsHandler.getInstance().getWidth(),
                OptionsHandler.getInstance().getHeight(), null);
        drawNext(g);
    }

    @Override
    public void keyPressed(Key e) {
        passKeyPress(e);
    }

    @Override
    public void keyReleased(Key e) {
        passKeyRelease(e);
    }
}
