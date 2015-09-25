package layers;


import enumerations.GameFont;
import enumerations.GameSound;
import enumerations.Key;
import handlers.FontOutlineHandler;
import handlers.HighScoresHandler;
import handlers.OptionsHandler;
import util.Logger;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

/**
 * This layer draws the highscores
 */
public class HighScoreLayer extends Layer {

    private HighScoresHandler _HighScoresHandler = HighScoresHandler.getInstance();
    int currentScores = _HighScoresHandler.getHighScores().size(); // Max amount of showing scores
    private final static int maxScores = 6;

    /*
     * Appearance options of the title to be displayed
     */
    private final static String titletext = "HIGHSCORES";
    private final Color titlefill = Color.WHITE;
    private final Color titleoutline = Color.BLACK;
    private final static float titleoutlinesize = 2;
    private final Font titlefont = new Font("Times New Roman", Font.BOLD, 100);

    /*
     * The coordinates for the text
     */
    private final static int ytitle = 125;
    private final static int yoption = 700;
    private final static int xNameStart = 325;
    private final static int yScoreStart = 210;
    private final static int xScoreStart = 810;
    private final static int yScoreStep = 75;

    /*
     * Appearance options of the options to be displayed
     */
    private final static String optiontext = "BACK";
    private final Color selectedfill = Color.YELLOW;
    private final Color optionoutline = Color.BLACK;
    private final static float optionoutlinesize = 1;
    private final Font optionfont = new Font("Times New Roman", Font.BOLD, 85);

    public HighScoreLayer(){
        super();
        // Load sound resources
        _soundHandler.loadSound(GameSound.SELECT);
        _soundHandler.loadSound(GameSound.NAVIGATE);
        Logger.info("Opening Highscore Menu");
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

        // Draw the opacity background square
        int screenWidth = OptionsHandler.getInstance().getWidth();
        final int rectWidth = 400;
        final int alphaValue = 192;

        graphic.setColor(new Color(0, 0, 0, alphaValue));
        graphic.fillRoundRect(screenWidth / 2 - rectWidth, ytitle - 100, // Start
                rectWidth * 2, yoption - 10, // End
                50, 50); // Corners

        // Title

        FontOutlineHandler.drawTextCenterWidth(graphic, GameFont.TITLE, titletext, ytitle);

        // Highscores
        if (currentScores > maxScores) {
            currentScores = maxScores;
        }
        for (int i = 0; i < currentScores; i++) {
            FontOutlineHandler.drawText(graphic, GameFont.TEXT, _HighScoresHandler.getHighScores().get(i).getName(), xNameStart, yScoreStart + i * yScoreStep);
            FontOutlineHandler.drawText(graphic, GameFont.TEXT, String.valueOf(_HighScoresHandler.getHighScores().get(i).getScore()), xScoreStart, yScoreStart + i * yScoreStep);
        }

        // Back key
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
    }

}
