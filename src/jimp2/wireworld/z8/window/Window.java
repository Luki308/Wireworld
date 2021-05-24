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

        //Make frame located in center of screens
        setBounds((Toolkit.getDefaultToolkit().getScreenSize().width - GUI.WINDOW_WIDTH) / 2
                , (Toolkit.getDefaultToolkit().getScreenSize().height - GUI.WINDOW_HEIGHT) / 2
                , GUI.WINDOW_WIDTH, GUI.WINDOW_HEIGHT);


        add(menu, BorderLayout.NORTH);
        add(worldEditor, BorderLayout.WEST);
        add(graphicWorld, BorderLayout.EAST);

        setLayout(new BorderLayout());
    }
}