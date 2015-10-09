package layers;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import enumerations.GameImages;
import settings.MusicSettings;
import settings.ScreenSettings;
import settings.SoundSettings;
import tools.resourcetools.ImageLoader;

/**
 * The BackgroundLayer class represents the bare minimum set of graphic elements.
 * Draws the background image as a background.
 * Then draws the layer specific graphic elements.
 * Then draws the icon overlay on top of the layer.
 */
public abstract class BackgroundLayer implements Layer{

    /**
     * Initialises the background layer.
     */
    public BackgroundLayer(){
        ImageLoader.getInstance().loadImage(GameImages.BACKGROUND);
        ImageLoader.getInstance().loadImage(GameImages.MUSIC_ON);
        ImageLoader.getInstance().loadImage(GameImages.MUSIC_OFF);
        ImageLoader.getInstance().loadImage(GameImages.SOUND_ON);
        ImageLoader.getInstance().loadImage(GameImages.SOUND_OFF);
    }

    /**
     * Draws the specified layer to the screen.
     * With the additional bare minimum set of graphic elements.
     * @param screen the screen.
     */
    public void drawToScreen(Graphics2D screen){
        // Get screen dimensions
        int screenWidth = ScreenSettings.getInstance().getWidth();
        int screenHeight = ScreenSettings.getInstance().getHeight();

        // Draw background image to fit screen dimensions
        BufferedImage backgoundImage = ImageLoader.getInstance().getImage(GameImages.BACKGROUND);
        screen.drawImage(backgoundImage, 0, 0, screenWidth, screenHeight, null);

        // Draw layer specific graphic elements to screen
        drawLayer(screen);

        // Draw music and sound icons
        BufferedImage musicIcon;
        BufferedImage soundIcon;

        int iconWidth = GameImages.MUSIC_ON.getWidth();
        int iconHeight = GameImages.MUSIC_ON.getHeight();
        int iconSpacing = 10;

        int startMusicX = 10;
        int startSoundX = startMusicX + iconWidth + iconSpacing;
        int startY = screenHeight - (iconHeight + iconSpacing);

        // If music is enabled
        if (MusicSettings.getInstance().isMusicOn()) {
            // Then draw music on icon
            musicIcon = ImageLoader.getInstance().getImage(GameImages.MUSIC_ON);
            screen.drawImage(musicIcon, startMusicX, startY, null);
        } else {
            // Else draw music off icon
            musicIcon = ImageLoader.getInstance().getImage(GameImages.MUSIC_OFF);
            screen.drawImage(musicIcon, startMusicX, startY, null);
        }

        // If sound is enabled
        if (SoundSettings.getInstance().isSoundOn()) {
            // Then draw sound on icon
            soundIcon = ImageLoader.getInstance().getImage(GameImages.SOUND_ON);
            screen.drawImage(soundIcon, startSoundX, startY, null);
        } else {
            // Else draw sound off icon
            soundIcon = ImageLoader.getInstance().getImage(GameImages.SOUND_OFF);
            screen.drawImage(soundIcon, startSoundX, startY, null);
        }
    }

    /**
     * Draws the layer specific graphic elements.
     * @param screen the screen.
     */
    protected abstract void drawLayer(Graphics2D screen);


}
