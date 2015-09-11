package layers;

import enumerations.Key;
import handlers.FontOutlineHandler;
import handlers.GameHandler;
import handlers.OptionsHandler;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class PauseLayer extends Layer{

    private GameHandler game;

    /*
     * The selected options index and list of options available
     */
    private int selected;
    private final String[] options = {"Resume Game", "Title Screen", "Exit"};

    /*
     * Appearance options of the title to be displayed
     */
    private final static String titletext = "PAUSED";
    private final Color titlefill = Color.WHITE;
    private final Color titleoutline = Color.BLACK;
    private final static float titleoutlinesize = 2;
    private final Font titlefont = new Font("Times New Roman", Font.BOLD, 100);

    /*
     * Appearance options of the paused options to be displayed
     */
    private final Color optionfill = Color.WHITE;
    private final Color optionoutline = Color.BLACK;
    private final Color selectedfill = Color.YELLOW;
    private final static float optionoutlinesize = 1;
    private final Font optionfont = new Font("Times New Roman", Font.BOLD, 85);

    /*
     * The coordinates for the title/options
     */
    private final static int ytitle = 150;
    private final static int yoption = 350;
    private final static int yoptionstep = 100;

    /**
     * Layer to display pause screen.
     */
    public PauseLayer(GameHandler game) {
        this.game = game;
        selected = 0;
    }

    private void select() {
        switch(selected) {
            case 0: game.resume(); // Resume
                    break;
            case 1: addLayer(new TitleLayer()); // Title Screen
                    removeLayer();
                    break;
            case 2: System.exit(0); // Exit
                    break;
            default: break;
        }
    }

    @Override
    protected void update() {
        if (!game.isPaused()) {
            removeLayer();
        }
    }

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
            case ESC:
                game.resume();
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
