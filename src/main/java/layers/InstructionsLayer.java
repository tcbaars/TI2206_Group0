package layers;

import enumerations.Key;
import handlers.FontOutlineHandler;

import java.awt.*;

public class InstructionsLayer extends Layer {

    /*
     * Appearance options of the title to be displayed
     */
    private final String titletext = "INSTRUCTIONS";
    private final Color titlefill = Color.WHITE;
    private final Color titleoutline = Color.BLACK;
    private final float titleoutlinesize = 2;
    private final Font titlefont = new Font("Times New Roman", Font.BOLD, 100);

    /*
    * Appearance options of the text to be displayed
    */
    private final String[] text = {"Eat smaller fish to grow bigger", "Avoid larger fish",
                            "The larger the fish, the more points", "you receive when eating"};
    private final Font textfont = new Font("Times New Roman", Font.BOLD, 50);

    /*
     * The coordinates for the text
     */
    private final int ytitle = 125;
    private final int yoption = 700;
    private final int ytext = 200;
    private final int ytextstep = 50;

    /*
     * Appearance options of the options to be displayed
     */
    private final String optiontext = "BACK";
    private final Color selectedfill = Color.YELLOW;
    private final Color optionoutline = Color.BLACK;
    private final float optionoutlinesize = 1;
    private final Font optionfont = new Font("Times New Roman", Font.BOLD, 85);

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

        // Title
        FontOutlineHandler.drawTextCenterWidth(graphic, titlefont, titletext, titlefill, titleoutline,
                                                titleoutlinesize, ytitle);

        // Instructions
        for (int i = 0; i < text.length; i++) {
            FontOutlineHandler.drawTextCenterWidth(graphic, textfont, text[i], titlefill, optionoutline,
                                                    optionoutlinesize, ytext + ytextstep * i);
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
