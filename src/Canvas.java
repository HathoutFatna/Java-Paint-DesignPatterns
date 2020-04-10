import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Canvas extends JComponent implements Observer
{
    Color backgroundColor;
    Point M = new Point();
    Point C = new Point();

    boolean nowDrawing = false;

    private PaintModel model = PaintModel.getInstance();

    public Canvas(PaintModel model, Color backgroundColor)
    {
        super();
        this.model = model;
        model.addObserver(this);
        this.backgroundColor = backgroundColor;
        this.setBackground(Color.WHITE);


        this.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                C.x = e.getX();
                C.y = e.getY();

               if (model.currentTool == PaintModel.Tool.EraseTool)
                {
                    for (int i = model.drawnShapes.size() - 1; i >= 0; i--)
                    {
                        Shape drawnShape = model.drawnShapes.get(i);

                        if (drawnShape.isBounding(C.x, C.y))
                        {
                            model.deselectAllShapes();
                            model.eraseShape(drawnShape);

                            break;
                        }
                    }
                } else
                {
                    nowDrawing = true;
                }
            }
        });


        this.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseReleased(MouseEvent e)
            {
                M.x = e.getX();
                M.y = e.getY();

                nowDrawing = false;

                Shape newShape;

                switch(model.currentTool)
                {

                    case LineDrawTool:
                    {
                        newShape = new LineShape(model.currentColor, model.currentLineThickness,
                                C.x, C.y, M.x, M.y);
                        model.addShape(newShape);

                        break;
                    }

                    case CircleDrawTool:
                    {
                        newShape = new CircleShape(model.currentColor, model.currentLineThickness, false,
                                Math.min(C.x, M.x), Math.min(C.y, M.y),
                                Math.abs(M.x - C.x), Math.abs(M.y - C.y));

                        model.addShape(newShape);

                        break;
                    }

                    case RectangleDrawTool:
                    {
                        newShape  = new RectangleShape(model.currentColor, model.currentLineThickness, false,
                                Math.min(C.x, M.x), Math.min(C.y, M.y),
                                Math.abs(M.x - C.x), Math.abs(M.y - C.y));

                        model.addShape(newShape);

                        break;
                    }
                    default:
                    {
                        break;
                    }
                }
                model.notifyAllObservers();
            }
        });

    }

    void paintSketchShape(Shape shape, Graphics2D g2)
    {
        g2.setColor(shape.color);
        g2.setStroke(new BasicStroke(shape.thickness));
        switch(shape.shapeType)
        {
            case Line:
            {
                paintLineSketchShape((LineShape) shape, g2);
                break;
            }
            case Circle:
            {
                paintCircleSketchShape((CircleShape) shape, g2);
                break;
            }
            case Rectangle:
            {
                paintRectangleSketchShape((RectangleShape) shape, g2);
                break;
            }
        }
    }


    void paintLineSketchShape(LineShape line, Graphics2D g2)
    {
        g2.translate(line.xTranslate, line.yTranslate);

        g2.drawLine(line.x1, line.y1, line.x2, line.y2);
    }


    void paintCircleSketchShape(CircleShape circle, Graphics2D g2)
    {
        g2.translate(circle.xTranslate, circle.yTranslate);

        if (circle.isFilled)
        {
            g2.fillOval(circle.x, circle.y, circle.width, circle.height);
        } else
        {
            g2.drawOval(circle.x, circle.y, circle.width, circle.height);
        }
    }


    void paintRectangleSketchShape(RectangleShape rectangle, Graphics2D g2)
    {
        g2.translate(rectangle.xTranslate, rectangle.yTranslate);

        if (rectangle.isFilled)
        {
            g2.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        } else
        {
            g2.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        }
    }


    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        setOpaque(true);
        g2.setColor(Color.WHITE);
        g2.setBackground(Color.WHITE);
        g2.fillRect(0, 0, this.getWidth(), this.getHeight());

        for (Shape sketchShape : model.drawnShapes)
        {
            paintSketchShape(sketchShape, g2);
        }

        g2.setColor(model.currentColor);
        g2.setStroke(new BasicStroke(model.currentLineThickness));

        if(nowDrawing == true) {
            switch (model.currentTool) {
                case LineDrawTool:
                    g2.drawLine(C.x, C.y, M.x, M.y);
                    break;

                case CircleDrawTool:
                    g2.drawOval(Math.min(C.x, M.x), Math.min(C.y, M.y),
                            Math.abs(M.x - C.x), Math.abs(M.y - C.y));
                    break;

                case RectangleDrawTool:
                    g2.drawRect(Math.min(C.x, M.x), Math.min(C.y, M.y),
                            Math.abs(M.x - C.x), Math.abs(M.y - C.y));
                    break;

                default:
                    break;
            }
        }
    }

    @Override
    public void update(Object observable)
    {
        repaint();
    }
}



