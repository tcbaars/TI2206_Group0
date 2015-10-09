package layers.gamelayers;

import java.awt.Graphics2D;

import enumerations.GameFonts;
import enumerations.MenuItems;
import layers.BackgroundLayer;
import menus.VerticalMenu;
import tools.layertools.BackgroundRectangle;
import tools.layertools.FontOutline;

/**
 * The PauseLayer class represents the specific set of graphic elements that make up the game's pause screen.
 * The pause screen displays a menu when the game is paused, from which the game can be resumed,
 * or the user can go back to the title screen, or exit the application.
 */
public class PauseLayer extends BackgroundLayer implements VerticalMenu{

    private int currentSelection;
    private int numberMenuItems;

    private MenuItems[] menuItems;

    /**
     * Initialise pause layer.
     */
    public PauseLayer(){
        // Initialise background layer
        super();

        // Initialise menu items
        currentSelection = 0;
        numberMenuItems = 3;
        menuItems = new MenuItems[numberMenuItems];

        // Set the available menu items
        menuItems[0] = MenuItems.RESUME;
        menuItems[1] = MenuItems.TITLE_SCREEN;
        menuItems[2] = MenuItems.EXIT;
    }

    /**
     * Draws the pause screen specific graphic elements.
     * @param screen the screen.
     */
    @Override
    protected void drawLayer(Graphics2D screen) {
        // Draw the background rectangle
        BackgroundRectangle.drawCenteredRectangle(screen, 900, 600);

        // Title text to be displayed
        String title = "PAUSED";
        int titleY = 150;

        // Draw title
        FontOutline.drawTextHorizontallyCentered(screen, GameFonts.TITLE, title, titleY);

        // Menu item starting depth and spacing
        int itemStartY = 350;
        int itemSpacing = 100;

        // For each menu item
        for (int i = 0; i < numberMenuItems; i++){
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

    public MenuItems getSelection() {
        return menuItems[currentSelection];
    }

    public void navigateUp() {
        currentSelection--;

        if (currentSelection < 0) {
            currentSelection = numberMenuItems - 1;
        }
    }

    public void navigateDown() {
        currentSelection = (currentSelection + 1) % numberMenuItems;
    }

}
