package layers;

import java.awt.Graphics2D;

import enumerations.GameFonts;
import enumerations.MenuItems;
import menus.VerticalMenu;
import tools.layertools.BackgroundRectangle;
import tools.layertools.FontOutline;

/**
 * The TitleLayer class represents the specific set of graphic elements that make up the game's title screen.
 * The title screen displays the title menu, which allows the user to launch a new game,
 * view high scores, view instructions, or exit the application.
 */
public class TitleLayer extends BackgroundLayer implements VerticalMenu{

    private int currentSelection;
    private int numberMenuItems;

    private MenuItems[] menuItems;

    /**
     * Initialises the title screen.
     */
    public TitleLayer(){
        // Initialise the background layer
        super();

        // Initialise menu items
        currentSelection = 0;
        numberMenuItems = 4;
        menuItems = new MenuItems[numberMenuItems];

        // Set the available menu items
        menuItems[0] = MenuItems.CLASSIC;
        menuItems[1] = MenuItems.HIGH_SCORES;
        menuItems[2] = MenuItems.INSTRUCTIONS;
        menuItems[3] = MenuItems.EXIT;
    }

    /**
     * Draws the title screen specific graphic elements.
     * @param screen the screen.
     */
    protected void drawLayer(Graphics2D screen){
        // Draw background rectangle
        BackgroundRectangle.drawCenteredRectangle(screen, 900, 600);

        // Title text to be displayed
        String title = "FISHY GAME";
        int titleY = 150;

        // Draw title text
        FontOutline.drawTextHorizontallyCentered(screen, GameFonts.TITLE, title, titleY);

        // Menu item starting depth and spacing
        int itemStartY = 300;
        int itemSpacing = 75;

        // For each menu item
        for (int i = 0; i < numberMenuItems; i++) {
            int itemY = itemStartY + (i * itemSpacing);
            GameFonts menuItemFont = GameFonts.MENU;
            if (i == currentSelection) {
                // Highlight selected
                menuItemFont = GameFonts.MENU_SELECTED;
            }
            // Draw menu item
            FontOutline.drawTextHorizontallyCentered(screen, menuItemFont, menuItems[i].getText(), itemY);
        }
    }

    public MenuItems getSelection(){
        return menuItems[currentSelection];
    }

    public void navigateUp(){
        currentSelection--;

        if (currentSelection < 0) {
            currentSelection = numberMenuItems - 1;
        }
    }

    public void navigateDown(){
        currentSelection = (currentSelection + 1) % numberMenuItems;
    }
}
