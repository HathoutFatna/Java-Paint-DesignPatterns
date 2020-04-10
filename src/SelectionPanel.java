import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import javax.imageio.ImageIO;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.MouseInputAdapter;


public class SelectionPanel extends Box implements Observer
{

    JButton eraseButton; JButton lineButton;
    JButton circleButton; JButton rectangleButton;

    JButton color1Button; JButton color2Button; JButton color3Button;
    JButton color4Button; JButton color5Button; JButton color6Button;

    JButton thickness1Button; JButton thickness2Button;
    JButton thickness3Button; JButton thickness4Button;

    ButtonGroup toolButtonGroup;
    ButtonGroup colorButtonGroup;
    ButtonGroup lineThicknessButtonGroup;

    JPanel toolPanel;
    JPanel colorPanel;
    JPanel lineThicknessPanel;

    PaintModel model= PaintModel.getInstance();

    public SelectionPanel(PaintModel model)
    {
        super(BoxLayout.Y_AXIS);

        this.model = model;

        model.addObserver(this);


        eraseButton = new JButton("Delete");
        lineButton = new JButton("Line"); circleButton = new JButton("Circle");
        rectangleButton = new JButton("Rect");

        // Instantiate color buttons.
        color1Button = new JButton("Black"); color2Button = new JButton("White");
        color3Button = new JButton("Red"); color4Button = new JButton("Green");
        color5Button = new JButton("Blue"); color6Button = new JButton("Pink");

        // Instantiate line thickness buttons.
        thickness1Button = new JButton(("Thickness 1")); thickness2Button = new JButton(("Thickness 2"));
        thickness3Button = new JButton(("Thickness 3")); thickness4Button = new JButton(("Thickness 4"));

        // Instantiate button groups.
        toolButtonGroup = new ButtonGroup();
        colorButtonGroup = new ButtonGroup();
        lineThicknessButtonGroup = new ButtonGroup();


        toolPanel = new JPanel( new GridLayout(1,4,5,5) );
        colorPanel = new JPanel( new GridLayout(1,4,5,5) );
        lineThicknessPanel = new JPanel( new GridLayout(1,4,5,5) );

        setButtonBorder(lineButton);

        eraseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.currentTool = PaintModel.Tool.EraseTool;
                defaultBorderToolButtonGroup();
                setButtonBorder(eraseButton);
            }
        });

        // Add action listener to line tool.
        lineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.currentTool = PaintModel.Tool.LineDrawTool;
                defaultBorderToolButtonGroup();
                setButtonBorder(lineButton);
            }
        });

        // Add action listener to circle tool.
        circleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.currentTool = PaintModel.Tool.CircleDrawTool;
                defaultBorderToolButtonGroup();
                setButtonBorder(circleButton);
            }
        });

        // Add action listener to rectangle tool.
        rectangleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.currentTool = PaintModel.Tool.RectangleDrawTool;
                defaultBorderToolButtonGroup();
                setButtonBorder(rectangleButton);
            }
        });


        toolButtonGroup.add(eraseButton);
        toolButtonGroup.add(lineButton);
        toolButtonGroup.add(circleButton);
        toolButtonGroup.add(rectangleButton);

        toolPanel.add(eraseButton);
        toolPanel.add(lineButton);
        toolPanel.add(circleButton);
        toolPanel.add(rectangleButton);

        setButtonBorder(color1Button);



        color1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.currentColor = model.colorOptions[0];
                model.changeSelectedShapeColor(model.currentColor);
                defaultBorderColorButtonGroup();
                setButtonBorder(color1Button);
            }
        });

        // Add action listener to the white color button.
        color2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.currentColor = model.colorOptions[1];
                model.changeSelectedShapeColor(model.currentColor);
                defaultBorderColorButtonGroup();
                setButtonBorder(color2Button);
            }
        });

        // Add action listener to the red color button.
        color3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.currentColor = model.colorOptions[2];
                model.changeSelectedShapeColor(model.currentColor);
                defaultBorderColorButtonGroup();
                setButtonBorder(color3Button);
            }
        });

        // Add action listener to the green color button.
        color4Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.currentColor = model.colorOptions[3];
                model.changeSelectedShapeColor(model.currentColor);
                defaultBorderColorButtonGroup();
                setButtonBorder(color4Button);
            }
        });

        // Add action listener to the blue color button.
        color5Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.currentColor = model.colorOptions[4];
                model.changeSelectedShapeColor(model.currentColor);
                defaultBorderColorButtonGroup();
                setButtonBorder(color5Button);
            }
        });

        // Add action listener to the pink color button.
        color6Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.currentColor = model.colorOptions[5];
                model.changeSelectedShapeColor(model.currentColor);
                defaultBorderColorButtonGroup();
                setButtonBorder(color6Button);
            }
        });

        // Add color buttons to a ButtonGroup.
        colorButtonGroup.add(color1Button);
        colorButtonGroup.add(color2Button);
        colorButtonGroup.add(color3Button);
        colorButtonGroup.add(color4Button);
        colorButtonGroup.add(color5Button);
        colorButtonGroup.add(color6Button);

        // Add color buttons to color panel.
        colorPanel.add(color1Button);
        colorPanel.add(color2Button);
        colorPanel.add(color3Button);
        colorPanel.add(color4Button);
        colorPanel.add(color5Button);
        colorPanel.add(color6Button);


        // Highlight default line thickness.
        setButtonBorder(thickness1Button);



        // Add action listener to the thickness level 1 button.
        thickness1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.currentLineThickness = model.linethicknessOptions[0];
                model.changeSelectedShapeThickness(model.currentLineThickness);
                defaultBorderThicknessButtonGroup();
                setButtonBorder(thickness1Button);
            }
        });

        // Add action listener to the thickness level 2 button.
        thickness2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.currentLineThickness = model.linethicknessOptions[1];
                model.changeSelectedShapeThickness(model.currentLineThickness);
                defaultBorderThicknessButtonGroup();
                setButtonBorder(thickness2Button);
            }
        });

        // Add action listener to the thickness level 3 button.
        thickness3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.currentLineThickness = model.linethicknessOptions[2];
                model.changeSelectedShapeThickness(model.currentLineThickness);
                defaultBorderThicknessButtonGroup();
                setButtonBorder(thickness3Button);
            }
        });

        // Add action listener to the thickness level 4 button.
        thickness4Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                defaultBorderThicknessButtonGroup();
                model.currentLineThickness = model.linethicknessOptions[3];
                model.changeSelectedShapeThickness(model.currentLineThickness);
                setButtonBorder(thickness4Button);
            }
        });

        // Add line thickness buttons to a ButtonGroup.
        lineThicknessButtonGroup.add(thickness1Button);
        lineThicknessButtonGroup.add(thickness2Button);
        lineThicknessButtonGroup.add(thickness3Button);
        lineThicknessButtonGroup.add(thickness4Button);

        // Add line thickness buttons to line thickness panel.
        lineThicknessPanel.add(thickness1Button);
        lineThicknessPanel.add(thickness2Button);
        lineThicknessPanel.add(thickness3Button);
        lineThicknessPanel.add(thickness4Button);


        this.add(Box.createVerticalStrut(10));
        this.add(toolPanel);
        this.add(Box.createVerticalStrut(10));
        this.add(colorPanel);
        this.add(Box.createVerticalStrut(10));
        this.add(lineThicknessPanel);
        this.add(Box.createVerticalStrut(10));

    }



    public void setButtonBorder(JButton button) {
        button.setBorder(new LineBorder(Color.MAGENTA, 3));
        button.setOpaque(true);
    }

    public void defaultBorderToolButtonGroup() {
        Border defaultBorder = UIManager.getBorder("Button.border");
        eraseButton.setBorder(defaultBorder);
        lineButton.setBorder(defaultBorder);
        circleButton.setBorder(defaultBorder);
        rectangleButton.setBorder(defaultBorder);
    }

    public void defaultBorderColorButtonGroup() {
        Border defaultBorder = UIManager.getBorder("Button.border");
        color1Button.setBorder(defaultBorder);
        color2Button.setBorder(defaultBorder);
        color3Button.setBorder(defaultBorder);
        color4Button.setBorder(defaultBorder);
        color5Button.setBorder(defaultBorder);
        color6Button.setBorder(defaultBorder);
    }

    public void defaultBorderThicknessButtonGroup() {
        Border defaultBorder = UIManager.getBorder("Button.border");
        thickness1Button.setBorder(defaultBorder);
        thickness2Button.setBorder(defaultBorder);
        thickness3Button.setBorder(defaultBorder);
        thickness4Button.setBorder(defaultBorder);
    }

    @Override
    public void update(Object observable)
    {
    }

}

