package layers;

import java.awt.Graphics2D;

import enumerations.Key;
import handlers.ImageHandler;
import handlers.OptionsHandler;

/**
 * The BackgroundLayer class is responsibly for the background features which are common for all layers.
 * This includes displaying the background image, sound icon, and music icon.
 */
public class BackgroundLayer extends Layer {

    /*
     * Background Image
     */
    private final static String bgkey = "background";
    private final static String bgurl = "/backgrounds/Background.png";

    /*
     * Sound and Music icons
     */
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
     * Creates a new BackgroundLayer.
     */
    public BackgroundLayer() {
        super();
        // Loads sound and music icons
        ImageHandler.getInstance().loadImage(bgkey, bgurl);
        ImageHandler.getInstance().loadImage(soundonkey, soundonurl);
        ImageHandler.getInstance().loadImage(soundoffkey, soundoffurl);
        ImageHandler.getInstance().loadImage(musiconkey, musiconurl);
        ImageHandler.getInstance().loadImage(musicoffkey, musicoffurl);
        // Displays title screen
        addLayer(new TitleLayer());
    }

    /**
     * Any layer specific updates that need to be applied to per 'tick'.
     */
    @Override
    public void update() {
    }

    /**
     * Draws the layer specific graphical elements to the specified 2-Dimensional image
     *
     * @param graphic the 2-Dimensional image.
     * @return the new graphic.
     */
    @Override
    public Graphics2D draw(Graphics2D graphic) {
        // Get screen size
        int screenWidth = OptionsHandler.getInstance().getWidth();
        int screenHeight = OptionsHandler.getInstance().getHeight();

        // Draw the background image
        graphic.drawImage(ImageHandler.getInstance().getImage(bgkey), 0, 0, screenWidth, screenHeight, null);

        // Get starting position for sound and music icons
        int startMusicX = 10;
        int startSoundX = startMusicX + iconwidth + 10;
        int startY = screenHeight - (iconheight + 10);

        // If music is enabled
        if (OptionsHandler.getInstance().musicOn()) {
            // Then draw music on icon
            graphic.drawImage(ImageHandler.getInstance().getImage(musiconkey), startMusicX, startY, iconwidth, iconheight, null);
        } else {
            // Otherwise draw music off icon
            graphic.drawImage(ImageHandler.getInstance().getImage(musicoffkey), startMusicX, startY, iconwidth, iconheight, null);
        }

        // If sound is enabled
        if (OptionsHandler.getInstance().soundOn()) {
            // Then draw sound on icon
            graphic.drawImage(ImageHandler.getInstance().getImage(soundonkey), startSoundX, startY, iconwidth, iconheight, null);
        } else {
            // Otherwise draw sound off icon
            graphic.drawImage(ImageHandler.getInstance().getImage(soundoffkey), startSoundX, startY, iconwidth, iconheight, null);
        }

        return graphic;
    }

    /**
     * Handles keys pressed by the player.
     *
     * @param key the key pressed
     */
    @Override
    public void keyPressed(Key key) {
    }

    /**
     * Handles keys no longer pressed by the payer.
     *
     * @param key the key released
     */
    @Override
    public void keyReleased(Key key) {
        switch (key) {
        case MUSIC:
            // Toggle music
            OptionsHandler.getInstance().toggleMusic();
            break;
        case SOUND:
            // Toggle sound
            OptionsHandler.getInstance().toggleSound();
            break;
        default:
            break;
        }
    }
}
