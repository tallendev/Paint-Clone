package edu.wcu.cs.cs263.honors.gui.shape.producer;

/**
 * @author: Tyler Allen
 * @author: Alisha Hayman
 * @version: 03/25/2013
 *
 * Abstract class the defines functionality common to all shape producers.
 * Maintains a list of ShapeCreationListener and provides methods to notify
 * these observers about the creation of new shapes. Also maintains the 
 * color with which newly produced shapes will be drawn.
 */

/** Included so we can create a list of action listeners. */
import java.util.List;
/** Included as our concrete list of action listeners. */
import java.util.LinkedList;
/** Included so that we can create a shape with a color. */
import java.awt.Color;
/** Included because this class extends the MouseAdapter abstract class. */
import java.awt.event.MouseAdapter;
/** Included so we can create shapes. */
import edu.wcu.cs.cs263.honors.gui.shape.Shape;
/** Included because this class maintaines a list of ShapeCreationListeners. */
import edu.wcu.cs.cs263.honors.gui.shape.producer.ShapeCreationListener;

public abstract class AbstractShapeProducer extends MouseAdapter 
                                             implements ShapeProducer
{
    /** The current color that we create new shapes with. */
    private Color color;
    /** The list of listerns that we maintain. */
    private List<ShapeCreationListener> listeners;

    /** 
     * Constructor initializes a list of ShapeCreatoinListeners, and sets the 
     * starting colr to null.
     * @precondition: true
     * @postcondition: listeners will be initialized to a new list.
     * @postcondition: color will be initialized to null.
     */
    public AbstractShapeProducer()
    {
        listeners = new LinkedList<ShapeCreationListener>();
        color = null;
    }

    /**
     * This method sets the current color to draw shapes in.
     * @param: drawColor  The new color to draw shapes as.
     * @precondition: true
     * @postcondition: this.color = drawColor
     */
    public void setDrawColor(Color drawColor)
    {
        this.color = drawColor;
    }

    /**
     * Getter for the drawColor.
     * @precondition: true
     * @return: color
     */
    public Color getDrawColor()
    {
        return color;
    }

    /**
     * Adds a new ShapeCreationListener to our list of listeners.
     * @param: scl  The listener to add.
     * @precondition: true
     * @postcondition: |listeners| = |#listeners| + 1
     */ 
    public void addShapeCreationListener(ShapeCreationListener scl)
    {
        listeners.add(scl);
    }

    /**
     * Removes the provided shape creation listener from the list.
     * @param: scl  The listener to be removed from the list.
     * @precondition: none
     * @postcondition: If object exists in the list, 
     *                 |listeners| = |#listeners| - 1
     * @return: true if listener existed and was removed, false otherwise.
     */
    public boolean removeShapeCreationListener(ShapeCreationListener scl)
    {
        return listeners.remove(scl);
    }

    /**
     * Alerts all listeners in our list to a new shape being created.
     * @param shape  The shape that was created.
     * @precondition: true
     * @postcondition: All listeners will be notified.
     */
    protected void notifyShapeCreated(Shape shape)
    {
        for (ShapeCreationListener listener: listeners)
        {
            listener.shapeCreated(shape);
        }
    }
    
    /**
     * Notifies all listeners that a shape is being created.
     * @param: shape  The shape being created.
     * @precondition: true
     * @postcondition: All listeners will be notified.
     */
    protected void notifyShapeInProgress(Shape shape)
    {
        for (ShapeCreationListener listener : listeners)
        {
            listener.shapeInProgress(shape);
        }
    }
}
