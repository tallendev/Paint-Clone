package edu.wcu.cs.cs263.honors.gui.shape;

/** 
 * @author: Tyler Allen
 * @author: Alisha Hayman
 * @version: 03/25/2013
 * 
 * Defines shape that can draw themselves.
 */

/** All shapes are serializable. */
import java.io.Serializable;
/** Shapes need Graphics to redraw themselves. */
import java.awt.Graphics;

public interface Shape extends Serializable
{
    /**
     * This method allows a Shape to redraw itself.
     * @param: g  The Graphics used to redraw the screen.
     * @precondition: true
     * @postcondition: The shape will be drawn to the screen.
     */
    public void draw(Graphics g);
}
