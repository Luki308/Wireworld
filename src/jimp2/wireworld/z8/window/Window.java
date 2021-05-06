package jimp2.wireworld.z8.window;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;


public class Window extends JFrame {
    public static final int screenWidth = 1200;
    public static final int screenHeight = 800;
    public static final Border border = BorderFactory.createLineBorder(Color.BLACK,2);


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
        setBounds((Toolkit.getDefaultToolkit().getScreenSize().width - screenWidth) / 2
                , (Toolkit.getDefaultToolkit().getScreenSize().height - screenHeight) / 2
                , screenWidth, screenHeight);


        add(menu, BorderLayout.NORTH);
        add(worldEditor, BorderLayout.WEST);
        add(graphicWorld, BorderLayout.EAST);

        setLayout(new BorderLayout());
    }
}