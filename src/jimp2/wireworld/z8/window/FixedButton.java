package jimp2.wireworld.z8.window;

import javax.swing.*;
import java.awt.*;

public class FixedButton extends JButton {
    public FixedButton(String name)
    {
        super( String.format("<html><body style=\"text-align: center;  text-justify: inter-word;\">%s</body></html>", name));
        setPreferredSize(new Dimension(150, 80));
        setFont(new Font("Monospaced", Font.BOLD, 20));
    }
}
