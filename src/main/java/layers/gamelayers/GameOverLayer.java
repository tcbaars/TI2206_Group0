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
     * @param title Title of the screen
     * @param singlePlayerGame The current game
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
        char oldValue = name[currentSelectionColumn];
        char newValue = 'A';
        if (oldValue != 'Z'){
            int oldIndex = (int)oldValue;
            newValue = (char) (oldIndex + 1);
        }
        name[currentSelectionColumn] = newValue;
    }

    public void decrementSelection() {
        char oldValue = name[currentSelectionColumn];
        char newValue = 'Z';
        if (oldValue != 'A'){
            int oldIndex = (int)oldValue;
            newValue = (char) (oldIndex-1);
        }
        name[currentSelectionColumn] = newValue;
    }

    public void setValue(char value) {
        if (Character.isAlphabetic(value)) {
            name[currentSelectionColumn] = Character.toUpperCase(value);
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

    public int getScore(){
        return singlePlayerGame.getPlayer().getCurrentScore();
    }

    private int getHighScore(){
        int highScore = HighScores.getInstance().getHighestScore().getScore();
        int currentScore = getScore();
        if (currentScore > highScore) {
            return currentScore;
        }
        return highScore;
    }

    public String getName(){
        return new String(name);
    }


}
