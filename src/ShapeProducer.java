package edu.wcu.cs.cs263.honors.gui.shape.producer;

/**
 * @author: Tyler Allen
 * @author: Alisha Hayman
 * @version: 03/25/2013
 *
 * Interface to objects encapsulating logic necessary to produce shapes in 
 * response to mouse events. 
 */

/** Used in the setDrawColor method. */
import java.awt.Color;
/** This class extends MouseListener, MouseMotionListener. */
import java.awt.event.MouseMotionListener;
/** This class extends MouseListener, MouseMotionListener. */
import java.awt.event.MouseListener;

public interface ShapeProducer extends MouseListener, MouseMotionListener
{
    /** 
     * This method adds a shapeCreationListener to the list of CreationListeners
     * maintained by classes implementing this interface.
     * @param: one  The ShapeCreationListener to add to the list.
     * @precondition: true
     * @postcondition: |list| = |#list| + 1
     */
    public void addShapeCreationListener(ShapeCreationListener one);
    /**
     * This method removes a shapeCreationListener to the list of 
     * CreationListeners by classes implementing this interface.
     * @param: one The shape listener to remove.
     * @return: True if listener was removed, false otherwise.
     * @precondition: true
     * @postcondition: |list| = |#list| - 1
     */
    public boolean removeShapeCreationListener(ShapeCreationListener one);
    /**
     * Sets the new color to draw shapes produced by a ShapeProducer.
     * @param: drawColor the new color to set the drawColor to.
     * @precondition: true
     */
    public void setDrawColor(Color drawColor);
}
