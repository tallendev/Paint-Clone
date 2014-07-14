package edu.wcu.cs.cs263.honors.gui.shape;

/**
 * @author: Tyler Allen
 * @author: Alisha Hayman
 * @version: 03/25/2013
 *
 * Contains code necessary to draw a continuous curve between two points with a 
 * specified color.
 */

/** Our Curve maintains a list of points that make up the curve. */
import java.util.List;
/** Our concrete implementation of a list for our points. */
import java.util.LinkedList;
/** Our shape maintains a color. */
import java.awt.Color;
/** We receives a graphic object that allows us to redraw the screen. */
import java.awt.Graphics;
/** Our curve is made up of points.*/
import java.awt.Point;

public class Curve extends AbstractShape
{
    /** The current UID version of our shapes. */
    private static final long serialVersionUID = 1;
    /** The list of points that make up this curve.*/
    List<Point> points;
    
    /** Constructor for making a new curve.
     * @param: color  The color of this curve.
     * @precondition: true
     * @postcondition: All fields will be initialized.
     */
    public Curve(Color color)
    {
        super(color);
        points = new LinkedList<Point>();
    }

    /**
     * Adds a new point to this curve.
     * @param: point  The point to add.
     * @precondition: true
     * @postcondition: |points| = |#points| + 1
     */
    public void addPoint(Point point)
    {
        points.add(point);
    }

    /**
     * Draws this shape on the screen.
     * @param: g  The Graphics that allows us to redraw the screen.
     * @precondition: true
     * @postcondition: Curve will be drawn to the screen.
     */
    public void draw(Graphics g)
    {
        g.setColor(getColor());
        for (int i = 0; i < points.size() - 1; i++)
        {
            Point point1 = points.get(i);
            Point point2 = points.get(i+1);
            g.drawLine((int)point1.getX(), (int)point1.getY(),
                       (int)point2.getX(), (int)point2.getY());
        }
    }
}
