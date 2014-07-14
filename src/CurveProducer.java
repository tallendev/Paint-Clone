package edu.wcu.cs.cs263.honors.gui.shape.producer;

/**
 * @author: Tyler Allen
 * @author: Alisha Hayman
 * @version: 03/25/2013
 *
 * This class contains logic for creating curves.
 */

/** Included so that new shapes can be given a color. */
import java.awt.Color;
/** Included so that we can receive a point from a MouseEvent. */
import java.awt.Point;
/** Included so that we can receive MouseEvents. */
import java.awt.event.MouseEvent;
/** Included so that we can create curves. */
import edu.wcu.cs.cs263.honors.gui.shape.Curve;

public class CurveProducer extends AbstractShapeProducer
{
    /** The current curve being produced by this shape producer. */
    Curve curve;
    
    /** 
     * Initializes the first curve to null, and calls the parent constructor.
     * @precondition: true
     * @postcondition: All fields are initialized.
     */
    public CurveProducer()
    {
        super();
        curve = null;
    }

    /**
     * When the mouse is pressed, we receive an alert. This method adds a 
     * point to the curve when the mouse is pressed.
     * @param: e  The MouseEvent created containing our new point.
     * @precondition: true
     * @postcondition: A new point will be added to the curve.
     */
    public void mousePressed(MouseEvent e)
    {
        super.mousePressed(e);
        this.curve = new Curve(getDrawColor());
        curve.addPoint(e.getPoint());
        notifyShapeInProgress(this.curve);
    }

    /**
     * When the mouse is released, we receive an alert. This method adds a point
     * to the curve when the mouse is released.
     * @param: e  The MouseEvent created containing our new point.
     * @precondition: true
     * @postcondition: A new point will be added to the curve
     */
    public void mouseReleased(MouseEvent e)
    {
        super.mouseReleased(e);
        curve.addPoint(e.getPoint());
        notifyShapeCreated(this.curve);
    }

    /**
     * When the mouse is dragged, we receive an alert. This method adds a point
     * to the curve when the mouse is dragged.
     * @param: e  The MouseEvent created containing our new point.
     * @precondition: true
     * @postcondition: A new point will be added to the curve.
     */
    public void mouseDragged(MouseEvent e)
    {
        super.mouseDragged(e);
        curve.addPoint(e.getPoint());
        notifyShapeInProgress(this.curve);
    }
}
