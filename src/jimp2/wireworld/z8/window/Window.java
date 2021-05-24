package jimp2.wireworld.z8.window;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;


public class Window extends JFrame {
    public static final int WINDOW_WIDTH = 1200;
    public static final int WINDOW_HEIGHT = 800;

    public static final int ORIGIN_POINT = 0;
    public static final int UPPER_PANEL_HEIGHT = WINDOW_HEIGHT / 4;
    public static final int LOWER_PANEL_HEIGHT = WINDOW_HEIGHT * 3 / 4;
    public static final int LEFT_PANEL_HEIGHT = WINDOW_HEIGHT;
    public static final int LEFT_PANEL_WIDTH = WINDOW_WIDTH / 4;
    public static final int RIGHT_PANELS_WIDTH = WINDOW_WIDTH * 3 / 4;

    public static final Border STANDARD_BORDER = BorderFactory.createLineBorder(Color.BLACK,2);
    public static final Font STANDARD_FONT = new Font("Monospaced", Font.BOLD, 20);
    public static final Dimension STANDARD_BUTTON_SIZE = new Dimension(150, 80);
    public static final Dimension STANDARD_TEXT_FIELD_SIZE = new Dimension(80, 80);
    public static final int STANDARD_LAYOUT_GAP = 10;


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
        setBounds((Toolkit.getDefaultToolkit().getScreenSize().width - WINDOW_WIDTH) / 2
                , (Toolkit.getDefaultToolkit().getScreenSize().height - WINDOW_HEIGHT) / 2
                , WINDOW_WIDTH, WINDOW_HEIGHT);


        add(menu, BorderLayout.NORTH);
        add(worldEditor, BorderLayout.WEST);
        add(graphicWorld, BorderLayout.EAST);

        setLayout(new BorderLayout());
    }
}