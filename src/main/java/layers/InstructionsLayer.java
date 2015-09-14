package layers;

import enumerations.Key;
import handlers.FontOutlineHandler;
import handlers.ImageHandler;
import handlers.OptionsHandler;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class InstructionsLayer extends Layer {

    /*
     * Appearance options of the title to be displayed
     */
    private final static String titletext = "INSTRUCTIONS";
    private final Color titlefill = Color.WHITE;
    private final Color titleoutline = Color.BLACK;
    private final static float titleoutlinesize = 2;
    private final Font titlefont = new Font("Times New Roman", Font.BOLD, 100);

    /*
    * Appearance options of the text to be displayed
    */
    private final String[] text = {"Eat smaller fish to grow bigger.", "Avoid larger fish.",
                            "The larger the fish, the more points", "you receive when eating."};
    private final String[] text2 = {"to move", "to pause the game", "to toggle sound", "to toggle music"};
    private final Font textfont = new Font("Times New Roman", Font.BOLD, 50);

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
    private final Color selectedfill = Color.YELLOW;
    private final Color optionoutline = Color.BLACK;
    private final static float optionoutlinesize = 1;
    private final Font optionfont = new Font("Times New Roman", Font.BOLD, 85);

    /*
    * The locations to the icons
    */
    private final String[] imageKey = {"arrowKey", "escKey", "sKey", "mKey"};
    private final String[] imageUrl = {"/sprites/icons/arrowKeys.png", "/sprites/icons/escKey.png",
                                        "/sprites/icons/sKey.png", "/sprites/icons/mKey.png"};

    public InstructionsLayer(){
        super();
    }

    private void select() {
        addLayer(new TitleLayer());
        removeLayer();
    }

    @Override
    public void update() {}

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
        FontOutlineHandler.drawTextCenterWidth(graphic, titlefont, titletext, titlefill, titleoutline,
                                                titleoutlinesize, ytitle);

        // Instructions
        for (int i = 0; i < text.length; i++) {
            FontOutlineHandler.drawTextCenterWidth(graphic, textfont, text[i], titlefill, optionoutline,
                                                    optionoutlinesize, ytext + ytextstep * i);
        }

        // Key explanation
        for (int i = 0; i < text2.length; i++) {
            FontOutlineHandler.drawTextCenterWidth(graphic, textfont, text2[i], titlefill, optionoutline,
                                                    optionoutlinesize, ytext2 + ytext2step * i);
            graphic.drawImage(ImageHandler.getInstance().getImage(imageKey[i]), 350, ytext2 + ytext2step * i - 45, 60, 60, null);
        }

        // Back key
        FontOutlineHandler.drawTextCenterWidth(graphic, optionfont, optiontext, selectedfill, optionoutline,
                optionoutlinesize, yoption);

        return graphic;
    }

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

    @Override
    public void keyReleased(Key key) {
    }

}
