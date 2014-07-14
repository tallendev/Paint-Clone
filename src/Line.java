package edu.wcu.cs.cs263.honors.gui.shape;

/**
 * @author: Tyler Allen
 * @author: Alisha Hayman
 * @version: 03/25/2013
 *
 * Provides code necessary to draw a line between two points with a specified
 * color.
 */

/** Imported so that a Rectangle may maintain a color. */
import java.awt.Color;
/** Imported so that the Rectangle may consist of two Points. */
import java.awt.Graphics;
/** Imported so that we can redraw the screen using a Graphics. */
import java.awt.Point;

public class Line extends AbstractTwoPointShape
{
    /** The current UID of this program. */
    private static final long serialVersionUID = 1;

    /**
     * Creates a Line by calling the parent constructors.
     * @param: color  The color of this Line.
     * @param start  The starting point of this Line.
     * @param end  The end point of this Line.
     */
    public Line(Color color, Point start, Point end)
    {
       super (color, start, end);
    }

    /**
     * Contains logic for drawing this shape.
     * @param: g  The Graphics that we use to draw this Line.
     * @precondition: true
     * @postcondition: This Line will be drawn to the screen.
     */
    public void draw(Graphics g)
    {
        g.setColor(getColor());
        Point tempStart = getStart();
        Point tempEnd = getEnd();
        g.drawLine((int)tempStart.getX(), (int)tempStart.getY(), 
                   (int)tempEnd.getX(), (int)tempEnd.getY());
    }
}
