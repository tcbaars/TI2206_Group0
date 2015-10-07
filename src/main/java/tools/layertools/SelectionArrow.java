package tools.layertools;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;


import enumerations.Directions;

public class SelectionArrow {

    private static Polygon[] getRightTriangle(int width){
        int half = width / 2;
        Polygon outline = new Polygon(
                new int[]{0, width, 0},
                new int[]{0, half, width},
                3
        );
        int outlinesize = 5;
        Polygon fill = new Polygon(
                new int[]{0 + outlinesize, width - outlinesize, 0 + outlinesize},
                new int[]{0 + outlinesize, half, width - outlinesize},
                3
        );
        Polygon triangle[] = {outline,fill};
        return triangle;
    }
    private static Polygon[] getLeftTriangle(int width){
        int half = width / 2;
        Polygon outline = new Polygon(
                new int[]{width, width, 0},
                new int[]{0, width, half},
                3
        );
        int outlinesize = 5;
        Polygon fill = new Polygon(
                new int[]{width - outlinesize, width - outlinesize, 0 + outlinesize},
                new int[]{0 + outlinesize, width - outlinesize, half},
                3
        );
        Polygon triangle[] = {outline,fill};
        return triangle;
    }
    private static Polygon[] getUpTriangle(int width){
        int half = width / 2;
        Polygon outline = new Polygon(
                new int[]{half, width, 0},
                new int[]{0, width, width},
                3
        );
        int outlinesize = 5;
        Polygon fill = new Polygon(
                new int[]{half, width - outlinesize, 0 + outlinesize},
                new int[]{0 + outlinesize, width - outlinesize, width - outlinesize},
                3
        );
        Polygon triangle[] = {outline,fill};
        return triangle;
    }
    private static Polygon[] getDownTriangle(int width){
        int half = width / 2;
        Polygon outline = new Polygon(
                new int[]{width, half, 0},
                new int[]{0, width, 0},
                3
        );
        int outlinesize = 5;
        Polygon fill = new Polygon(
                new int[]{width - outlinesize, half, 0 + outlinesize},
                new int[]{0 + outlinesize, width - outlinesize, 0 + outlinesize},
                3
        );
        Polygon triangle[] = {outline,fill};
        return triangle;
    }
    private static Polygon[] getSelectionArrow(int width, Directions direction){
        switch (direction) {
            case UP:
                return getUpTriangle(width);
            case DOWN:
                return getDownTriangle(width);
            case LEFT:
                return getLeftTriangle(width);
            case RIGHT:
                return getRightTriangle(width);
            default:
                return null;
        }
    }
    public static boolean drawSelectionArrow(Graphics2D screen, int startX, int startY, int width, Directions direction, boolean selected){
        Polygon triangles[] = getSelectionArrow(width, direction);
        Color tempColor = screen.getColor();
        Color outlineColor = Color.BLACK;
        Color fillColor = Color.WHITE;
        if (selected) {
            fillColor = Color.YELLOW;
        }
        screen.translate(startX, startY);
        screen.setColor(outlineColor);
        screen.fill(triangles[0]);
        screen.setColor(fillColor);
        screen.fill(triangles[1]);
        screen.translate((-1) * startX, (-1) * startY);
        screen.setColor(tempColor);
        return true;
    }

}
