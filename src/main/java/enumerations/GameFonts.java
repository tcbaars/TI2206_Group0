package enumerations;

import java.awt.Color;
import java.awt.Font;

/**
 * The GameFonts enumeration is used as a way to easily reference commonly used font styles.
 * These font styles are structured in such a way that they can easily be used to draw text with outlines.
 * <ul>
 * <li>{@link #TITLE}</li>
 * <li>{@link #LAYER_TITLE}</li>
 * <li>{@link #MENU}</li>
 * <li>{@link #MENU_SELECTED}</li>
 * <li>{@link #GAME_TEXT}</li>
 * <li>{@link #TEXT}</li>
 * <li>{@link #TEXT_GRAY}</li>
 * <li>{@link #DEBUG}</li>
 * </ul>
 */
public enum GameFonts {
    /**
     * Used for the main headings.
     */
    TITLE(Color.WHITE, Color.BLACK, 2, new Font("Times New Roman", Font.BOLD, 125)),
    /**
     * Used for layer headings.
     */
    LAYER_TITLE(Color.WHITE, Color.BLACK, 2, new Font("Times New Roman", Font.BOLD, 85)),
    /**
     * Used for menu options.
     */
    MENU(Color.WHITE, Color.BLACK, 1, new Font("Times New Roman", Font.BOLD, 75)),
    /**
     * Used for menu options which are selected.
     */
    MENU_SELECTED(Color.YELLOW, Color.BLACK, 1, new Font("Times New Roman", Font.BOLD, 75)),
    /**
     * Used for in-game text.
     */
    GAME_TEXT(Color.WHITE, Color.BLACK, 1, new Font("Times New Roman", Font.BOLD, 45)),
    /**
     * Used for text.
     */
    TEXT(Color.WHITE, Color.BLACK, 1, new Font("Arial", Font.BOLD, 45)),
    /**
     * Used for text with a grey highlight.
     */
    TEXT_GRAY(Color.LIGHT_GRAY, Color.BLACK, 1, new Font("Arial", Font.BOLD, 45)),
    /**
     * Used to display text used for debugging purposes to the screen.
     */
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
