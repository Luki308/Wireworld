package jimp2.wireworld.z8.window;

import javax.swing.*;


public class StandardTextField extends JTextField {

    public StandardTextField() {
        setPreferredSize(GUI.STANDARD_TEXT_FIELD_SIZE);
        setHorizontalAlignment(JTextField.CENTER);
        setFont(GUI.STANDARD_FONT);
    }

    public StandardTextField(String text) {
        this();
        setText(text);
    }
}
