package layers;

import enumerations.Key;
import handlers.FontOutlineHandler;
import handlers.OptionsHandler;
import util.Logger;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

/**
 * Created by Adriaan on 11-9-2015.
 * This class is responsible for the win/lost screen.
 */
public class FinishLayer extends Layer {

    private int selected;
    private int score;
    private boolean hasWon; // Currently not used. Waiting for highscore

    /*
     * Appearance options of the title to be displayed
     */
    private String titletext;
    private final Color titlefill = Color.WHITE;
    private final Color titleoutline = Color.BLACK;
    private final static float titleoutlinesize = 2;
    private final Font titlefont = new Font("Times New Roman", Font.BOLD, 100);

    /*
    * Appearance options of the text to be displayed
    */
    // private final String[] text = {"Fish Eaten: NaN", "Score: ", "High Score: NaN"};
    private final String[] text = {"", "Score: ", ""};
    private final Font textfont = new Font("Times New Roman", Font.BOLD, 50);

    /*
    * Appearance options of the options to be displayed
    */
    private final Color optionfill = Color.WHITE;
    private final Color selectedfill = Color.YELLOW;
    private final Color optionoutline = Color.BLACK;
    private final static float optionoutlinesize = 1;
    private final String[] options = {"Play Again", "Go To Title Screen"};
    private final Font optionfont = new Font("Times New Roman", Font.BOLD, 85);

    /*
     * The coordinates for the elements
     */
    private final static int ytitle = 125;
    private final static int ytext = 200;
    private final static int ytextstep = 50;
    private final static int yoption = 600;
    private final static int yoptionstep = 85;

    /**
     * Constructor for win/lost screen
     */
    public FinishLayer(int score, boolean hasWon) {
        this.score = score;
        this.hasWon = hasWon;
        selected = 0;
        if (hasWon) {
            titletext = "YOU WIN!";
        } else {
            titletext = "YOU LOST";
        }

        Logger.info("Player ended the game, " + titletext);
        text[1] = text[1] + score;
    }

    /**
     * Select method to select the right options
     */
    private void select() {
        switch(selected) {
            case 0: addLayer(new GameLayer()); // Start new game
                    removeLayer();
                    break;
            case 1: addLayer(new TitleLayer()); // Title Screen
                    removeLayer();
                    break;
            default: break;
        }
    }

    /**
     * This method draws the FinishLayer on top of the background
     * @return drawed screen with all information
     */
    @Override
    public Graphics2D draw(Graphics2D graphic) {

        // Draw the opacity background square
        int screenWidth = OptionsHandler.getInstance().getWidth();
        final int rectWidth = 450;
        final int alphaValue = 192;

        graphic.setColor(new Color(0, 0, 0, alphaValue));
        graphic.fillRoundRect(screenWidth / 2 - rectWidth, ytitle - 100, // Start
                rectWidth * 2, yoption + yoptionstep * (options.length - 1), // End
                50, 50); // Corners

        // Title
        FontOutlineHandler.drawTextCenterWidth(graphic, titlefont, titletext, titlefill, titleoutline,
                titleoutlinesize, ytitle);

        // Scores
        for (int i = 0; i < text.length; i++) {
            FontOutlineHandler.drawTextCenterWidth(graphic, textfont, text[i], titlefill, optionoutline,
                    optionoutlinesize, ytext + ytextstep * i);
        }

        // Draw each option
        for(int i = 0; i < options.length; i++){
            // Highlights the option selected
            if (i == selected) {
                FontOutlineHandler.drawTextCenterWidth(graphic, optionfont, options[i], selectedfill, optionoutline,
                        optionoutlinesize, (yoption + (yoptionstep * i)));
            } else {
                FontOutlineHandler.drawTextCenterWidth(graphic, optionfont, options[i], optionfill, optionoutline,
                        optionoutlinesize, (yoption + (yoptionstep * i)));
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
            default:
                break;
        }
    }

    /**
     * Handles keys no longer pressed by the payer.
     *
     * @param key the key released
     */
    public void keyReleased(Key key) {}

    @Override
    protected void update() {}
}
