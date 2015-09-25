package layers;

import enumerations.GameFont;
import enumerations.GameSound;
import enumerations.Key;
import handlers.FontOutlineHandler;
import handlers.OptionsHandler;
import util.Logger;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * The TitleLayer class represents the title screen of the game.
 * From which the option to start a new game, view the instructions, view the high scores, or exit can be selected.
 */
public class TitleLayer extends Layer {

    private OptionsHandler _optionsHandler = OptionsHandler.getInstance();

    /*
     * The selected options index and list of options available
     */
    private int selected;
    private String[] options = { "New Game", "Highscore", "Instructions", "Exit" };

    /*
     * Appearance options of the title to be displayed
     */
    private final static String titletext = "FISHY GAME";

    /*
     * The Y-coordinate of the title
     */
    private final static int ytitle = 150;

    /*
     * Appearance options of the title options to be displayed
     */
    private final Color optionfill = Color.WHITE;

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
        // Load sound resources
        _soundHandler.loadSound(GameSound.SELECT);
        _soundHandler.loadSound(GameSound.NAVIGATE);
        Logger.info("Opening Title Menu");
    }

    /**
     * Select the currently selected option.
     */
    private void select() {
        // Play selection sound
<<<<<<< HEAD
        _soundHandler.playSound(selectsoundkey);
        // if new game
        if (selected == 0) {
            addLayer(new GameLayer());
            removeLayer();
        // if Highscore
        } else if (selected == 1) {
            addLayer(new HighScoreLayer());
            removeLayer();
        // if instructions
        } else if (selected == 2) {
            addLayer(new InstructionsLayer());
            removeLayer();
        // if exit
        } else if (selected == 3) {
            Logger.info("User exited the game in the Title Menu");
            System.exit(0);
=======
        _soundHandler.playSound(GameSound.SELECT);

        switch (selected) {
            // New game
            case 0:
                addLayer(new GameLayer());
                removeLayer();
                break;
            // High Scores
            case 1:
                addLayer(new HighScoreLayer());
                removeLayer();
               break;
             // Instructions
            case 2:
                addLayer(new InstructionsLayer());
                removeLayer();
                break;
            // Exit
            case 3:
                Logger.info("User exited the game in the Title Menu");
                System.exit(0);
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
                break;
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