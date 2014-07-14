package edu.wcu.cs.cs263.honors.gui.shape;

/**
 * @author: Tyler Allen
 * @author: Alisha Hayman
 * @version: 03/25/2013
 *
 * Shape object used for implementing the Null Object pattern.
 */

/** Included so that we can meet the requirements of our interface.*/
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import edu.wcu.cs.cs263.honors.gui.shape.AbstractTwoPointShape;

public class NullShape extends AbstractTwoPointShape
{
    /** The serialVersionUID for this program.*/
    private static final long serialVersionUID = 1;  

    /**
     * Creates a NullShape.
     * @param: color, start, end  Unused.
     */
    public NullShape(Color color, Point start, Point end)
    {
        super(color, start, end);
    }

    /**
     * Overrides draw.
     * @param: g  Unused.
     */
    public void draw(Graphics g)
    {
    }
}
