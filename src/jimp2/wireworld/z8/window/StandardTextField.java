package jimp2.wireworld.z8.window;

import javax.swing.*;


public class StandardTextField extends JTextField {

    public StandardTextField() {
        setPreferredSize(Window.STANDARD_TEXT_FIELD_SIZE);
        setHorizontalAlignment(JTextField.CENTER);
        setFont(Window.STANDARD_FONT);
    }

    public StandardTextField(String text) {
        this();
        setText(text);
    }
}
