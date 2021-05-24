package jimp2.wireworld.z8.window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;


public class Menu extends JPanel {

    // this one probably won't be used due to better file choosing method
    //private JTextField inputName = new JTextField();

    // first row
    private final StandardButton auto = new StandardButton("Auto");
    private final JButton stop = new StandardButton("Stop");
    private final JButton next = new StandardButton("Next");
    private final JButton saveAsInputFile = new StandardButton("Save as an input file");
    private final JButton saveAsCustomElement = new StandardButton("Save as a custom element");
    // second row
    private final JLabel iterationsLabel = new StandardLabel("Iterations:", JLabel.RIGHT);
    private final JTextField iterations = new StandardTextField("0");
    private final JButton start = new StandardButton("Start");
    private final JButton abort = new StandardButton("Abort");


    public Menu(ActionListener mainManager) {
        setBounds(Window.LEFT_PANEL_WIDTH, Window.ORIGIN_POINT, Window.RIGHT_PANELS_WIDTH, Window.UPPER_PANEL_HEIGHT);
        setBorder(Window.STANDARD_BORDER);

        add(auto);
        auto.setActionCommand("AUTO");
        add(stop);
        stop.setActionCommand("STOP");
        add(next);
        next.setActionCommand("NEXT");
        add(saveAsInputFile);
        saveAsInputFile.setActionCommand("SAVE_AS_FILE");
        add(saveAsCustomElement);
        saveAsCustomElement.setActionCommand("SAVE_AS_ELEMENT");
        add(start);
        start.setActionCommand("START");
        add(abort);
        abort.setActionCommand("ABORT");
        add(iterationsLabel);
        add(iterations);

        setLayout(new FlowLayout(FlowLayout.CENTER, Window.STANDARD_LAYOUT_GAP ,Window.STANDARD_LAYOUT_GAP));

        for (Component component : getComponents()){
            if(component instanceof JButton){
                ((JButton)component).addActionListener(mainManager);
            }
        }
    }

/* PROBABLY USELESS NOW
    public HashMap<String, JButton> getAllButtons() {
        // TODO implement here
        return null;
    }
*/

    private void ensureFileIsJson(String fileName) {
        // TODO implement here
    }


    public String getInputFileName() {
        // TODO implement here
        return "";
    }


    public int getIterationNumber() {
        // TODO implement here
        return 0;
    }


    public void setIterationNumber() {
        // TODO implement here
    }


    public void unlockStartingFields() {
        // TODO implement here
    }


    public void unlockNavigationFields() {
        // TODO implement here
    }


    public void unlockStop() {
        // TODO implement here
    }

}