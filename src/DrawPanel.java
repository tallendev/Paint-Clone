package edu.wcu.cs.cs263.honors.gui;

/**
 * @author: Tyler allen
 * @author: Alisha Hayman
 * @version: 03/25/2013
 *
 * This class serves as the component that is notified of the creation of new
 * shapes and paints all drawn shapes. Instances of this class serve as 
 * observers in instances of the Observer design pattern. The interesting state
 * in which this object is interest is the creation of new shapes.
 *
 * Instances of this class will maintain a list of shapes that corresponds
 * to the shapes that are to be painted on this panel.
 *
 */

/** This class maintains a List of shapes. */
import java.util.List;
/** The concrete implementation of our List. */
import java.util.LinkedList;
/** This class maintains a stack of mementos. */
import java.util.Stack;
/** This class is able to read serialized objects out of a file. */
import java.io.File;
/** This file creates an output stream to output serializable objects to a file.
 */
import java.io.BufferedOutputStream;
/** This file creates an input stream to input serializable objects from a file.
 */
import java.io.BufferedInputStream;
/** This class creates a BufferedInputStream to output to a file.
 */
import java.io.FileOutputStream;
/** This creates a FileInputStream to read in data from a file.*/
import java.io.FileInputStream;
/** This file creates an ObjectInputStream to read in objects from a file. */
import java.io.ObjectInputStream;
/** This file creates an ObjectOutputStream to output objects to a file. */
import java.io.ObjectOutputStream;
/** This class may throw a FileNotFoundException in the event a file being
 * read from does not exist. */
import java.io.FileNotFoundException;
/** Included in the event we get a generic IOException. */
import java.io.IOException;
/** Included so that we can keep a color to provide new ColorProducers. */
import java.awt.Color;
/** Included so that we can pass Graphics objects to shapes that draw themselves
 * to the screen. */
import java.awt.Graphics;
/** This class extends JPanel.*/
import javax.swing.JPanel;
/** We create a JOptionPane. */
import javax.swing.JOptionPane;
/** This class managhes Shapes.*/
import edu.wcu.cs.cs263.honors.gui.shape.Shape;
/** This class initializes itself with NullShapes. */
import edu.wcu.cs.cs263.honors.gui.shape.NullShape;
/** This class creates and maintains ShapeProducers. */
import edu.wcu.cs.cs263.honors.gui.shape.producer.ShapeProducer;
/** This class creates and maintains NullShapeProducers. */
import edu.wcu.cs.cs263.honors.gui.shape.producer.NullShapeProducer;
/** This class is a ShapeCreationListener. */
import edu.wcu.cs.cs263.honors.gui.shape.producer.ShapeCreationListener;
/** This class is a ColorChangeListener. */
import edu.wcu.cs.cs263.honors.gui.color.ColorChangeListener;
/** Included so that we can easily refer to colors. */
import static java.awt.Color.*;

public class DrawPanel extends JPanel implements ShapeCreationListener, 
                                                 ColorChangeListener,
                                                 ButtonSelectionListener
{
    /** The current version UID for this class. */
    private static final long serialVersionUID = 1;
    /** The current ShapeProducer that we use. */
    private ShapeProducer producer;
    /** This current color to create ShapeProducers with. */
    private Color color;
    /** The currentShape being drawn. */
    private Shape currentShape;
    /** The list of already drawn shapes. */
    private List<Shape> shapes;
    /** The stack of mementos for this class. */
    private Stack<DrawingMemento> mementos;

    /**
     * Constructor for a DrawPanel. Initializes all fields and sets values.
     * @param: background  The background color to draw this panel.
     * @precondition: true
     * @postcondition: all fields will be initialized.
     */
    public DrawPanel(Color background)
    {
        this.setOpaque(true);
        this.setBackground(background);

        color = white;
        currentShape = new NullShape(null, null, null);
        producer = new NullShapeProducer();
        this.addMouseListener(this.producer);
        this.addMouseMotionListener(this.producer);
        
        shapes = new LinkedList<Shape>();
        mementos = new Stack<DrawingMemento>();

    }

    /**
     * Changes the color of producers created by this object.
     * @param: color  The color to change to.
     * @precondition: true
     * @postcondition: The color will be changed.
     */
    public void colorChange(Color color)
    {
        this.color = color;
        this.producer.setDrawColor(this.color);
    }

    /**
     * Changes the current ShapeProducer this frame is using to create shapes.
     * @param: producer  The new producer to use.
     * @precondition: true
     * @postcondition: the shape producer will be removed and replaced with
     *                  the new shape producer.
     */
    public void selectionChanged(ShapeProducer producer)
    {
        this.removeMouseListener(this.producer);
        this.removeMouseMotionListener(this.producer);
        this.producer.removeShapeCreationListener(this);
        
        this.producer = producer;
        this.producer.setDrawColor(this.color);
        this.producer.addShapeCreationListener(this);
        this.addMouseListener(this.producer);
        this.addMouseMotionListener(this.producer);
    }
    
    /**
     * Sets the shape producer to a new producer. Please see selectionChanged.
     * @param: newProducer  The new producer to use.
     */
    public void setShapeProducer(ShapeProducer newProducer)
    {
        selectionChanged(newProducer);
    }
    
    /**
     * Changes the drawColor. 
     * @param: drawColor the new drawColor.
     * @precondition: true
     * @postcondition: this.drawColor = drawColor
     */
    public void setDrawColor(Color drawcolor)
    {
        colorChange(drawcolor);
    }
    
    /**
     * Called when a new shape has been created.
     * @param: shape  The shape that has been created. 
     * @precondition: true
     * @postcondition: The new shape is added to the list.
     * @postcondition: The current shape is set to a NullShape.
     * @postcondition: The screen will be redrawn.
     */
    public void shapeCreated(Shape shape)
    {
        shapes.add(shape);
        currentShape = new NullShape(null, null, null);
        clearRedo();
        mementos.push(new DrawingMemento(new LinkedList<Shape>(shapes)));
        repaint();
    }

    /**
     * Updates the currentShape with the new state of the shape in progress.
     * @param: shape  The shape being drawn.
     * @precondition: true
     * @postcondition: currentShape = shape
     * @postcondition: The screen will be redrawn.
     */
    public void shapeInProgress(Shape shape)
    {
        this.currentShape = shape;
        repaint();
    }
    
    /**
     * Saves the current state of the paint frame to a file. Does not save
     * the memento state.
     * @param: file  The file to save to.
     * @precondition: Must be able to create or edit file.
     * @postcondition: Current state will be saved to file.
     */
    public void save(File file)
    {
        ObjectOutputStream stream = null;
        try 
        {
             stream = new ObjectOutputStream(
                      new BufferedOutputStream(
                      new FileOutputStream(file)));
        }
        catch (FileNotFoundException e)
        {
            System.out.println(e.getMessage());
            return;
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
            return;
        }
        try
        {
            stream.writeObject(shapes);
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
            return;
        }
        try
        {
            stream.close();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
   
    /** Warning supressed because it is unavoidable with this implementation. */
    @SuppressWarnings("unchecked")
    /**
     * Loads a given file. 
     * @param: file  The file to load.
     * @precondition: File must existed.
     * @postcondition: The file will be loaded.
     */
    public void load(File file)
    {
        ObjectInputStream stream = null;
        try {
            stream = new ObjectInputStream(
                     new BufferedInputStream(
                     new FileInputStream(file)));
        }
        catch (FileNotFoundException e)
        {
            JOptionPane.showMessageDialog(this, "Failed to load file...",
                                                "File Error", 
                                                JOptionPane.WARNING_MESSAGE);
            return; 
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog(this, "Failed to load file...",
                                                "File Error", 
                                                JOptionPane.WARNING_MESSAGE);
            return;
        }
        try 
        {
            Object obj = stream.readObject();
            if (obj instanceof List)
            {
                shapes = (List<Shape>)obj;
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Failed to load file...",
                                              "File Error", 
                                              JOptionPane.WARNING_MESSAGE);
                return;
            }
            stream.close();
        }
        catch (ClassNotFoundException e)
        {
            JOptionPane.showMessageDialog(this, "Failed to load file...",
                                          "File Error", 
                                          JOptionPane.WARNING_MESSAGE);
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog(this, "Failed to load file...",
                                          "File Error", 
                                          JOptionPane.WARNING_MESSAGE);
            return;
        }
        repaint();
    }
    
    /**
     * Function to clear the redo state.
     * @precondition: true
     * @postcondition: |mementos| = 0
     */
    private void clearRedo()
    {
        for (int i = 0; i < mementos.size() - shapes.size(); i++)
        {
            mementos.pop();
        }
    }

    /**
     * Clears the screen. 
     * @precondition: true
     * @postcondition: screen will be cleared.
     * @postcondition: |shapes| = 0
     */
    public void clear()
    {
        if (shapes.size() != 0)
        {

            int close = JOptionPane.showConfirmDialog(
                        DrawPanel.this, 
                        "Are you sure that you want to clear? This can not" +
                        " be undone.",
                        "Clear Screen",
                        JOptionPane.YES_NO_OPTION);
            if (close == JOptionPane.YES_OPTION)
            {
                shapes = new LinkedList<Shape>();
                mementos= new Stack<DrawingMemento>();
            }
            repaint();
        }
    }
    
    /**
     * Determines if we can undo.
     * @precondition: true
     * @return: True if we can undo, false otherwise.
     */
    public boolean canUndo()
    {
        return shapes.size() != 0;
    }

    /**
     * Determines if we can redo.
     * @precondition: true
     * @return True if we can redo, false otherwise.
     */
    public boolean canRedo()
    {
        return mementos.size() > shapes.size();
    }

    /**
     * Adds a memento to the stack of mementos, removes a shape from the list of
     * shapes, redraws the screen.
     * @precondition: canUndo must be true.
     * @postcondition: |mementos| = |#mementos| + 1
     * @postcondition: |shapes| = |#shapes| - 1
     */
    public void undo()
    {
        mementos.push(new DrawingMemento(new LinkedList<Shape>(shapes)));
        pop();
        repaint();
    }

    /**
     * Undos what the previous undo call did.
     * @precondition: canRedo must be true.
     * @postcondition: |mementos| = |#mementos| - 1
     * @postcondition: |shapes| = |#shapes| + 1
     */
    public void redo()
    {
        this.shapes = mementos.pop().getState();
        repaint();
    }

    /** 
     * Pops the last element off the List, quick helper.
     * @precondition: |#shapes| > 0
     * @postcondition: |shapes| = |#shapes| - 1
     */
    private void pop()
    {
        shapes.remove(shapes.size() - 1);
    }

    /**
     * Draws each shape in the shapes list.
     * @param: g  The Graphics object we use to redraw the screen.
     * @precondition: true
     * @postcondition: The screen will be redrawn.
     */
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        for (Shape shape : shapes)
        {
            shape.draw(g);
        }
        if (!(currentShape instanceof NullShape))
        {
            currentShape.draw(g);
        }
    }

    /**
     * This is the DrawingMemento class that we use for storing the state 
     * of DrawingPanel.
     */
    private class DrawingMemento
    {
        /** This is the list of shapes from the DrawingPanel class. */
        List<Shape> state;
        
        /** Creates a new DrawingMemento.
         * @param: state  The state of the DrawingPanel that we are storing.
         * @precondition: true
         * @postcondition: All fields will be assigned.
         */
        protected DrawingMemento(List<Shape> state)
        {
            this.state = state;
        }
        
        /** 
         * Returns the state stored in this memento.
         * @precondition: true
         * @return: Returns the state stored by this memento.
         */
        protected List<Shape> getState()
        {
            return state;
        }
    }
}
