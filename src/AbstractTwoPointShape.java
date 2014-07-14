package edu.wcu.cs.cs263.honors.gui.shape;

/**
 * @author: Tyler Allen
 * @author: Alisha Hayman
 * @version: 03/25/2013
 *
 * Includes shape functionality common to shapes that can be produced with 
 * two points.
 */

/** Included so that we can maintain a start and end point. */
import java.awt.Point;
/** Included so that we can maintain a color in our ancestor. */
import java.awt.Color;

public abstract class AbstractTwoPointShape extends AbstractShape 
                                            implements Shape
{
    /** Keeps track of the start of a two point shape.*/
    Point start;
    /** Keeps track of the end of a two point shape. */
    Point end; 

    /** 
     * Calls our parent and then initializes our fields.
     * @param: color  The color of this shape.
     * @param: start  Initializes the start point.
     * @param: end  Initializes the end point.
     * @precondition: true
     * @postcondition: All fields will be initialized.
     */
    public AbstractTwoPointShape(Color color, Point start, Point end)
    {
        super(color);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the starting point of this shape.
     * @precondition: true
     * @return start The starting point.
     */
    protected Point getStart()
    {
        return start;
    }

    /**
     * Returns the end point of this shape.
     * @precondition: true
     * @return end  The end point.
     */
    protected Point getEnd()
    {
        return end;
    }
}
