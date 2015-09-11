package handlers;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.GlyphVector;
import java.awt.geom.Rectangle2D;

/**
 * The FontOutlineHandler class is used to draw text with an outline.
 */
public class FontOutlineHandler {

    /**
     * Draws the specified text with the specified settings onto the specified 2-dimensional image.
     *
     * @param graphic a 2-dimensional image
     * @param font the font desired
     * @param text the text to be displayed
     * @param fillColor the colour of the text to be displayed
     * @param outlineColor the colour of the outline to be drawn around the text
     * @param outlineSize the thickness of the outline
     * @param startX the x-coordinate from which the text shall start
     * @param startY the y-coordinate from which the text shall start
     */
    public static void drawText(Graphics2D graphic, Font font, String text, Color fillColor, Color outlineColor, float outlineSize, int startX, int startY){
        // Splits the specified text into separate letters or 'glyphs'
        GlyphVector gv = font.createGlyphVector(graphic.getFontRenderContext(), text);

        // Sets the origin which the location of the text drawn shall be relative to
        graphic.translate(startX, startY);
        graphic.setStroke(new BasicStroke(outlineSize));

        // Draw the letter and outline for every letter in the specified text
        for(int i = 0; i < text.length(); i++){
            Shape shape = gv.getGlyphOutline(i);
            graphic.setPaint(fillColor);
            graphic.fill(shape);
            graphic.setPaint(outlineColor);
            graphic.draw(shape);
        }

        // Reset the origin
        graphic.translate((-1 * startX),(-1 * startY));
    }

    /**
     * Uses the dimensions specified in the OptionHandler to draw the specified text in the horizontal centre of the screen.
     *
     * @param graphic a 2-dimensional image
     * @param font the font desired
     * @param text the text to be displayed
     * @param fillColor the colour of the text to be displayed
     * @param outlineColor the colour of the outline to be drawn around the text
     * @param outlineSize the thickness of the outline
     * @param startY the y-coordinate from which the text shall start
     */
    public static void drawTextCenterWidth(Graphics2D graphic, Font font, String text, Color fillColor, Color outlineColor, float outlineSize, int startY){
        // Gets the estimated width of the text
        double textWidth = getFontWidth(graphic, font, text);
        double centerScreen = OptionsHandler.getInstance().getWidth() / 2.0;
        // Gets the horizontal centre of the screen adjusting for the width of the text
        int startX = (int) (centerScreen - (textWidth / 2.0));
        drawText(graphic, font, text, fillColor, outlineColor, outlineSize, startX, startY);
    }

    /**
     * Returns an approximation of the width of the text given the font desired.
     *
     * @param graphic a 2-dimensional image
     * @param font the font desired
     * @param text the text
     * @return the width of the string
     */
    private static double getFontWidth(Graphics2D graphic, Font font, String text){
        Rectangle2D fontBox = font.getStringBounds(text, graphic.getFontRenderContext());
        return fontBox.getWidth();
    }
}
