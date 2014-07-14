package edu.wcu.cs.cs263.honors.gui.color;

/**
 * @Author: Tyler Allen
 * @Author: Alisha Hayman
 * @version: 03/27/2013
 *
 * This class serves as the container and coordinator of the visual components
 * used to select the color with which users wish to draw. 
 */

/** Included so that we can keep a list of ColorChangeListener. */
import java.util.List;
/** Our concrete list used to store ColorChangeListeners. */
import java.util.LinkedList;

/** Keeps track of the currently selected color. */ 
import java.awt.Color;
/** The lowout that we use for this JPanel. */
import java.awt.FlowLayout;
/** Included to set the size of the preview window. */
import java.awt.Dimension;

/** This class extends JPanel. */
import javax.swing.JPanel;
/** This Jpanel includes a JColorChooser. */
import javax.swing.JColorChooser;
/** This class creates ChangeListeners that pull their color from 
 * DefaultColorSelectionModels. */
import javax.swing.colorchooser.DefaultColorSelectionModel;
/** We include this so that we can remove parts we don't want of the 
 * JColorChooser. */
import javax.swing.colorchooser.AbstractColorChooserPanel;

/** This class creates ChangeListeners. */
import javax.swing.event.ChangeListener;
/** When the color changes, a ChangeEvent is generated. */
import javax.swing.event.ChangeEvent;

/** Included for easy reference of Color static fields. */
import static java.awt.Color.*;

public class ColorSelectionPanel extends JPanel
{
    /** The current version UID. */
    private static final long serialVersionUID = 1;
    /** The preview panel that we create. */
    private JPanel preview;
    /** The JColorChooser that appears on this JPanel. */
    private JColorChooser choose;
    /** The list of listeners that keep track of color changes. */
    private List<ColorChangeListener> list;

    /**
     * Constructor that builds a new ColorSelectionPanel.
     * @precondition: true
     * @postcondition: All fields will be initialized. 
     * @postcondition: The JColorChooser will be stripped of excess panels.
     */
    public ColorSelectionPanel()
    {
        list = new LinkedList<ColorChangeListener>();
        this.setLayout(new FlowLayout());
        choose = new JColorChooser();
        choose.setPreviewPanel(new JPanel()); //clears preview box

        //removes the extra colorchooser tabs we really don't want....
        //print left on for future possible debugging purposes
        AbstractColorChooserPanel[] junkPanels = choose.getChooserPanels();
        for (int i = 0; i < junkPanels.length; i++)
        {
            String name = junkPanels[i].getClass().getName();
            if (name.equals("javax.swing.colorchooser.ColorChooserPanel"))
            {
                choose.removeChooserPanel(junkPanels[i]);
            }
        }
        preview = new JPanel();
        preview.setPreferredSize(new Dimension(100, 100));
        preview.setBackground(white);//default is white

        //create listener so that our current color box will update.
        choose.getSelectionModel().addChangeListener(new ChangeListener()
        {
            public void stateChanged(ChangeEvent e)
            {
                Color temp = ((DefaultColorSelectionModel)e.getSource())
                                                           .getSelectedColor();
                preview.setBackground(temp);
                notifyColorChange(temp);
            }
            
        });
        this.add(preview);
        this.add(choose);
    }

    /**
     * This method notifies all ColorChangeListeners of the new color.
     * @param: color  The new color selected.
     * @precondition: true
     * @postcondition: All ColorChangeListeners will be notified.
     */
    protected void notifyColorChange(Color color)
    {
        for (ColorChangeListener listener : list)
        {
            listener.colorChange(color);
        }
    }

    /**
     * Adds a change listener to our list of listeners.
     * @param: ccl  The listener to add.
     * @precondition: true
     * @postcondition: |listeners| = |#listeners| + 1
     */
    public void addColorChangeListener(ColorChangeListener ccl)
    {
        list.add(ccl);
    }

    /**
     * Removes the received ColorChangeListener from the list of listeners 
     * if it exists in the list of listeners.
     *
     * @param: ccl  The listener to remove..
     * @precondition: true
     * @postcondition: If the listener exists, |listeners| = |#listeners| - 1
     * @return: True if ccl exists in list, false otherwise.
     */
    public boolean removeColorChangeListener(ColorChangeListener ccl)
    {
        return list.remove(ccl);
    }

    /**
     * Returns the currently selected color.
     * @precondition: true
     * @return: color  The currently selected color. 
     */
    public Color getSelectedColor()
    {
        return choose.getColor();
    }
}
