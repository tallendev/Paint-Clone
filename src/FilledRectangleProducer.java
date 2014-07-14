package edu.wcu.cs.cs263.honors.gui.shape.producer;

/**
 * @author: Tyler Allen
 * @author: Alisha Hayman
 * @version: 03/25/2013
 *
 * This class contains logic for creating filled rectangles.
 */

/** Included so that we can specify a Color to create Rectangles */
import java.awt.Color;
/** Included so that we can receive FilledRectangles from mouse events. */
import java.awt.Point;
/** Included so that we can receive FilledReectangles from mouse events. */
import java.awt.event.MouseEvent;
/** Included so that we can draw new FilledRectangles. */
import edu.wcu.cs.cs263.honors.gui.shape.FilledRectangle;

public class FilledRectangleProducer extends AbstractTwoPointShapeProducer
{
    /** The current FilledRectangle being drawn. */
    private FilledRectangle current;

    /** 
     * Constructs a new FilledRectangle.
     * @precondition: true
     * @postcondition: All fields will be initialized.
     */
    public FilledRectangleProducer()
    {
        super();
        current = null;
    }

    /**
     * Reads a point from a MouseEvent when the mouse button is pressed.
     * @param: e  The MouseEvent containing the new point for our 
     *            FilledRectangle.
     * @precondition: true
     * @postcondition: The start point for current will be set.
     */
    public void mousePressed(MouseEvent e)
    {
        super.mousePressed(e);
        current = new FilledRectangle(getDrawColor(), e.getPoint(), 
                                                      e.getPoint());
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
        current  = new FilledRectangle(getDrawColor(), getStart(), 
                                                       e.getPoint());   
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
        current  = new FilledRectangle(getDrawColor(), getStart(), 
                                                       e.getPoint());   
        notifyShapeInProgress(current);
    }
}
