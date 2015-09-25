package layers;

import enumerations.GameFont;
import enumerations.GameSound;
import enumerations.Key;
import handlers.FontOutlineHandler;
import handlers.GameHandler;
import handlers.OptionsHandler;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * The PauseLayer class represents the pause screen of the game.
 * From which the game can be resumed and exited.
 */
public class PauseLayer extends Layer{

    /*
     * The game paused
     */
    private GameHandler game;

    /*
     * Appearance options of the paused screen to be displayed
     */
    private final static String titletext = "PAUSED";

    /*
     * The selected options index and list of options available
     */
    private int selected;
    private final String[] options = {"Resume Game", "Title Screen", "Exit"};

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
        // Set the initial selected item
        selected = 0;
        // Load PauseLayer sound resources
        _soundHandler.loadSound(GameSound.SELECT);
        _soundHandler.loadSound(GameSound.NAVIGATE);
    }

    /**
     * Select the currently selected option.
     */
    private void select() {
        // Play selection sound
        _soundHandler.playSound(GameSound.SELECT);

        switch(selected) {
            // Resume
            case 0:
                game.resume();
                break;
            // Title Screen
            case 1:
                addLayer(new TitleLayer());
                removeLayer();
                break;
            // Exit
            case 2:
                System.exit(0); // Exit
                break;
            default:
                break;
        }
    }

    /**
     * Changes the currently selected option.
     *
     * @param change the change to be applied.
     */
    private void navigate(int change){
        _soundHandler.playSound(GameSound.NAVIGATE);
        selected = selected + change;
        if(selected < 0){
            selected = options.length - 1;
        } else if(selected >= options.length ){
            selected = 0;
        }
    }

    /**
     * Draws the layer specific graphical elements to the specified 2-Dimensional image
     *
     * @param graphic the 2-Dimensional image.
     * @return the new graphic.
     */
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
        FontOutlineHandler.drawTextCenterWidth(graphic, GameFont.TITLE, titletext, ytitle);

        // Draw each option
        for(int i = 0; i < options.length; i++){
            // Highlights the option selected
            if (i == selected) {
                FontOutlineHandler.drawTextCenterWidth(graphic, GameFont.SELECTED, options[i], (yoption + (yoptionstep * i)));
            } else {
                FontOutlineHandler.drawTextCenterWidth(graphic, GameFont.OPTION, options[i], (yoption + (yoptionstep * i)));
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
            case UP:
            case LEFT:
                navigate(-1);
                break;
            case DOWN:
            case RIGHT:
                navigate(1);
<<<<<<< HEAD
=======
                break;
            case ENTER:
                select();
                break;
            case ESC:
                game.resume();
>>>>>>> 6ecd746c29c9ec1f6cc2281dc9043021d708f2c5
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
                select();
                break;
            case ESC:
                game.resume();
                break;
            default:
                break;
        }
    }
}
