package edu.wcu.cs.cs263.honors.gui.shape;

/**
 * @author: Tyler Allen
 * @author: Alisha Hayman
 * @version: 03/25/2013
 *
 * This abstract class includes shape functionality common to all shapes.
 */

/** Imported so that we can change the color that shapes are to be drawn. */
import java.awt.Color;
/** Imported so that we may create receives graphics in order to redraw
 * the screen. */
import java.awt.Graphics;

public abstract class AbstractShape implements Shape
{
    /** The color that this shape is to be drawn in. */
    private Color color;

    /**
     * Constructor that sets the color owned by this abstract class.
     * @param: color  The color of this shape.
     * @precondition: true
     * @postcondition: color will be set.
     */
    public AbstractShape(Color color)
    {
        this.color = color;
    }

    /**
     * This function returns the color of this shape.
     * @precondition: true
     * @return: color
     */
    protected Color getColor()
    {
        return color;
    }

    /**
     * This should include instructions on how to draw the shape.
     * This must be overriden.
     * @param: Graphics  The graphic used to redraw the shape.
     */
    public abstract void draw(Graphics g);
}
