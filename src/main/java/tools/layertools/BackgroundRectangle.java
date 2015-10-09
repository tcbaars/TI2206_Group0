package tools.layertools;

import java.awt.Color;
import java.awt.Graphics2D;

import settings.ScreenSettings;

/**
 * The BackgroundRectangle class is a utility for drawing a some-what opaque rectangle to the specified screen.
 * The rectangle is purely used for aesthetics.
 */
public class BackgroundRectangle {

    private static final int alphaValue = 192;
    private static final Color backgorundColor = new Color(0,0,0, alphaValue);
    private static final int arcWidth = 50;
    private static final int arcHeight = 50;

    /**
     * Draws a opaque rounded rectangle at the specified location to the screen.
     * With the specified dimensions.
     * @param screen the screen.
     * @param startX the X coordinate of the top-left corner.
     * @param startY the Y coordinate of the top-left corner.
     * @param width the desired rectangle width.
     * @param height the desired rectangle height.
     * @return <code>true</code> if the rectangle was successfully drawn to the screen, otherwise <code>false</code>.
     */
    public static void drawRectangle(Graphics2D screen, int startX, int startY, int width, int height){
        if (screen != null){
            // Get the current colour
            Color tempColor = screen.getColor();
            // Change the current colour
            screen.setColor(backgorundColor);
            // Draw rounded rectangle
            screen.fillRoundRect(startX, startY, width, height, arcWidth, arcHeight);
            // Restore the previous current colour
            screen.setColor(tempColor);
        }
    }

    /**
     * Draws a opaque rounded rectangle at the centre of the screen.
     * With the specified dimensions.
     * @param screen the screen.
     * @param width the desired rectangle width.
     * @param height the desired rectangle height.
     */
    public static void drawCenteredRectangle(Graphics2D screen, int width, int height){
        // Get the centre of the screen
        double centerX = ScreenSettings.getInstance().getWidth() / 2.0;
        double centerY = ScreenSettings.getInstance().getHeight() / 2.0;
        // Offset the top-left corner to account for the rectangle dimensions
        int startX = (int)(centerX - (width / 2.0));
        int startY = (int)(centerY - (height / 2.0));
        startY -= 35;
        drawRectangle(screen, startX, startY, width, height);
    }
}
