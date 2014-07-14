package edu.wcu.cs.cs263.honors.gui.shape.producer;

/**
 * @author: Tyler Allen
 * @author: Alisha Hayman
 * @version: 03/25/2013
 *
 * Abstract class the defines functionality common to all shape producers that
 * produce shapes with two points. Maintains a list of ShapeCreationListener and 
 * provides methods to notify these observers about the creation of new shapes. 
 * Also maintains the color with which newly produced shapes will be drawn.
 */

/** This abstract class overrides the MouseAdapter method, mousePressed.*/
import java.awt.event.MouseEvent;
/** Included so that we can receive the point pressed by the mouse. */
import java.awt.Point;

public abstract class AbstractTwoPointShapeProducer 
                      extends AbstractShapeProducer
{
    /** The current start point of two point shape. */
    private Point currentStart;
    /** 
     * Alerted when the mouse is pressed.
     * @param: e  The event that generates this function call. 
     * @precondition: true
     * @postcondition: currentStart is updated.
     */
    public void mousePressed(MouseEvent e)
    {
        super.mousePressed(e);
        currentStart = e.getPoint();
    }
    
    /**
     * Returns the start point of this producer.
     * @precondition: true
     * @return: the currentStart.
     */
    protected Point getStart()
    {
        return this.currentStart;
    }
}
