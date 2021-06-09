package jimp2.wireworld.z8.window;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

// the most "static" class available in java - contains some project's GUI constants
public final class GUI {
    // dimensions of various GUI elements
    public static final int WINDOW_WIDTH = 1200;
    public static final int WINDOW_HEIGHT = 800;

    public static final int ORIGIN_POINT = 0;
    public static final int UPPER_PANEL_HEIGHT = WINDOW_HEIGHT / 4;
    public static final int LOWER_PANEL_HEIGHT = WINDOW_HEIGHT * 3 / 4;
    public static final int LEFT_PANEL_HEIGHT = WINDOW_HEIGHT;
    public static final int RIGHT_PANEL_HEIGHT = WINDOW_HEIGHT;
    public static final int LEFT_PANEL_WIDTH = WINDOW_WIDTH / 4;
    public static final int RIGHT_PANELS_WIDTH = WINDOW_WIDTH * 3 / 4;

    public static final Dimension STANDARD_BUTTON_SIZE = new Dimension(145, 80);
    public static final Dimension STANDARD_TEXT_FIELD_SIZE = new Dimension(80, 80);
    public static final Dimension STANDARD_COMBO_BOX_SIZE = new Dimension(180, 40);
    public static final int STANDARD_LAYOUT_GAP = 10;

    public static final Dimension EDITOR_BUTTON_SIZE = new Dimension(200, 70);
    public static final Dimension EDITOR_TEXT_FIELD_SIZE = new Dimension(80, 40);

    // constant names of button action commands, ensuring no typo mistakes
    public static final String NEXT = "NEXT";
    public static final String AUTO = "AUTO";
    public static final String STOP = "STOP";
    public static final String SAVE_AS_FILE = "SAVE_AS_FILE";
    public static final String SAVE_AS_ELEMENT = "SAVE_AS_ELEMENT";
    public static final String CHOOSE_FILE = "CHOOSE_FILE";
    public static final String START = "START";
    public static final String ABORT = "ABORT";

    public static final Font STANDARD_FONT = new Font("Monospaced", Font.BOLD, 18);
    public static final Border THIN_BORDER = BorderFactory.createLineBorder(Color.BLACK,1);

    private GUI() {}
}