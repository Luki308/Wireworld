package jimp2.wireworld.z8.window;

import javax.swing.*;


public class StandardButton extends JButton {
    private static final String buttonTextFormat = "<html><body style=\"text-align: center;  text-justify: inter-word;\">%s</body></html>";

    public StandardButton(String name)
    {
        setPreferredSize(Window.STANDARD_BUTTON_SIZE);
        setText(String.format(buttonTextFormat, name));
        setFont(Window.STANDARD_FONT);
    }
}
