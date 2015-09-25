package layers;

import enumerations.GameFont;
import enumerations.GameSound;
import enumerations.Key;
import handlers.FontOutlineHandler;
import handlers.ImageHandler;
import handlers.OptionsHandler;
import util.Logger;

import java.awt.Color;
import java.awt.Graphics2D;

public class InstructionsLayer extends Layer {

    /*
     * Appearance options of the title to be displayed
     */
    private final static String titletext = "INSTRUCTIONS";

    /*
    * Appearance options of the text to be displayed
    */
    private final String[] text = {"Eat smaller fish to grow bigger.", "Avoid larger fish.",
                            "The larger the fish, the more points", "you receive when eating."};
    private final String[] text2 = {"to move", "to pause the game", "to toggle sound", "to toggle music"};

    /*
     * The coordinates for the text
     */
    private final static int ytitle = 125;
    private final static int yoption = 700;
    private final static int ytext = 200;
    private final static int ytextstep = 50;
    private final int ytext2 = ytext + ytextstep * text.length + 25;
    private final static int ytext2step = 60;

    /*
     * Appearance options of the options to be displayed
     */
    private final static String optiontext = "BACK";

    /*
    * The icons
    */
    private final String[] imageKey = {"arrowKey", "escKey", "sKey", "mKey"};
    private final String[] imageUrl = {"/icons/arrowKeys.png", "/icons/escKey.png",
                                        "/icons/sKey.png", "/icons/mKey.png"};

    /**
     * Creates a new instructions screen.
     */
    public InstructionsLayer(){
        super();
        // Load sound resources
        _soundHandler.loadSound(GameSound.SELECT);
        Logger.info("Opening Instructions Menu");
    }

    /**
     * Select the currently selected option.
     */
    private void select() {
        // Play selection sound
        _soundHandler.playSound(GameSound.SELECT);
        addLayer(new TitleLayer());
        removeLayer();
    }

    /**
     * Any layer specific updates that need to be applied to per 'tick'.
     */
    @Override
    public void update() {}

    /**
     * Draws the layer specific graphical elements to the specified 2-Dimensional image
     *
     * @param graphic the 2-Dimensional image.
     * @return the new graphic.
     */
    @Override
    public Graphics2D draw(Graphics2D graphic) {

        // Load images from disk
        ImageHandler handler = ImageHandler.getInstance();
        for (int i = 0; i < imageKey.length; i++) {
            handler.loadImage(imageKey[i], imageUrl[i]);
        }

        // Draw the opacity background square
        int screenWidth = OptionsHandler.getInstance().getWidth();
        final int rectWidth = 450;
        final int alphaValue = 192;

        graphic.setColor(new Color(0, 0, 0, alphaValue));
        graphic.fillRoundRect(screenWidth / 2 - rectWidth, ytitle - 100, // Start
                rectWidth * 2, yoption - 10, // End
                50, 50); // Corners

        // Title
        FontOutlineHandler.drawTextCenterWidth(graphic, GameFont.TITLE, titletext, ytitle);

        // Instructions
        for (int i = 0; i < text.length; i++) {
            FontOutlineHandler.drawTextCenterWidth(graphic, GameFont.TEXT, text[i], ytext + ytextstep * i);
        }

        // Key explanation
        for (int i = 0; i < text2.length; i++) {
            FontOutlineHandler.drawTextCenterWidth(graphic, GameFont.TEXT, text2[i], ytext2 + ytext2step * i);
            graphic.drawImage(ImageHandler.getInstance().getImage(imageKey[i]), 350, ytext2 + ytext2step * i - 45, 60, 60, null);
        }

        // Back option
        FontOutlineHandler.drawTextCenterWidth(graphic, GameFont.SELECTED, optiontext, yoption);

        return graphic;
    }

    /**
     * Handles keys pressed by the player.
     *
     * @param key the key pressed
     */
    @Override
    public void keyPressed(Key key) {
        switch (key) {
            case ENTER:
            case ESC:
                select();
                break;
            default:
                break;
        }
    }

    /**
     * Handles keys no longer pressed by the payer.
     *
     * @param key the key released
     */
    @Override
    public void keyReleased(Key key) {
        switch (key) {
            case ENTER:
            case ESC:
                select();
                break;
            default:
                break;
        }
    }

}
