package jimp2.wireworld.z8.window;

import jimp2.wireworld.z8.externalclasses.TextPrompt;

import javax.swing.*;
import java.awt.*;


public class NumericTextField extends JTextField {
    private final String name;

    public NumericTextField(String name, Dimension size) {
        this.name = name;

        setPreferredSize(size);
        setHorizontalAlignment(JTextField.CENTER);
        setFont(GUI.STANDARD_FONT);

        TextPrompt promptInCaseTextIsMissing = new TextPrompt(name, this);
    }

    public NumericTextField(String name) {
        this(name, GUI.STANDARD_TEXT_FIELD_SIZE);
    }

    public NumericTextField(String name, String text) {
        this(name);
        setText(text);
    }

    public int getNumber() {
        int returnedNumber = -1;
        String textValue = getText();

        if (textValue.matches("\\d+")) {
            returnedNumber = Integer.parseInt(textValue);
        } else {
            JOptionPane.showMessageDialog(getParent(), String.format("%s must be a positive integer!", name));
        }

        return returnedNumber;
    }
}
