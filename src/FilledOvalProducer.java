package edu.wcu.cs.cs263.honors.gui.shape.producer;

/**
 * @author: Tyler Allen
 * @author: Alisha Hayman
 * @version: 03/25/2013
 *
 * This class contains logic for creating filled ovals.
 */

/** Included so that we can specify a Color to create Ovals. */
import java.awt.Color;
/** Included so that we can receive Points from mouse events. */
import java.awt.Point;
/** Included so that we can read from MouseEvents. */
import java.awt.event.MouseEvent;
/** Included so that we can draw new FilledOvals.  */
import edu.wcu.cs.cs263.honors.gui.shape.FilledOval;

public class FilledOvalProducer extends AbstractTwoPointShapeProducer
{
    /** The current FilledOval being drawn. */
    FilledOval current;

    /** 
     * Constructs a new FilledOval.
     * @precondition: true
     * @postcondition: All fields will be initialized.
     */
    public FilledOvalProducer()
    {
        super();
        current = null;
    }

    /**
     * Reads a point from a MouseEvent when the mouse button is pressed.
     * @param: e  The MouseEvent containing the new point for our Oval.
     * @precondition: true
     * @postcondition: The start point for current will be set.
     */
    public void mousePressed(MouseEvent e)
    {
        super.mousePressed(e);
        current = new FilledOval(getDrawColor(), e.getPoint(), e.getPoint());
        notifyShapeInProgress(current);
    }

    /**
     * Reads a point from a MouseEvent when the mouse button is released.
     * @param: e  The MouseEvent containing the new point for our Oval.
     * @precondition: true
     * @postcondition: The end point for current will be set.
     */
    public void mouseReleased(MouseEvent e)
    {
        super.mouseReleased(e);
        current  = new FilledOval(getDrawColor(), getStart(), e.getPoint());   
        notifyShapeCreated(current);
    }

    /**
     * Reads a point from a MouseEvent when the mouse button is dragged.
     * @param: e  The MouseEvent containing the new point for our Oval.
     * @precondition: true
     * @postcondition: The temporary end point for current will be set.
     */
    public void mouseDragged(MouseEvent e)
    {
        super.mouseDragged(e);
        current  = new FilledOval(getDrawColor(), getStart(), e.getPoint());   
        notifyShapeInProgress(current);
    }
}
