package jimp2.wireworld.z8.window;

import javax.swing.*;
import java.awt.*;


public class RightPanel extends JPanel {

    public RightPanel(Menu menu, GraphicWorld graphicWorld) {
        Point point = new Point(GUI.LEFT_PANEL_WIDTH, GUI.ORIGIN_POINT);
        Dimension dimension = new Dimension(GUI.RIGHT_PANELS_WIDTH, GUI.RIGHT_PANEL_HEIGHT);
        Rectangle rectangle = new Rectangle(point, dimension);
        setBounds(rectangle);

        add(menu, BorderLayout.NORTH);
        add(graphicWorld, BorderLayout.SOUTH);

        setLayout(new BorderLayout());
    }
}