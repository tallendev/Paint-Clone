package edu.wcu.cs.cs263.honors.gui.shape.producer;

/**
 * @author: Tyler Allen
 * @author: Alisha Hayman
 * @version: 03/25/2013
 *
 * This class contains logic for creating lines.
 */

/** Imported so that a Line may maintain a color. */
import java.awt.Color;
/** Imported so that the Line may consist of two Points. */
import java.awt.Point;
/** Included so that we can receive Points from mouse events. */
import java.awt.event.MouseEvent;
/** Included so that we can draw new Lines. */
import edu.wcu.cs.cs263.honors.gui.shape.Line;

public class LineProducer extends AbstractTwoPointShapeProducer
{
    /** The current LineProducer being drawn. */
    private Line current;
    
    /** 
     * Constructs a new Line.
     * @precondition: true
     * @postcondition: All fields will be initialized.
     */
    public LineProducer()
    {
        super();
        current = null;
    }
    
    /**
     * Reads a point from a MouseEvent when the mouse button is pressed.
     * @param: e  The MouseEvent containing the new point for our 
     *            Line.
     * @precondition: true
     * @postcondition: The start point for current will be set.
     */
    public void mousePressed(MouseEvent e)
    {
        super.mousePressed(e);
        current = new Line(getDrawColor(), getStart(), getStart());
        notifyShapeInProgress(current);
    }

    /**
     * Reads a point from a MouseEvent when the mouse button is released.
     * @param: e  The MouseEvent containing the new point for our 
     *            Line.
     * @precondition: true
     * @postcondition: The end point for current will be set.
     */
    public void mouseReleased(MouseEvent e)
    {
        super.mouseReleased(e);
        current = new Line(getDrawColor(), getStart(), e.getPoint());        
        notifyShapeCreated(current);
    }

    /**
     * Reads a point from a MouseEvent when the mouse button is dragged.
     * @param: e  The MouseEvent containing the new point for our Line.
     * @precondition: true
     * @postcondition: The temporary end point for current will be set.
     */
    public void mouseDragged(MouseEvent e)
    {
        super.mouseDragged(e);
        current = new Line(getDrawColor(), getStart(), e.getPoint());
        notifyShapeInProgress(current);
    }
}
