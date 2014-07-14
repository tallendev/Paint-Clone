package edu.wcu.cs.cs263.honors.gui;

/**
 * @author: Tyler Allen
 * @author: Alisha Hayman
 * @version: 03/25/2013
 * 
 * Interface to observer objects that receive notification about changes in 
 * button selection in ShapeButtonPanel
 */

import edu.wcu.cs.cs263.honors.gui.shape.producer.ShapeProducer;

public interface ButtonSelectionListener
{
    /** 
     * Method called when listener is alerted that a selection is changed.
     */
    public void selectionChanged(ShapeProducer sproducer);
}
