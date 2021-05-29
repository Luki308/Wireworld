package jimp2.wireworld.z8.window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;


public class Window extends JFrame {

    public Menu menu;
    public WorldEditor worldEditor;
    public GraphicWorld graphicWorld;


    public Window(ActionListener mainManager, ActionListener editorManager, MouseAdapter canvasManager) {
        menu = new Menu(mainManager);
        worldEditor = new WorldEditor(editorManager);
        graphicWorld = new GraphicWorld(canvasManager);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Wireworld Simulator");
        setVisible(true);
        setResizable(false);

        //Make frame located in center of screen and set it's size
        Point point = new Point((Toolkit.getDefaultToolkit().getScreenSize().width - GUI.WINDOW_WIDTH) / 2
                                , (Toolkit.getDefaultToolkit().getScreenSize().height - GUI.WINDOW_HEIGHT) / 2);
        Dimension dimension = new Dimension(GUI.WINDOW_WIDTH+ getInsets().left + getInsets().right
                                            , GUI.WINDOW_HEIGHT + getInsets().top + getInsets().bottom);
        Rectangle rectangle = new Rectangle(point, dimension);
        setBounds(rectangle);

        add(worldEditor, BorderLayout.WEST);
        add(menu, BorderLayout.NORTH);
        add(graphicWorld, BorderLayout.SOUTH);

        setLayout(new BorderLayout());
    }
}