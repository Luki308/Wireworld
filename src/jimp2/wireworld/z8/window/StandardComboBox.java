package jimp2.wireworld.z8.window;

import javax.swing.*;


public class StandardComboBox<T> extends JComboBox<T> {

    public StandardComboBox() {
        setPreferredSize(GUI.STANDARD_COMBO_BOX_SIZE);
        ((JLabel)getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        setFont(GUI.STANDARD_FONT);
    }

    public StandardComboBox(T[] items) {
        this();

        setItems(items);
    }

    public void setItems(T[] items) {
        removeAllItems();

        for (T item : items) {
            if (item != null) {
                addItem(item);
            }
        }
    }
}
