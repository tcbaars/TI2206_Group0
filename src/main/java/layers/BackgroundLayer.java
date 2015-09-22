package layers;

import java.awt.Graphics2D;

import enumerations.Key;
import handlers.ImageHandler;
import handlers.OptionsHandler;

public class BackgroundLayer extends Layer {

    private final static String bgkey = "background";
    private final static String bgurl = "/backgrounds/Background.png";

    private final static int iconwidth = 30;
    private final static int iconheight = 30;
    private final static String soundonkey = "soundOn";
    private final static String soundonurl = "/icons/soundOn.png";
    private final static String soundoffkey = "soundOff";
    private final static String soundoffurl = "/icons/soundOff.png";
    private final static String musiconkey = "musicOn";
    private final static String musiconurl = "/icons/musicOn.png";
    private final static String musicoffkey = "musicOff";
    private final static String musicoffurl = "/icons/musicOff.png";

    /**
     * Background.
     */
    public BackgroundLayer() {
        super();
        ImageHandler.getInstance().loadImage(bgkey, bgurl);
        ImageHandler.getInstance().loadImage(soundonkey, soundonurl);
        ImageHandler.getInstance().loadImage(soundoffkey, soundoffurl);
        ImageHandler.getInstance().loadImage(musiconkey, musiconurl);
        ImageHandler.getInstance().loadImage(musicoffkey, musicoffurl);
        addLayer(new TitleLayer());
    }

    @Override
    public void update() {
    }

    @Override
    public Graphics2D draw(Graphics2D graphics) {
        int screenWidth = OptionsHandler.getInstance().getWidth();
        int screenHeight = OptionsHandler.getInstance().getHeight();
        graphics.drawImage(ImageHandler.getInstance().getImage(bgkey), 0, 0, screenWidth, screenHeight, null);
        int startMusicX = 10;
        int startSoundX;
        int startY = screenHeight - (iconheight + 10);
        if (OptionsHandler.getInstance().musicOn()) {
            startSoundX = startMusicX + iconwidth + 10;
            graphics.drawImage(ImageHandler.getInstance().getImage(musiconkey), startMusicX, startY, iconwidth, iconheight, null);
        } else {
            startSoundX = startMusicX + iconwidth + 10;
            graphics.drawImage(ImageHandler.getInstance().getImage(musicoffkey), startMusicX, startY, iconwidth, iconheight, null);
        }
        if (OptionsHandler.getInstance().soundOn()) {
            graphics.drawImage(ImageHandler.getInstance().getImage(soundonkey), startSoundX, startY, iconwidth, iconheight, null);
        } else {
            graphics.drawImage(ImageHandler.getInstance().getImage(soundoffkey), startSoundX, startY, iconwidth, iconheight, null);
        }
        return graphics;
    }

    @Override
    public void keyPressed(Key key) {
        switch (key) {
        case MUSIC:
            OptionsHandler.getInstance().toggleMusic();
            break;
        case SOUND:
            OptionsHandler.getInstance().toggleSound();
            break;
        default:
            break;
        }
    }

    @Override
    public void keyReleased(Key key) {
    }
}
