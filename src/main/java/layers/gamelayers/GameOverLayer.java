package layers.gamelayers;

import java.awt.Graphics2D;

import enumerations.Directions;
import enumerations.GameFonts;
import enumerations.MenuItems;
import games.SinglePlayerGame;
import highscores.HighScores;
import layers.BackgroundLayer;
import menus.HorizontalMenu;
import menus.InputMenu;
import menus.VerticalMenu;
import settings.ScreenSettings;
import tools.layertools.FontOutline;
import tools.layertools.SelectionArrow;

/**
 * The GameOverLayer class represents  the specific set of graphic elements that make up the game over screen.
 * The game over screen displays the score achieved, the current highest score,
 * and options to exit or restart.
 * It also gives the player the ability to input a 3-character name to be associated with the score achieved.
 */
public class GameOverLayer extends BackgroundLayer implements VerticalMenu, HorizontalMenu, InputMenu{

    private String title;
    private SinglePlayerGame singlePlayerGame;

    private char[] name;
    private int currentSelectionRow;
    private int currentSelectionColumn;
    private int numberMenuRows;
    private int numberMenuColumns;

    private MenuItems[] menuItems;

    private int textY;

    /**
     * Initialise game over screen.
     * @param title title of the screen
     * @param singlePlayerGame the current game
     */
    public GameOverLayer(String title, SinglePlayerGame singlePlayerGame){
        // Initialise background layer
        super();

        // Title to be displayed
        this.title = title;

        // Associated single player game
        this.singlePlayerGame = singlePlayerGame;

        // Initialise player name
        name = "AAA".toCharArray();

        // Initialise menu items
        currentSelectionRow = 0;
        currentSelectionColumn = 0;
        numberMenuRows = 6;
        numberMenuColumns = 3;
        menuItems = new MenuItems[numberMenuRows];

        // Set the available menu items
        menuItems[0] = MenuItems.INCREMENT;
        menuItems[1] = MenuItems.SET_VALUE;
        menuItems[2] = MenuItems.DECREMENT;
        menuItems[3] = MenuItems.RESTART;
        menuItems[4] = MenuItems.TITLE_SCREEN;
        menuItems[5] = MenuItems.EXIT;
    }

    @Override
    protected void drawLayer(Graphics2D screen) {
        // Title
        int titleY = 100;
        FontOutline.drawTextHorizontallyCentered(screen, GameFonts.TITLE, title, titleY);

        // Initial text depth
        textY = 150;
        // Draw score information
        drawScoreInfo(screen);
        // Draw high score input
        drawScoreInput(screen);
        // Draw menu items
        drawMenu(screen);
    }

    /**
     * Draws the score achieved by the player and the current high score,
     * to the specified screen.
     * @param screen the screen.
     */
    private void drawScoreInfo(Graphics2D screen) {
        int textSpacing = 50;
        String scoreText = "Score: " + getScore();
        FontOutline.drawTextHorizontallyCentered(screen, GameFonts.TEXT, scoreText, textY);
        // Update text cursor
        textY += textSpacing;
        String highScoreText = "High Score: " + getHighScore();
        FontOutline.drawTextHorizontallyCentered(screen, GameFonts.TEXT, highScoreText, textY);
        // Update text cursor
        textY += 25;
    }

    /**
     * Draws the score input section to the specified screen.
     * Where the user can input the name to be associated with the achieved score.
     * @param screen the screen.
     */
    private void drawScoreInput(Graphics2D screen) {
        double screenWidth = ScreenSettings.getInstance().getWidth();
        int centreX = (int)(screenWidth / 2);
        // Top Arrow Row Selected
        drawSelectionArrow(screen, (currentSelectionRow == 0), centreX, Directions.UP);
        // Name Row Selected
        drawName(screen, (currentSelectionRow == 1), centreX);
        // Bottom Arrow Row Selected
        drawSelectionArrow(screen, (currentSelectionRow == 2), centreX, Directions.DOWN);
    }

    /**
     * Draws the arrows which are part of the score input section.
     * @param screen the screen.
     * @param selectedRow whether or not the current row is selected.
     * @param centreX the horizontal centre of the screen.
     * @param direction which direction the arrow should be facing.
     */
    private void drawSelectionArrow(Graphics2D screen, boolean selectedRow, int centreX, Directions direction){
        int numberArrows = 3;
        int arrowWidth = 50;
        int stepX = arrowWidth + 50;
        int startX = centreX - stepX;
        for (int i = 0; i < numberArrows; i++) {
            SelectionArrow.drawSelectionArrow(screen, startX, textY, arrowWidth, direction, (selectedRow && (currentSelectionColumn == i)));
            // Update cursor
            startX += stepX;
        }
        // Update cursor
        textY += 125;
    }

    /**
     * Draws the current character array, which represents the name,
     * which is part of the score input section.
     * @param screen the screen.
     * @param selectedRow whether or not the current row is selected.
     * @param centreX the horizontal centre of the screen.
     */
    private void drawName(Graphics2D screen, boolean selectedRow, int centreX){
        // Update cursor
        textY -= 15;
        int stepX = 100;
        int stepY = 10;
        int startX = centreX - stepX;
        GameFonts font;
        for (int i = 0; i < name.length; i++) {
            font = GameFonts.MENU;
            String letter = "" + name[i];
            if (selectedRow && (currentSelectionColumn == i)) {
                font = GameFonts.MENU_SELECTED;
            }
            FontOutline.drawText(screen, font, letter, startX, textY);
            // Update cursor
            startX += stepX;
        }
        // Update cursor
        textY += stepY;
    }

    /**
     * Draws the additional menu options to the screen.
     * @param screen the screen.
     */
    private void drawMenu(Graphics2D screen) {
        // The row index of the 'actual' menu items to be displayed
        int menuListRowIndex = 3;
        int itemSpacing = 100;

        // For each menu item
        for (int i = menuListRowIndex; i < numberMenuRows; i++){
            GameFonts menuItemFont = GameFonts.MENU;
            if (i == currentSelectionRow) {
                // Highlight selected
                menuItemFont = GameFonts.MENU_SELECTED;
            }
            // Draw menu item
            FontOutline.drawTextHorizontallyCentered(screen, menuItemFont, menuItems[i].getText(), textY);
            // Update the text cursor
            textY += itemSpacing;
        }
    }

    public MenuItems getSelection() {
        return menuItems[currentSelectionRow];
    }

    public void incrementSelection() {
        /*
         * Since the character array is the only input option
         * we do not have to check if the option to increment the character array has been selected
         */
        // Get current value
        char oldValue = name[currentSelectionColumn];
        char newValue = 'A';
        if (oldValue != 'Z'){
            /*
             * If the current value is not the last letter
             * Then get the next letter
             */
            int oldIndex = (int)oldValue;
            newValue = (char) (oldIndex + 1);
        }
        // Set new value
        name[currentSelectionColumn] = newValue;
    }

    public void decrementSelection() {
        /*
         * Since the character array is the only input option
         * we do not have to check if the option to decrement the character array has been selected
         */
        // Get current value
        char oldValue = name[currentSelectionColumn];
        char newValue = 'Z';
        if (oldValue != 'A'){
            /*
             * If the current value is not the first letter
             * Then get the previous letter
             */
            int oldIndex = (int)oldValue;
            newValue = (char) (oldIndex-1);
        }
        // Set new value
        name[currentSelectionColumn] = newValue;
    }

    public void setValue(char value) {
        /*
         * Since the character array is the only input option
         * we do not have to check if the option to input the character array has been selected
         */
        if (Character.isAlphabetic(value)) {
            // Set new value
            name[currentSelectionColumn] = Character.toUpperCase(value);
            // Go to the next character
            navigateRight();
        }
    }

    public void navigateLeft() {
        currentSelectionColumn--;
        if (currentSelectionColumn <  0) {
            currentSelectionColumn = numberMenuColumns - 1;
        }
    }

    public void navigateRight() {
        currentSelectionColumn = (currentSelectionColumn + 1) % numberMenuColumns;
    }

    public void navigateUp() {
        currentSelectionRow--;
        if (currentSelectionRow <  0) {
            currentSelectionRow = numberMenuRows - 1;
        }
    }

    public void navigateDown() {
        currentSelectionRow = (currentSelectionRow + 1) % numberMenuRows;
    }

    /**
     * Returns the score achieved by the player in the specified single player game.
     * @return the current score.
     */
    private int getScore(){
        return singlePlayerGame.getPlayer().getCurrentScore();
    }

    /**
     * Returns the current highest score.
     * @return the highest score.
     */
    private int getHighScore(){
        int highScore = HighScores.getInstance().getHighestScore().getScore();
        int currentScore = getScore();
        if (currentScore > highScore) {
            return currentScore;
        }
        return highScore;
    }

    /**
     * Returns the entered 3 character array,
     * which represents the name to be associated with the achieved score.
     * @return the name.
     */
    public String getName(){
        return new String(name);
    }
}
