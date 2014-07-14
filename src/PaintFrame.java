package edu.wcu.cs.cs263.honors.gui;

/**
 * @author: Tyler Allen
 * @author: Alisha Hayman
 * @version: 03/25/2013
 *
 * This class represents the main window of the application. This class 
 * coordinates high-level behavior for this program.
 */

/** Imported so that we may provide Colors to our panels.*/
import java.awt.Color;
/** Imported for saving/loading files. */
import java.io.File;
/** Imported as a layout that we use for a JPanel. */
import java.awt.BorderLayout;
/** Imported so that we can modify JPanel components. */
import java.awt.Component;
/** Imported so that we can create anonymous ActionEvents. */
import java.awt.event.ActionEvent;
/** Imported so that we can create WindowAdapters. */
import java.awt.event.WindowAdapter;
/** Imported so that we can receive WindowEvents. */
import java.awt.event.WindowEvent;
/** Imported so that we can create/receive actions. */
import javax.swing.Action;
/** Imported so that we can create JButtons. */
import javax.swing.JButton;
/** Imported so that we can build Borders. */
import javax.swing.BorderFactory;
/** We because we use JPanels. */
import javax.swing.JPanel;
/** Extended because we extend JFrame. */
import javax.swing.JFrame;
/** We create JMenus. */
import javax.swing.JMenu;
/** We create a JMenuBar. */
import javax.swing.JMenuBar;
/** We create JMenuItems. */
import javax.swing.JMenuItem;
/** We create AbstractActions. */
import javax.swing.AbstractAction;
/** We use a JFileChooser. */
import javax.swing.JFileChooser;
/** We create a JOptionPane. */
import javax.swing.JOptionPane;
/** We create/receive MenuEvents. */
import javax.swing.event.MenuEvent;
/** We create MenuListeners. */
import javax.swing.event.MenuListener;
/** We own a ColorSelectionPanel. */
import edu.wcu.cs.cs263.honors.gui.color.ColorSelectionPanel;
/** We create ColorChangeListeners. */
import edu.wcu.cs.cs263.honors.gui.color.ColorChangeListener;

/** Imported for easy use of predefined colors. */
import static java.awt.Color.*;

public class PaintFrame extends JFrame
{
    /** The serialVersionUID for this program. */
    private static final long serialVersionUID = 1;
    /** The drawPanel that we create. */
    DrawPanel drawPanel;

    /**
     * Here we set up the JFrame, by initializing all the components.
     * precondition: true
     * postcondition: PaintFrame will be initialized.
     */
    public PaintFrame()
    {
        super();
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().setBackground(white);
        this.setTitle("cs263 Paint");
        this.setJMenuBar(this.makeMenuBar());

        ShapeButtonPanel shapes = new ShapeButtonPanel();
        shapes.setBorder(BorderFactory.createTitledBorder("Type"));
        this.getContentPane().add(shapes, BorderLayout.WEST);

        ColorSelectionPanel colors = new ColorSelectionPanel();
        colors.setBorder(BorderFactory.createTitledBorder("Color"));
        this.getContentPane().add(colors, BorderLayout.SOUTH);

        drawPanel = new DrawPanel(white);
        this.getContentPane().add(drawPanel, BorderLayout.CENTER);
    
        shapes.addButtonSelectionListener((ButtonSelectionListener)drawPanel);
        colors.addColorChangeListener((ColorChangeListener)drawPanel);
        
        this.setCloseOperation();
    }

    /**
     * Sets the DefaultCloseOperation on the x button in the upper right 
     * corner to create a prompt asking the user if they would like to quit.
     * @precondition: true
     * @postcondition: If the user selects yes, the program will exit.
     */
    private void setCloseOperation()
    {
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        /**
         * Anyonymous inner class for generating a window.
         */
        this.addWindowListener( new WindowAdapter()
        {
            /** 
             * If the x button is selected, we ask the user if they would like
             * to quit.
             * @precondition: true
             * @postcondition: If the user selects yes, the program will exit.
             */
            public void windowClosing(WindowEvent e)
            {
                int close = JOptionPane.showConfirmDialog(
                            PaintFrame.this, 
                            "Are you sure that you would like to quit?",
                            "Exit Program",
                            JOptionPane.YES_NO_OPTION);
                if (close == JOptionPane.YES_OPTION)
                {
                    PaintFrame.this.setDefaultCloseOperation
                                    (JFrame.EXIT_ON_CLOSE);
                }
            }
        });
    }

    /** 
     * Helper method where changes to the menu bar will go. This is to help
     * keep things organized.
     * @precondition: true
     * @return: A completed JMenubar.
     */
    private JMenuBar makeMenuBar()
    {
        JMenuBar menubar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem save = saveMaker();
        JMenuItem open = openMaker(); 
        JMenuItem exit = exitMaker();
        file.add(save);
        file.add(open);
        file.add(exit);

        JMenu edit = editMaker();

        JMenu help = helpMaker();

        menubar.add(file);
        menubar.add(edit);
        menubar.add(help);
        return menubar;
    }
    
    /**
     * Helper function to create the edit menu.
     * @precondition: true
     * @return: A completed edit button.
     */
    private JMenu editMaker()
    {
        JMenu edit = new JMenu("Edit");
        JMenuItem undo = undoMaker();
        JMenuItem redo = redoMaker();
        JMenuItem clear = clearMaker();
        edit.add(undo);
        edit.add(redo);
        edit.add(clear);
        /** Anonymous class for the edit button. */
        edit.addMenuListener(new MenuListener()
        {
            /** 
             * If the menu has been selected, updates necessary buttons.
             * @param e  The new event generated.
             * @precondition: true
             * @postcondition: The state of buttons will be updated.
             */
            public void menuSelected(MenuEvent e)
            {
                Component[] elements = ((JMenu)(e.getSource()))
                                                 .getMenuComponents();
                JMenuItem undo = (JMenuItem)elements[0];
                JMenuItem redo = (JMenuItem)elements[1];
                if (drawPanel.canUndo())
                {
                    undo.setEnabled(true);
                }
                else
                {
                    undo.setEnabled(false);
                }
                if(drawPanel.canRedo())
                {
                    redo.setEnabled(true);
                }
                else
                {
                    redo.setEnabled(false);
                }
            }
            /**
             * Unused.
             */ 
            public void menuDeselected(MenuEvent e)
            {
            }

            /**
             * Unused.
             */
            public void menuCanceled(MenuEvent e)
            {
            }
        });
        return edit;
    }
    
    /**
     * Helper for creating a clear button. Quickly creates an anonymous
     * class that contains a listener listening for the button to be pressed.
     */
    private JMenuItem clearMaker()
    {
        return new JMenuItem(new AbstractAction("Clear")
        {
            /** The serialVersionUID for this version of the program. */
            private static final long serialVersionUID = 1;
            /**
             * If this is called, we call the clear function from DrawPanel.
             * @param: e  The event generated by the button being pressed.
             * @precondition: true
             * @postcondition: The DrawPanel will be cleared.
             */
            public void actionPerformed(ActionEvent e)
            {
                drawPanel.clear();
            }
        });
    }

    /**
     * Helper for creating a undo button. Quickly creates an anonymous
     * class that contains a listener listening for the button to be pressed.
     */
    private JMenuItem undoMaker()
    {
        return new JMenuItem(new AbstractAction("Undo")
        {
            /** The serialVersionUID for this version of the program. */
            private static final long serialVersionUID = 1;
            /**
             * If this is called, we call undo if it can be called.
             * @param: e  The event generated when the Undo button is pressed.
             * @precondition: true
             * @postcondition: If it can, an undo operation will be performed.
             */
            public void actionPerformed(ActionEvent e)
            {
                if (drawPanel.canUndo())
                {
                    drawPanel.undo();
                }
            }
        });
    }
    
    /**
     * Helper for creating a redo button. Quickly creates an anonymous
     * class that contains a listener listening for the button to be pressed.
     */
    private JMenuItem redoMaker()
    {
        return new JMenuItem(new AbstractAction("Redo")
        {
            /** The serialVersionUID for this version of the program. */
            private static final long serialVersionUID = 1;
            /**
             * If this is called, we call redo if it can be called.
             * @param: e  The event generated when the Redo button is pressed.
             * @precondition: true
             * @postcondition: If it can, an redo operation will be performed.
             */
            public void actionPerformed(ActionEvent e)
            {
                if (drawPanel.canRedo())
                {
                    drawPanel.redo();
                }
            }
        });
    }

    /**
     * Helper for creating an exit button. Quickly creates an anonymous
     * class that contains a listener listening for the button to be pressed.
     */
    private JMenuItem exitMaker()
    {
        return new JMenuItem(new AbstractAction("Exit")
        {
            /** The serialVersionUID for this version of the program. */
            private static final long serialVersionUID = 1;
            /**
             * If the button is pressed, we prompt the user to quit.
             * @precondition: true
             * @postcondition: If yes is selected, the program will exit.
             */
            public void actionPerformed(ActionEvent e)
            {
                int close = JOptionPane.showConfirmDialog(
                            PaintFrame.this, 
                            "Are you sure that you would like to quit?",
                            "Exit Program",
                            JOptionPane.YES_NO_OPTION);
                if (close == JOptionPane.YES_OPTION)
                {
                    System.exit(0);
                }
            }
        });
    }

    /**
     * Helper for creating a save button. Quickly creates an anonymous
     * class that contains a listener listening for the button to be pressed.
     */
    private JMenuItem saveMaker()
    {
        return new JMenuItem(new AbstractAction("Save") 
        {
            /** The serialVersionUID for this version of the program. */
            private static final long serialVersionUID = 1;
            /**
             * Will prompt the user for what to call the file to be saved, 
             * and save if it is possible.
             * @param: e  The event generated by pressing the save button.
             * @precondition: true
             * @postcondition: If the file can be created, the current state
             *                 of the program will be saved.
             */
            public void actionPerformed(ActionEvent e)
            {
                Object temp =   JOptionPane.showInputDialog(PaintFrame.this, 
                                "Please enter file name to save as: ");
                if (temp != null)
                {
                    File file = new File(temp.toString());
                    drawPanel.save(file);
                }
            }
        });
    }

    /**
     * Helper for creating a load button. Quickly creates an anonymous
     * class that contains a listener listening for the button to be pressed.
     */
    private JMenuItem openMaker()
    {
        return new JMenuItem(new AbstractAction("Open")
        {    
            /** The serialVersionUID for this version of the program. */
            private static final long serialVersionUID = 1;
            /**
             * Will prompt the user for a file to load.
             * @param: e  The event generated by pressing the load button.
             * @precondition: true
             * @postcondition: If the file can be loaded, we will load the 
             *                 state saved in the file.
             */
            public void actionPerformed(ActionEvent e)
            {
                JFileChooser choose = new JFileChooser();
                int option = choose.showOpenDialog(null);
                if (option == JFileChooser.APPROVE_OPTION)
                {
                    File file = choose.getSelectedFile(); //call function with file
                    drawPanel.load(file);
                }
            }
        });
    }

    /**
     * Helper for creating a help button. Quickly creates an anonymous
     * class that contains a listener listening for the button to be pressed.
     */
    private JMenu helpMaker()
    {
        JMenu help = new JMenu("Help");
        help.addMenuListener(new MenuListener()
        {
            /**
             * Creates a help button.
             * param: e  The event generated by pressing the button.
             * @precondition: true
             * @postcondition: A help menu will be displayed.
             */
            public void menuSelected(MenuEvent e)
            {
                JOptionPane.showMessageDialog(PaintFrame.this, 
                "Authors: Tyler Allen, Alisha Hayman" +
                " \nVersion: 3.29.13\n\nClick colors and shapes to draw stuff" +
                "\nThe default color is white, so if you haven't\n selected a" +
                " color, you're drawing in white.");
            }
            
            public void menuDeselected(MenuEvent e){}
            public void menuCanceled(MenuEvent e){}
        });
        return help;
    }
}
