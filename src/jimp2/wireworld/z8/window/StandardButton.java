package jimp2.wireworld.z8.window;

import javax.swing.*;


public class StandardButton extends JButton {
    private static final String buttonTextFormat = "<html><body style=\"text-align: center;  text-justify: inter-word;\">%s</body></html>";

    public StandardButton(String name)
    {
        setPreferredSize(GUI.STANDARD_BUTTON_SIZE);
        setHorizontalAlignment(CENTER);
        setText(String.format(buttonTextFormat, name));
        setFont(GUI.STANDARD_FONT);
    }
}
