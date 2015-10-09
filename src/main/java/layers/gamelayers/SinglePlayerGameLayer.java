package layers.gamelayers;

import java.awt.Graphics2D;

import enumerations.GameFonts;
import highscores.HighScores;
import settings.ScreenSettings;
import tools.layertools.FontOutline;

/**
 * The SinglePlayerGameLayer class represents the default set of graphic elements for a single player game.
 * The SinglePlayerGameLayer draws the default single player game HUD,
 * which consists of the number of fish eaten, the current score and the current high score.
 */
public abstract class SinglePlayerGameLayer extends GameLayer{

    private int textY;

    /**
     * Initialises the single player game layer.
     */
    public SinglePlayerGameLayer(){
        // Initialise the game layer
        super();

        // Initial text depth
        textY = 100;
    }

    /**
     * Returns the current number of fish consumed by the player.
     * @return the number of fish eaten.
     */
    abstract protected int getNumberFishEaten();

    /**
     * Returns the player's current score.
     * @return the current score.
     */
    abstract protected int getScore();

    /**
     * Returns the current high score.
     * @return the high score.
     */
    private int getCurrentHighScore(){
        int highScore = HighScores.getInstance().getHighestScore().getScore();
        int currentScore = getScore();
        if(currentScore > highScore){
            return currentScore;
        }
        return highScore;
    }

    /**
     * Draws the single player game layer specific HUD.
     * Which is consists of the number fish eaten,
     * the current score, and the current high score.
     * Can be overridden to be customised by different single player game modes.
     * @param screen the screen
     */
    public void drawHud(Graphics2D screen){
        drawNumberFishEaten(screen);
        drawScore(screen);
        drawHighScore(screen);
    }

    /**
     * Draws current number of fish consumed by the player.
     * Aligned far left of the screen.
     * Can be overridden to be customised by different single player game modes.
     * @param screen the screen
     */
    private void drawNumberFishEaten(Graphics2D screen){
        // Draw current number fish eaten far left
        String fishEaten = "Fish Eaten: " + getNumberFishEaten();
        int fishEatenX = 25;
        FontOutline.drawText(screen, GameFonts.GAME_TEXT, fishEaten, fishEatenX, textY);
    }

    /**
     * Draws the player's current score.
     * Aligned horizontally centre of the screen.
     * Can be overridden to be customised by different single player game modes.
     * @param screen the screen.
     */
    private void drawScore(Graphics2D screen){
        // Draw current score horizontally centred
        String currentScore = "Current Score: " + getScore();
        FontOutline.drawTextHorizontallyCentered(screen, GameFonts.GAME_TEXT, currentScore, textY);
    }

    /**
     * Draws the current high score.
     * Aligned far right of the screen.
     * Can be overridden to be customised by different single player game modes.
     * @param screen the screen.
     */
    private void drawHighScore(Graphics2D screen){
        // Draw current high score far right
        String highScore = " High Score: " + getCurrentHighScore();
        double textWidth = FontOutline.getFontWidth(GameFonts.GAME_TEXT.getFont(), highScore);
        int screenWidth = ScreenSettings.getInstance().getWidth();
        int highScoreX = (int) (screenWidth - (textWidth + 25));
        FontOutline.drawText(screen, GameFonts.GAME_TEXT, highScore, highScoreX, textY);
    }
}
