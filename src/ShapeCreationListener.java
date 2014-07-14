package edu.wcu.cs.cs263.honors.gui.shape.producer;

/**
 * @author: Tyler Allen
 * @author: Alisha Hayman
 * @version: 03/25/2013
 *
 * Interface to objects that can create shapes.
 */
import edu.wcu.cs.cs263.honors.gui.shape.Shape;

public interface ShapeCreationListener
{
    /** 
     * Called when a shape is created.
     * @param: shape  The shape that was created.
     * @precondition: true
     */
    public void shapeCreated(Shape shape);

    /**
     * Called when a shape is in progress.
     * @param: shape The shape that is in progress.
     * @precondition: true
     */
    public void shapeInProgress(Shape shape);
}
