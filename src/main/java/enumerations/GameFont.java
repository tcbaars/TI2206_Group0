package enumerations;

import java.awt.Color;
import java.awt.Font;

public enum GameFont {
    TITLE(Color.WHITE, Color.BLACK, 2, new Font("Times New Roman", Font.BOLD, 100)),
    OPTION(Color.WHITE, Color.BLACK, 1, new Font("Times New Roman", Font.BOLD, 85)),
<<<<<<< HEAD
    SELECTED(Color.WHITE, Color.YELLOW, 1, new Font("Times New Roman", Font.BOLD, 85)),
=======
    SELECTED(Color.YELLOW, Color.BLACK, 1, new Font("Times New Roman", Font.BOLD, 85)),
>>>>>>> 6ecd746c29c9ec1f6cc2281dc9043021d708f2c5
    TEXT(Color.WHITE, Color.BLACK, 1, new Font("Times New Roman", Font.BOLD, 50));


    private Color fill;
    private Color outlineColor;
    private float outlineSize;
    private Font font;

    private GameFont(Color fill,  Color outlineColor, float outlineSize, Font font){
      this.fill = fill;
      this.outlineColor = outlineColor;
      this.outlineSize = outlineSize;
      this.font = font;
    }

    public Color getFill(){
        return fill;
    }

    public Color getOultineColor(){
        return outlineColor;
    }

    public float getOutlineSize(){
        return outlineSize;
    }

    public Font getFont(){
        return font;
    }
}
