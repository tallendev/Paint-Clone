package edu.wcu.cs.cs263.honors;

/**
 * @author: Tyler Allen
 * @author: Alisha Hayman
 * @version: 03/25/2013
 *
 * The class containing the main method for this implementation of a paint 
 * clone. 
 */

/** We need to be able to make the main PaintFrame. */
import edu.wcu.cs.cs263.honors.gui.PaintFrame;

public class MainDriver
{
    /** Simply creates a new frame and makes it visible. */
    public static void main(String[] args)
    {
        PaintFrame frame1 = new PaintFrame();
        frame1.setSize(1000, 1000);
        frame1.setVisible(true);
    }
}

