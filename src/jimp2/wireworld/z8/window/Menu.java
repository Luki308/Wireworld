package jimp2.wireworld.z8.window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;


public class Menu extends JPanel {

    // this one probably won't be used due to better file choosing method
    //private JTextField inputName = new JTextField();
    // TODO: maybe abort button in case user types too many iterations to process? like 999999
    /*
    Solution still needs refactoring due to huge amount of magical numbers.
    Its worth to consider creation of GUI "static" class containing static variables
     Add font to that class and proper sizes. Rename *screen* from width/height
     maybe add initializing classes for textfields and/or labels
     */

    private FixedButton auto = new FixedButton("Auto");
    private JButton stop = new FixedButton("Stop");
    private JButton next = new FixedButton("Next");
    private JButton saveAsInputFile = new FixedButton("Save as an input file");
    private JButton saveAsCustomElement = new FixedButton("Save as a custom element");

    private JLabel iterationsLabel = new JLabel("Iterations:", JLabel.RIGHT);
    private JTextField iterations = new JTextField("0");
    private JButton start = new FixedButton("Start");


    public Menu(ActionListener mainManager) {
        setBounds(Window.screenWidth / 4, 0, Window.screenWidth * 3 / 4, Window.screenHeight / 4);
        setBorder(Window.border);

        iterationsLabel.setPreferredSize(new Dimension(150, 80));
        iterationsLabel.setFont(new Font("Monospaced", Font.BOLD, 20));
        iterations.setFont(new Font("Monospaced", Font.BOLD, 20));
        iterations.setPreferredSize(new Dimension(80, 80));
        iterations.setHorizontalAlignment(JTextField.CENTER);

        add(auto);
        add(stop);
        add(next);
        add(saveAsInputFile);
        add(saveAsCustomElement);
        add(iterationsLabel);
        add(iterations);
        add(start);

        setLayout(new FlowLayout(FlowLayout.CENTER, 10 ,10));


        // TODO implement here
    }


    public HashMap<String, JButton> getAllButtons() {
        // TODO implement here
        return null;
    }


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