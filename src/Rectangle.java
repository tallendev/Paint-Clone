package edu.wcu.cs.cs263.honors.gui.shape;

/**
 * @author: Tyler Allen
 * @author: Alisha Hayman
 * @version: 03/25/2013
 *
 * Contains code necessary to draw a rectangle between two points with a 
 * specified drawing color.
 */

/** Imported so that a Rectangle may maintain a color. */
import java.awt.Color;
/** Imported so that we can redraw the screen using a Graphics. */
import java.awt.Point;
/** Imported so that the Rectangle may consist of two Points. */
import java.awt.Graphics;

public class Rectangle extends AbstractTwoPointShape
{
    /** The current UID of this program. */
    private static final long serialVersionUID = 1;

    /**
     * Creates a Rectangle by calling the parent constructors.
     * @param: color  The color of this Rectangle.
     * @param start  The starting point of this Rectangle.
     * @param end  The end point of this Rectangle.
     */
    public Rectangle(Color color, Point start, Point end)
    {
        super(color, start, end);
    }

    /**
     * Contains logic for drawing this shape.
     * @param: g  The Graphics that we use to draw this Rectangle.
     * @precondition: true
     * @postcondition: This Rectangle will be drawn to the screen.
     */
    public void draw(Graphics g)
    {
        g.setColor(getColor());
        Point tempStart = getStart();
        Point tempEnd = getEnd();
        int x = (int)Math.min(tempStart.getX(), tempEnd.getX());
        int y = (int)Math.min(tempStart.getY(), tempEnd.getY());
        int width = (int)Math.abs(tempStart.getX() - tempEnd.getX());
        int height = (int)Math.abs(tempStart.getY() - tempEnd.getY());
        g.drawRect(x, y, width, height);
    }
}
