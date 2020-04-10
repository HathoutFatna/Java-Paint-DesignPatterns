import java.awt.*;
import java.util.*;
import java.util.List;


public class PaintModel
{
    private List<Observer> observers;

    public enum Tool
    {

        EraseTool,
        LineDrawTool,
        CircleDrawTool,
        RectangleDrawTool,

    };

    Color colorOptions[] = {Color.BLACK,
            Color.WHITE,
            Color.RED,
            Color.GREEN,
            Color.BLUE,
            Color.PINK};

    Color presetColor[] = {Color.BLACK, Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.PINK};

    int linethicknessOptions[] = {2, 4, 6, 8};

    Tool currentTool;
    Color currentColor;
    int currentLineThickness;

    List<Shape> drawnShapes = new ArrayList<Shape>();
    Shape selectedShape;


    private static PaintModel instance;

    private PaintModel() {
        this.observers = new ArrayList<Observer>();

        // Set initial tool, color, and line thickness.
        currentTool = Tool.LineDrawTool;
        currentColor = colorOptions[0];
        currentLineThickness = linethicknessOptions[0];
    }

    public static PaintModel getInstance() {    //singleton
        if (instance == null) {
            instance = new PaintModel();
        }
        return instance;
    }

    public void addShape(Shape shape)
    {
        this.drawnShapes.add(shape);
    }


    public void eraseShape(Shape drawnShape)
    {
        drawnShapes.remove(drawnShape);

        notifyAllObservers();
    }
    public void deselectAllShapes()
    {
        if (selectedShape != null)
        {
            selectedShape.isSelected = false;
        }

        selectedShape = null;

        notifyAllObservers();
    }

    public void changeSelectedShapeColor(Color color)
    {
        if (selectedShape != null)
        {
            selectedShape.color = color;

            notifyAllObservers();
        }
    }

    public void changeSelectedShapeThickness(int thickness)
    {
        if (selectedShape != null)
        {
            selectedShape.thickness = thickness;

            notifyAllObservers();
        }
    }


    public void addObserver(Observer observer)
    {
        this.observers.add(observer);
    }

    public void notifyAllObservers()
    {
        for (Observer observer : this.observers)
        {
            observer.update(this);
        }
    }

}
