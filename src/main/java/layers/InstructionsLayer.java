package layers;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import enumerations.GameFonts;
import enumerations.GameImages;
import enumerations.MenuItems;
import menus.Menu;
import tools.layertools.BackgroundRectangle;
import tools.layertools.FontOutline;
import tools.resourcetools.ImageLoader;

/**
 * The InstructionsLayer class represents the specific set of graphic elements that make up the game's instructions screen.
 * The instructions screen displays the game rules and controls.
 */
public class InstructionsLayer extends BackgroundLayer implements Menu{

    private int textY;
    private MenuItems back;

    /**
     * Initialises the instruction screen.
     */
    public InstructionsLayer(){
        // Initialised the background layer
        super();

        // Load image resources
        ImageLoader.getInstance().loadImage(GameImages.DIRECTION_KEYS);
        ImageLoader.getInstance().loadImage(GameImages.M_KEY);
        ImageLoader.getInstance().loadImage(GameImages.S_KEY);
        ImageLoader.getInstance().loadImage(GameImages.ESC_KEY);

        // Single menu item, to go back to the title screen
        back = MenuItems.TITLE_SCREEN;
    }

    /**
     * Draws the instructions screen specific graphic elements.
     * @param screen the screen.
     */
    @Override
    protected void drawLayer(Graphics2D screen) {
        // Draw background rectangle
        BackgroundRectangle.drawCenteredRectangle(screen, 900, 600);

        // Title text to be displayed
        String title = "INSTRUCTIONS";
        int titleY = 100;

        // Draw title centre aligned
        FontOutline.drawTextHorizontallyCentered(screen, GameFonts.LAYER_TITLE, title, titleY);

        // Initial text cursor depth
        textY = 175;

        // Draw instructions
        drawInstructions(screen);

        //Draw control explanation
        drawKeyHelp(screen);

        // Draw back menu item (selected by default) centre aligned
        textY+=45;
        FontOutline.drawTextHorizontallyCentered(screen, GameFonts.MENU_SELECTED, back.getText(), textY);
    }


    /**
     * Draws the text on how to play the game.
     * @param screen the screen.
     */
    private void drawInstructions(Graphics2D screen){
        // Instructions
        String[] text = {
            "Eat smaller fish to grow bigger.",
            "Avoid larger fish.",
            "The larger the fish, the more points", "you receive when you eat it."
        };

        int lineWidth = 50;

        // For each instruction line
        for (int i = 0; i < text.length; i++) {
            String line = text[i];
            int startY = textY;
            // Draw the line of text
            FontOutline.drawTextHorizontallyCentered(screen, GameFonts.TEXT, line, startY);
            // Update the global cursor
            textY += lineWidth;
        }
    }

    /**
     * Draws the text and icons on what the controls are.
     * @param screen the screen.
     */
    private void drawKeyHelp(Graphics2D screen){
        // Create a space between the instructions and the controls explanation
        textY += 25;

        // Controls explanation text
        String[] text = {
            "to move",
            "to pause the game",
            "to toggle sound",
            "to toggle music",
        };

        // Icons
        int iconX = 350;
        GameImages[] icons = {
                GameImages.DIRECTION_KEYS,
                GameImages.ESC_KEY,
                GameImages.S_KEY,
                GameImages.M_KEY,
        };

        int lineWidth = 60;

        // For each line
        for (int i = 0; i < text.length; i++){
            String line = text[i];
            BufferedImage icon = ImageLoader.getInstance().getImage(icons[i]);
            int startY = textY;
            // Draw line of text
            FontOutline.drawTextHorizontallyCentered(screen, GameFonts.TEXT, line, startY);
            int iconY = textY - 45;
            // Draw associated icon
            screen.drawImage(icon, iconX, iconY, 60, 60, null);
            // Update the global cursor
            textY += lineWidth;
        }
    }

    public MenuItems getSelection() {
        return back;
    }
}
