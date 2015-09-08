package layers;

import java.awt.Graphics2D;
import enumerations.Key;
import handlers.ImageHandler;
import handlers.OptionsHandler;

public class BackgroundLayer extends Layer {

    private final String BGKEY = "background";
    private final String BGURL = "/backgrounds/Background.png";

    public BackgroundLayer() {
        super();
        ImageHandler.getInstance().loadImage(BGKEY, BGURL);
        addLayer(new TitleLayer());
    }

    @Override
    public void update() {
    }

    @Override
    public Graphics2D draw(Graphics2D g) {
        g.drawImage(ImageHandler.getInstance().getImage(BGKEY), 0, 0, OptionsHandler.getInstance().getWidth(),
                OptionsHandler.getInstance().getHeight(), null);
        return g;
    }

    @Override
    public void keyPressed(Key e) {
    }

    @Override
    public void keyReleased(Key e) {
    }
}
