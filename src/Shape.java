import java.awt.*;
import java.io.Serializable;
import java.awt.geom.Line2D;
import java.awt.geom.Ellipse2D;


public abstract class Shape implements Serializable, GeneralShape {

    protected final int HIT_TEST_PADDING = 5;

    enum SketchShapeType
    {
        Line,
        Circle,
        Rectangle
    }

    SketchShapeType shapeType;
    Color color;
    int thickness;
    boolean isFilled;
    boolean isSelected;
    int xTranslate = 0;
    int yTranslate = 0;

    abstract boolean isClicked(int xClick, int yClick);
    abstract boolean isBounding(int xClick, int yClick);
}

class LineShape extends Shape
{

    int x1, y1, x2, y2;

    LineShape(Color color, int thickness,
              int x1, int y1, int x2, int y2)
    {
        this.shapeType = SketchShapeType.Line;
        this.color = color;
        this.thickness = thickness;

        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
    @Override
    boolean isClicked(int xClick, int yClick)
    {
        double d2;

        d2 = Line2D.ptSegDist(x1, y1, x2, y2, xClick, yClick);

        return (d2 <= (thickness + HIT_TEST_PADDING));
    }

    @Override
    boolean isBounding(int xClick, int yCLick)
    {
        return isClicked(xClick, yCLick);
    }


    @Override
    public void paste() {

    }
}

class CircleShape extends Shape
{
    int x, y, width, height;

    CircleShape(Color color, int thickness, boolean isFilled,
                int x, int y, int width, int height)
    {
        this.shapeType = SketchShapeType.Circle;
        this.color = color;
        this.thickness = thickness;
        this.isFilled = isFilled;

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    @Override
    boolean isClicked(int xClick, int yClick)
    {
        Ellipse2D circle = new Ellipse2D.Double(x - thickness / 2 - HIT_TEST_PADDING,
                y - thickness / 2 - HIT_TEST_PADDING,
                width + thickness + 2 * HIT_TEST_PADDING,
                height + thickness + 2 * HIT_TEST_PADDING);

        if (isFilled == true)
        {
            return (circle.contains(xClick, yClick));
        } else
        {
            Ellipse2D innerCircle = new Ellipse2D.Double(x + thickness / 2 + HIT_TEST_PADDING,
                    y + thickness / 2 + HIT_TEST_PADDING,
                    width - thickness - 2 * HIT_TEST_PADDING,
                    height - thickness - 2 * HIT_TEST_PADDING);

            return (circle.contains(xClick, yClick)) && !(innerCircle.contains(xClick, yClick));
        }
    }


    @Override
    boolean isBounding(int xClick, int yClick)
    {
        Ellipse2D circle = new Ellipse2D.Double(x - thickness / 2 - HIT_TEST_PADDING,
                y - thickness / 2 - HIT_TEST_PADDING,
                width + thickness + 2 * HIT_TEST_PADDING,
                height + thickness + 2 * HIT_TEST_PADDING);

        return (circle.contains(xClick, yClick));
    }

    @Override
    public void paste() {

    }
}

class RectangleShape extends Shape
{
    int x, y, width, height;

    RectangleShape(Color color, int thickness, boolean isFilled,
                   int x, int y, int width, int height)
    {
        this.shapeType = SketchShapeType.Rectangle;
        this.color = color;
        this.thickness = thickness;
        this.isFilled = isFilled;

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }


    @Override
    boolean isClicked(int xClick, int yClick)
    {
        Rectangle rectangle = new Rectangle(x - thickness / 2 - HIT_TEST_PADDING,
                y - thickness / 2 - HIT_TEST_PADDING,
                x + thickness + 2 * HIT_TEST_PADDING,
                y + thickness + 2 * HIT_TEST_PADDING);

        if (isFilled == true)
        {
            return (rectangle.contains(xClick, yClick));
        } else
        {
            Rectangle innerRectangle = new Rectangle(x + thickness / 2 + HIT_TEST_PADDING,
                    y + thickness / 2 + HIT_TEST_PADDING,
                    x - thickness - 2 * HIT_TEST_PADDING,
                    y - thickness - 2 * HIT_TEST_PADDING);

            return (rectangle.contains(xClick, yClick)) && !(innerRectangle.contains(xClick, yClick));
        }
    }

    @Override
    boolean isBounding(int xClick, int yClick)
    {
        Rectangle rectangle = new Rectangle(x - thickness / 2 - HIT_TEST_PADDING,
                y - thickness / 2 - HIT_TEST_PADDING,
                x + thickness + 2 * HIT_TEST_PADDING,
                y + thickness + 2 * HIT_TEST_PADDING);

        return (rectangle.contains(xClick, yClick));
    }


    @Override
    public void paste() {

    }
}
