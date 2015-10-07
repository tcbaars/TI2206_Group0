package layers;

import java.awt.Graphics2D;
import java.util.Iterator;

import enumerations.GameFonts;
import enumerations.MenuItems;
import highscores.HighScores;
import highscores.Score;
import menus.Menu;
import settings.ScreenSettings;
import tools.layertools.BackgroundRectangle;
import tools.layertools.FontOutline;

/**
 * The HighScoresLayer class represents the specific set of graphic elements that make up the game's high scores screen.
 * The high score screen displays the current leaderboard.
 */
public class HighScoresLayer extends BackgroundLayer implements Menu{

    private int textY;
    private MenuItems back;

    /**
     * Initialise the high screen.
     */
    public HighScoresLayer(){
        // Initialise the background layer
        super();

        // Single menu item, to go back to the title screen
        back = MenuItems.TITLE_SCREEN;
    }

    /**
     * Draws the high score screen specific graphic elements.
     * @param screen the screen.
     */
    @Override
    protected void drawLayer(Graphics2D screen) {
        //Draw background rectangle
        BackgroundRectangle.drawCenteredRectangle(screen, 900, 600);

        // Title text to be displayed
        String title = "HIGH SCORES";
        int titleY = 100;

        // Initial text cursor depth
        textY = 165;

        // Draw title
        FontOutline.drawTextHorizontallyCentered(screen, GameFonts.LAYER_TITLE, title, titleY);

        // Draw leaderboard
        drawLeaderboard(screen);

        // Draw back option (selected by default)
        textY+=25;
        FontOutline.drawTextHorizontallyCentered(screen, GameFonts.MENU_SELECTED, back.getText(), textY);
    }

    /**
     * Draws the current leaderboard.
     * @param screen the screen.
     */
    private void drawLeaderboard(Graphics2D screen){
        // Index starts at 1st place
        int scoreIndex = 1;
        // Score properties
        Score score;
        String name;
        String scoreValue;
        // Score display properties
        int nameX = (ScreenSettings.getInstance().getWidth() / 2) - 70;
        int indexX = nameX - 200;
        int scoreX = nameX + 300;
        int textSpacing = 50;

        // Get current leaderboard
        Iterator<Score> leaderboard = HighScores.getInstance().getLeaderBoard();
        // For each score in the leaderboard
        while (leaderboard.hasNext()) {
            GameFonts textFont = GameFonts.TEXT;
            if ((scoreIndex % 2) == 0) {
                // Highlight alternate rows to improve readability
                textFont = GameFonts.TEXT_GRAY;
            }
            // Get score properties
            score = leaderboard.next();
            name = score.getName();
            scoreValue = "" + score.getScore();
            // Draw index
            FontOutline.drawText(screen, textFont, scoreIndex + ".", indexX, textY);
            // Draw name
            FontOutline.drawText(screen, textFont, name, nameX, textY);
            // Draw score
            FontOutline.drawText(screen, textFont, scoreValue, scoreX, textY);
            // Update cursor and index
            textY += textSpacing;
            scoreIndex++;
        }
    }

    public MenuItems getSelection() {
        return back;
    }
}
