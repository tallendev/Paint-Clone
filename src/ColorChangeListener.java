package edu.wcu.cs.cs263.honors.gui.color;

/**
 * @author: Tyler Allen
 * @author: Alisha Hayman
 * @version: 03/25/2013
 *
 * Interface to oberserver objects that receive notifications about changes in
 * the currently selected color in ColorSelectionPanel.
 */

import java.awt.Color;

public interface ColorChangeListener
{
    /** 
     * Method called when a listener needs to be notified of a color change.
     */
    public void colorChange(Color color);
}
