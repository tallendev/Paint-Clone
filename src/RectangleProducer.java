package edu.wcu.cs.cs263.honors.gui.shape.producer;

/**
 * @author: Tyler Allen
 * @author: Alisha Hayman
 * @version: 03/25/2013
 *
 * This class contains logic for creating rectangles.
 */

/** Included so that we can specify a Color to create Rectangles */
import java.awt.Color;
/** Included so that we can receive Rectangles from mouse events. */
import java.awt.Point;
/** Included so that we can receive Rectangles from mouse events. */
import java.awt.event.MouseEvent;
/** Included so that we can draw new Rectangles. */
import edu.wcu.cs.cs263.honors.gui.shape.Rectangle;

public class RectangleProducer extends AbstractTwoPointShapeProducer
{
    /** The current Rectangle being drawn. */
    private Rectangle current;
    
    /** 
     * Constructs a new Rectangle.
     * @precondition: true
     * @postcondition: All fields will be initialized.
     */
    public RectangleProducer()
    {
        super();
        current = null;
    }

    /**
     * Reads a point from a MouseEvent when the mouse button is pressed.
     * @param: e  The MouseEvent containing the new point for our 
     *            Rectangle.
     * @precondition: true
     * @postcondition: The start point for current will be set.
     */
    public void mousePressed(MouseEvent e)
    {
        super.mousePressed(e);
        current = new Rectangle(getDrawColor(), e.getPoint(), e.getPoint());
        notifyShapeInProgress(current);
    }

    /**
     * Reads a point from a MouseEvent when the mouse button is released.
     * @param: e  The MouseEvent containing the new point for our 
     *            FilledRectangle.
     * @precondition: true
     * @postcondition: The end point for current will be set.
     */
    public void mouseReleased(MouseEvent e)
    {
        super.mouseReleased(e);
        current  = new Rectangle(getDrawColor(), getStart(), e.getPoint());   
        notifyShapeCreated(current);
    }

    /**
     * Reads a point from a MouseEvent when the mouse button is dragged.
     * @param: e  The MouseEvent containing the new point for our 
     *            FilledRectangle.
     * @precondition: true
     * @postcondition: The temporary end point for current will be set.
     */
    public void mouseDragged(MouseEvent e)
    {
        super.mouseDragged(e);
        current  = new Rectangle(getDrawColor(), getStart(), e.getPoint());   
        notifyShapeInProgress(current);
    }
}
