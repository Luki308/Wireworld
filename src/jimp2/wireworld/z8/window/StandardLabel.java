package jimp2.wireworld.z8.window;

import javax.swing.*;


public class StandardLabel extends JLabel {

    public StandardLabel(){
        setPreferredSize(Window.STANDARD_BUTTON_SIZE);
        setFont(Window.STANDARD_FONT);
    }

    public StandardLabel(String text, int alignment){
        this();
        setText(text);
        setHorizontalAlignment(alignment);
    }
}
