package tools.layertools;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;


import enumerations.Directions;

/**
 * The SelectionArrow class is a utility for drawing arrows.
 * These arrows are used to indicate the values associated can be changed, ie either incremented or decremented.
 * The arrow should have a highligh if it is selected.
 */
public class SelectionArrow {

    /**
     * Creates a set of polygons which represent an arrow facing right.
     * The first polygon represents an outline of a right facing triangle.
     * The second polygon represents the fill of a right facing triangle.
     * @param width the desired width of the arrow.
     * @return a set of polygons which represent an arrow facing right.
     */
    private static Polygon[] getRightArrow(int width){
        int half = width / 2;
        Polygon outline = new Polygon(
                new int[]{0, width, 0},
                new int[]{0, half, width},
                3
        );
        int outlinesize = 5;
        // Transform the triangle by the outline size to create an outline
        Polygon fill = new Polygon(
                new int[]{0 + outlinesize, width - outlinesize, 0 + outlinesize},
                new int[]{0 + outlinesize, half, width - outlinesize},
                3
        );
        Polygon triangle[] = {outline,fill};
        return triangle;
    }

    /**
     * Creates a set of polygons which represent an arrow facing left.
     * The first polygon represents an outline of a left facing triangle.
     * The second polygon represents the fill of a left facing triangle.
     * @param width the desired width of the arrow.
     * @return a set of polygons which represent an arrow facing left.
     */
    private static Polygon[] getLeftArrow(int width){
        int half = width / 2;
        Polygon outline = new Polygon(
                new int[]{width, width, 0},
                new int[]{0, width, half},
                3
        );
        int outlinesize = 5;
        // Transform the triangle by the outline size to create an outline
        Polygon fill = new Polygon(
                new int[]{width - outlinesize, width - outlinesize, 0 + outlinesize},
                new int[]{0 + outlinesize, width - outlinesize, half},
                3
        );
        Polygon triangle[] = {outline,fill};
        return triangle;
    }

    /**
     * Creates a set of polygons which represent an arrow facing up.
     * The first polygon represents an outline of an up facing triangle.
     * The second polygon represents the fill of an up facing triangle.
     * @param width the desired width of the arrow.
     * @return a set of polygons which represent an arrow facing up.
     */
    private static Polygon[] getUpArrow(int width){
        int half = width / 2;
        Polygon outline = new Polygon(
                new int[]{half, width, 0},
                new int[]{0, width, width},
                3
        );
        int outlinesize = 5;
        // Transform the triangle by the outline size to create an outline
        Polygon fill = new Polygon(
                new int[]{half, width - outlinesize, 0 + outlinesize},
                new int[]{0 + outlinesize, width - outlinesize, width - outlinesize},
                3
        );
        Polygon triangle[] = {outline,fill};
        return triangle;
    }

    /**
     * Creates a set of polygons which represent an arrow facing down.
     * The first polygon represents an outline of a down facing triangle.
     * The second polygon represents the fill of a down facing triangle.
     * @param width the desired width of the arrow.
     * @return a set of polygons which represent an arrow facing down.
     */
    private static Polygon[] getDownArrow(int width){
        int half = width / 2;
        Polygon outline = new Polygon(
                new int[]{width, half, 0},
                new int[]{0, width, 0},
                3
        );
        int outlinesize = 5;
        // Transform the triangle by the outline size to create an outline
        Polygon fill = new Polygon(
                new int[]{width - outlinesize, half, 0 + outlinesize},
                new int[]{0 + outlinesize, width - outlinesize, 0 + outlinesize},
                3
        );
        Polygon triangle[] = {outline,fill};
        return triangle;
    }

    /**
     * Creates a set of polygons which represent an arrow which is facing the specified direction.
     * The first polygon represents an outline of the arrow.
     * The second polygon represents the fill of the arrow.
     * @param width the desired width of the arrow.
     * @param direction the direction the arrow should be pointing.
     * @return a set of polygons which represent an arrow facing the specified direction.
     */
    private static Polygon[] getSelectionArrow(int width, Directions direction){
        switch (direction) {
            case UP:
                return getUpArrow(width);
            case DOWN:
                return getDownArrow(width);
            case LEFT:
                return getLeftArrow(width);
            case RIGHT:
                return getRightArrow(width);
            default:
                return null;
        }
    }

    /**
     * Draws the specified arrow to the specified screen.
     * @param screen the screen.
     * @param startX the starting x-coordinate.
     * @param startY the starting y-coordinate.
     * @param width the width of the triangle.
     * @param direction the direction the arrow should be facing.
     * @param selected whether or not the arrow is selected.
     */
    public static void drawSelectionArrow(Graphics2D screen, int startX, int startY, int width, Directions direction, boolean selected){
        Polygon triangles[] = getSelectionArrow(width, direction);
        Color tempColor = screen.getColor();
        Color outlineColor = Color.BLACK;
        Color fillColor = Color.WHITE;
        if (selected) {
            fillColor = Color.YELLOW;
        }
        // Draw the arrow outline
        screen.translate(startX, startY);
        screen.setColor(outlineColor);
        screen.fill(triangles[0]);
        // Draw the arrow fill
        screen.setColor(fillColor);
        screen.fill(triangles[1]);
        // Restore previous settings
        screen.translate((-1) * startX, (-1) * startY);
        screen.setColor(tempColor);
    }

}
