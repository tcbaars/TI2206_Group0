package tools.layertools;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.GlyphVector;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import enumerations.GameFonts;
import settings.ScreenSettings;

/**
 * The FontOutline class is a utility for drawing text with an outline to the specified image.
 */
public class FontOutline {

    /**
     * Draws the specified text with the specified font style to the screen.
     * Starting from the specified starting position.
     * @param screen the screen.
     * @param font the font style.
     * @param text the text to be displayed.
     * @param startX the X coordinate of the starting position of the text.
     * @param startY the Y coordinate of the starting position of the text
     * @return <code>true</code> if the text was successfully drawn, otherwise <code>false<code>.
     */
    public static boolean drawText(Graphics2D screen, GameFonts font, String text, int startX, int startY){
        return drawText(screen, font.getFont(), font.getFill(), font.getOutlineColor(), font.getOutlineSize(), text, startX, startY);
    }

    /**
     * Draws the specified text with the specified font style to the screen.
     * Starting from the specified starting position.
     * @param screen the screen.
     * @param font the font.
     * @param fill the font colour.
     * @param outlineColor the outline colour.
     * @param outlineSize the outline thickness.
     * @param text the text to be displayed.
     * @param startX the X coordinate of the starting position of the text.
     * @param startY the Y coordinate of the starting position of the text.
     * @return <code>true</code> if the text was successfully drawn, otherwise <code>false<code>.
     */
    public static boolean drawText(Graphics2D screen, Font font, Color fill, Color outlineColor, float outlineSize, String text, int startX, int startY){
       if (screen != null &&
               font != null &&
               fill != null &&
               outlineColor != null &&
               text != null) {
                    // Splits the specified text into separate letters or 'glyphs'
                    GlyphVector gv = font.createGlyphVector(screen.getFontRenderContext(), text);

                    // Sets the origin which the location of the text drawn shall be relative to
                    screen.translate(startX, startY);
                    screen.setStroke(new BasicStroke(outlineSize));

                    // Draw the letter and outline for every letter in the specified text
                    for(int i = 0; i < text.length(); i++){
                        Shape shape = gv.getGlyphOutline(i);
                        screen.setPaint(fill);
                        screen.fill(shape);
                        screen.setPaint(outlineColor);
                        screen.draw(shape);
                    }

                    // Reset the origin
                    screen.translate((-1 * startX),(-1 * startY));

                    return true;
       }
       return false;
    }

    /**
     * Draws the specified text with the specified font style to the screen.
     * Starting at the specified depth, and centred horizontally according to the window dimensions.
     * @param screen the screen.
     * @param font the font style.
     * @param text the text to be displayed.
     * @param startY the Y coordinate of the starting position of the text.
     * @return <code>true</code> if the text was successfully drawn, otherwise <code>false<code>.
     */
    public static boolean drawTextHorizontallyCentered(Graphics2D screen, GameFonts font, String text, int startY){
        return drawTextHorizontallyCentered(screen, font.getFont(), font.getFill(), font.getOutlineColor(), font.getOutlineSize(), text, startY);
    }

    /**
     * Draws the specified text with the specified font style to the screen.
     * Starting at the specified depth, and centred horizontally according to the window dimensions.
     * @param screen the screen.
     * @param font the font.
     * @param fill the font colour.
     * @param outlineColor the outline colour.
     * @param outlineSize the thickness of the outline.
     * @param text the text to be displayed.
     * @param startY the Y coordinate of the starting position of the text.
     * @return <code>true</code> if the text was successfully drawn, otherwise <code>false<code>.
     */
    public static boolean drawTextHorizontallyCentered(Graphics2D screen, Font font, Color fill, Color outlineColor, float outlineSize, String text, int startY){
        if (screen != null && font != null && text != null){
            // Gets the estimated width of the text
            double textWidth = getFontWidth(font, text);
            double centerScreen = ScreenSettings.getInstance().getWidth() / 2.0;
            // Gets the horizontal centre of the screen adjusting for the width of the text
            int startX = (int) (centerScreen - (textWidth / 2.0));
            return drawText(screen, font, fill, outlineColor, outlineSize, text, startX, startY);
        }
        return false;
    }

    /**
     * Returns an approximation of the width of the text, given the specified font style.
     * @param screen the screen.
     * @param font the font style.
     * @param text the text to be displayed.
     * @return the width of the text.
     */
    public static double getFontWidth(Font font, String text){
        Graphics2D screen = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB).createGraphics();
        screen.setFont(font);
        Rectangle2D fontBox = font.getStringBounds(text, screen.getFontRenderContext());
        return fontBox.getWidth();
    }

    /**
     * Returns an approximation of the height of the text, given the specified font style.
     * @param screen the screen.
     * @param font the font style.
     * @param text the text to be displayed.
     * @return the height of the text.
     */
    public static double getFontHeight(Font font, String text){
        Graphics2D screen = new BufferedImage(1,1,BufferedImage.TYPE_INT_RGB).createGraphics();
        screen.setFont(font);
        Rectangle2D fontBox = font.getStringBounds(text, screen.getFontRenderContext());
        return fontBox.getWidth();
    }
}
