package layers;

import java.awt.Graphics2D;

import enumerations.Key;
import handlers.ImageHandler;
import handlers.OptionsHandler;

public class BackgroundLayer extends Layer {

    private final String bgkey = "background";
    private final String bgurl = "/backgrounds/Background.png";

    /**
     * Background.
     */
    public BackgroundLayer() {
        super();
        ImageHandler.getInstance().loadImage(bgkey, bgurl);
        addLayer(new TitleLayer());
    }

    @Override
    public void update() {
    }

    @Override
    public Graphics2D draw(Graphics2D graphics) {
        graphics.drawImage(ImageHandler.getInstance().getImage(bgkey), 0, 0, OptionsHandler.getInstance().getWidth(),
                OptionsHandler.getInstance().getHeight(), null);
        return graphics;
    }

    @Override
    public void keyPressed(Key key) {
    }

    @Override
    public void keyReleased(Key key) {
    }
}
