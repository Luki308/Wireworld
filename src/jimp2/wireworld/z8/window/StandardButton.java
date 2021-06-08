package jimp2.wireworld.z8.window;

import javax.swing.*;
import java.awt.*;


public class StandardButton extends JButton {
    private static final String buttonTextFormat = "<html><body style=\"text-align: center;  text-justify: inter-word;\">%s</body></html>";

    public StandardButton(String name)
    {
        this(name, GUI.STANDARD_BUTTON_SIZE);
    }

    public StandardButton(String name, Dimension size)
    {
        setPreferredSize(size);
        setHorizontalAlignment(CENTER);
        setText(String.format(buttonTextFormat, name));
        setFont(GUI.STANDARD_FONT);
    }
}
