package layers;

import enumerations.Key;
import handlers.FontOutlineHandler;
import handlers.OptionsHandler;
import handlers.SoundHandler;
import util.Logger;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

/**
 * The TitleLayer class represents the title screen of the game.
 * From which the option to start a new game, view the instructions, or exit can be selected.
 */
public class TitleLayer extends Layer {

    private OptionsHandler _optionsHandler = OptionsHandler.getInstance();

    private final static String clicksoundurl = "/sounds/click.wav";
    private final static String clicksoundkey = "click";
    /*
     * The selected options index and list of options available
     */
    private int selected;
    private String[] options = { "New Game", "Instructions", "Exit" };

    /*
     * Appearance options of the title to be displayed
     */
    private final static String titletext = "FISHY GAME";
    private final Color titlefill = Color.WHITE;
    private final Color titleoutline = Color.BLACK;
    private final static float titleoutlinesize = 2;
    private final Font titlefont = new Font("Times New Roman", Font.BOLD, 100);

    /*
     * The Y-coordinate of the title
     */
    private final static int ytitle = 150;

    /*
     * Appearance options of the title options to be displayed
     */
    private final Color optionfill = Color.WHITE;
    private final Color optionoutline = Color.BLACK;
    private final Color selectedfill = Color.YELLOW;
    private final static float optionoutlinesize = 1;
    private final Font optionfont = new Font("Times New Roman", Font.BOLD, 85);

    /*
     * The Y-coordinate of the first option
     * and the offset for the following options
     */
    private final static int yoption = 350;
    private final static int yoptionstep = 100;

    /**
     * Create a new title screen.
     */
    public TitleLayer() {
        selected = 0;
        SoundHandler.loadSound(clicksoundkey, clicksoundurl);
        Logger.info("Opening Title Menu");
    }

    /**
     * Select the currently selected option.
     */
    private void select() {
        // if new game
        if (selected == 0) {
            SoundHandler.playSound(clicksoundkey);
            addLayer(new GameLayer());
            removeLayer();
        // if instructions
        } else if (selected == 1) {
            addLayer(new InstructionsLayer());
            removeLayer();
        // if exit
        } else if (selected == 2) {
            Logger.info("User exited the game in the Title Menu");
            System.exit(0);
        }
    }

    /**
     * Any special updates that need to be applied to the title screen each 'tick'.
     */
    @Override
    public void update() {
    }

    /**
     * Adds the title and options to the specified image, and returns the result.
     *
     * @param graphic a 2-dimensional image
     * @return a 2-dimensional image with the added title and options
     */
    @Override
    public Graphics2D draw(Graphics2D graphic) {

        // Draw the opacity background square
        int screenWidth = OptionsHandler.getInstance().getWidth();
        final int rectWidth = 350;
        final int alphaValue = 192;

        graphic.setColor(new Color(0, 0, 0, alphaValue));
        graphic.fillRoundRect(screenWidth / 2 - rectWidth, ytitle - 100, // Start
                rectWidth *2, yoption + yoptionstep * options.length + 50 - ytitle, // End
                50, 50); // Corners

        // Draw the title
        FontOutlineHandler.drawTextCenterWidth(graphic, titlefont, titletext, titlefill, titleoutline, titleoutlinesize, ytitle);

        // Draw each option
        for(int i = 0; i < options.length; i++){
            // Highlights the option selected
            if (i == selected) {
                FontOutlineHandler.drawTextCenterWidth(graphic, optionfont, options[i], selectedfill, optionoutline, optionoutlinesize, (yoption + (yoptionstep * i)));
            } else {
                FontOutlineHandler.drawTextCenterWidth(graphic, optionfont, options[i], optionfill, optionoutline, optionoutlinesize, (yoption + (yoptionstep * i)));
            }
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
        switch (key) {
        /*
         *  Change the current selection according to what directional key is pressed
         *  Making sure to stay within the bounds of the options available
         */
            case UP:
            case LEFT:
                selected = selected - 1;
                if (selected < 0) {
                    selected = options.length - 1;
                }
                break;
            case DOWN:
            case RIGHT:
                selected = (selected + 1) % options.length;
                break;
            // Press the Enter to confirm the selected option
            case ENTER:
                select();
                break;
            case DEBUG:
                _optionsHandler.toggleDebug();
                Logger.info("DEBUG: " + _optionsHandler.getDebug());
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
    }
}
