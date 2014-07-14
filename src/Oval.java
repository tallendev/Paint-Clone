package edu.wcu.cs.cs263.honors.gui.shape;

/**
 * @author: Tyler Allen
 * @author: Alisha Hayman
 * @version: 03/25/2013
 *
 * Contains code necessary to draw an oval between two points with a 
 * specified color.
 */

/** Imported so that an Oval may maintain a color. */
import java.awt.Color;
/** Imported so that we can redraw the screen using a Graphics. */
import java.awt.Graphics;
/** Imported so that the Oval may consist of two Points. */
import java.awt.Point;

public class Oval extends AbstractTwoPointShape
{
    /** The current UID of this program. */
    private static final long serialVersionUID = 1;

    /**
     * Creates a Oval by calling the parent constructors.
     * @param: color  The color of this Oval.
     * @param start  The starting point of this Oval.
     * @param end  The end point of this Oval.
     */
    public Oval(Color color, Point start, Point end)
    {
        super (color, start, end);
    }

    /**
     * Contains logic for drawing this shape.
     * @param: g  The Graphics that we use to draw this Oval.
     * @precondition: true
     * @postcondition: This Oval will be drawn to the screen.
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
        g.drawOval(x, y, width, height);

    }

}
