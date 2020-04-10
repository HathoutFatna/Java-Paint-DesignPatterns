import java.awt.*;
import javax.swing.*;


public class Main
{
    public static void main(String[] args)
    {

        PaintModel model = PaintModel.getInstance();
        JFrame frame = new JFrame("Paint");
        frame.setSize(1200, 1000);
        frame.setPreferredSize(new Dimension(1200,1000));
        frame.setMinimumSize(new Dimension(600, 500));


        JPanel mainPanel = new JPanel();

        mainPanel.setLayout(new BorderLayout());

        SelectionPanel selectionPanel = new SelectionPanel(model);
        mainPanel.add(selectionPanel, BorderLayout.NORTH);

        Canvas canvas = new Canvas(model, Color.white);
        Dimension fixedDim = new Dimension(frame.getWidth(), frame.getHeight());

        ScrollPane canvasScrollPane = new ScrollPane(ScrollPane.SCROLLBARS_AS_NEEDED);
        canvasScrollPane.setPreferredSize(fixedDim);

        canvasScrollPane.add(canvas);

        mainPanel.add(canvas, BorderLayout.CENTER);

        frame.add(mainPanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}