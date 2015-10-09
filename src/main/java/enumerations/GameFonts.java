package enumerations;

import java.awt.Color;
import java.awt.Font;

/**
 * The GameFonts enumeration is used as a way to easily reference commonly used font styles.
 * These font styles are structured in such a way that they can easily be used to draw text with outlines.
 */
public enum GameFonts {
    TITLE(Color.WHITE, Color.BLACK, 2, new Font("Times New Roman", Font.BOLD, 125)),
    LAYER_TITLE(Color.WHITE, Color.BLACK, 2, new Font("Times New Roman", Font.BOLD, 85)),
    MENU(Color.WHITE, Color.BLACK, 1, new Font("Times New Roman", Font.BOLD, 75)),
    MENU_SELECTED(Color.YELLOW, Color.BLACK, 1, new Font("Times New Roman", Font.BOLD, 75)),
    GAME_TEXT(Color.WHITE, Color.BLACK, 1, new Font("Times New Roman", Font.BOLD, 45)),
    TEXT(Color.WHITE, Color.BLACK, 1, new Font("Arial", Font.BOLD, 45)),
    TEXT_GRAY(Color.LIGHT_GRAY, Color.BLACK, 1, new Font("Arial", Font.BOLD, 45)),
    DEBUG(Color.BLACK, Color.BLACK, 1, new Font("Times New Roman", Font.PLAIN, 25));

    private Color fill;
    private Color outlineColor;
    private float outlineSize;
    private Font font;

    /**
     * The font style properties.
     * @param fill the colour of the text.
     * @param outlineColor the colour of the outline.
     * @param outlineSize the thickness of the outline.
     * @param font the font.
     */
    private GameFonts(Color fill, Color outlineColor, float outlineSize, Font font){
        this.fill = fill;
        this.outlineColor = outlineColor;
        this.outlineSize = outlineSize;
        this.font = font;
    }

    /**
     * Returns the colour of the text.
     * @return the fill colour.
     */
    public Color getFill(){
        return fill;
    }

    /**
     * Returns the colour of the outline.
     * @return the outline colour.
     */
    public Color getOutlineColor(){
        return outlineColor;
    }

    /**
     * Returns the thickness of the outline.
     * @return the outline size.
     */
    public float getOutlineSize(){
        return outlineSize;
    }

    /**
     * Returns the font.
     * @return the font.
     */
    public Font getFont(){
        return font;
    }
}
