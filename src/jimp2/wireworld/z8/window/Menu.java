package jimp2.wireworld.z8.window;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionListener;


public class Menu extends JPanel {

    // first row
    private final StandardButton auto = new StandardButton("Auto");
    private final JButton stop = new StandardButton("Stop");
    private final JButton next = new StandardButton("Next");
    private final JButton saveAsInputFile = new StandardButton("Save as an input file");
    private final JButton saveAsCustomElement = new StandardButton("Save as a custom element");
    // second row
    private final JLabel iterationsLabel = new StandardLabel("Iterations:", JLabel.RIGHT);
    private final JTextField iterations = new StandardTextField("0");
    private final JButton chooseInputFile = new StandardButton("Choose input .json file");
    private final JButton start = new StandardButton("Start");
    private final JButton abort = new StandardButton("Abort");


    public Menu(ActionListener mainManager) {
        Point point = new Point(GUI.LEFT_PANEL_WIDTH, GUI.ORIGIN_POINT);
        Dimension dimension = new Dimension(GUI.RIGHT_PANELS_WIDTH, GUI.UPPER_PANEL_HEIGHT);
        Rectangle rectangle = new Rectangle(point, dimension);
        setBounds(rectangle);

        setBorder(new MatteBorder(0, 2, 2, 0, Color.BLACK));

        add(next);
        next.setActionCommand(GUI.NEXT);
        add(auto);
        auto.setActionCommand(GUI.AUTO);
        add(stop);
        stop.setActionCommand(GUI.STOP);
        add(saveAsInputFile);
        saveAsInputFile.setActionCommand(GUI.SAVE_AS_FILE);
        add(saveAsCustomElement);
        saveAsCustomElement.setActionCommand(GUI.SAVE_AS_ELEMENT);

        add(iterationsLabel);
        add(iterations);
        add(chooseInputFile);
        chooseInputFile.setActionCommand(GUI.CHOOSE_FILE);
        add(start);
        start.setActionCommand(GUI.START);
        add(abort);
        abort.setActionCommand(GUI.ABORT);

        setLayout(new FlowLayout(FlowLayout.CENTER, GUI.STANDARD_LAYOUT_GAP, GUI.STANDARD_LAYOUT_GAP));

        for (Component component : getComponents()) {
            if (component instanceof JButton) {
                ((JButton) component).addActionListener(mainManager);
            }
        }
    }

    public int getIterationNumber() {
        int iterationNumber = -1;
        String textValue = iterations.getText();

        if (textValue.matches("\\d+")) {
            iterationNumber = Integer.parseInt(textValue);
        } else {
            JOptionPane.showMessageDialog(getParent(), "Number of iterations must be a positive integer!");
        }

        return iterationNumber;
    }

    public void setIterationNumber(int remainingIterations) {
        if (remainingIterations >= 0) {
            iterations.setText(Integer.toString(remainingIterations));
        }
    }

    public void unlockStartingFields() {
        start.setEnabled(true);
        iterations.setEnabled(true);
        chooseInputFile.setEnabled(true);
        saveAsInputFile.setEnabled(true);
        saveAsCustomElement.setEnabled(true);

        auto.setEnabled(false);
        stop.setEnabled(false);
        next.setEnabled(false);
        abort.setEnabled(false);
    }

    public void unlockNavigationFields() {
        auto.setEnabled(true);
        next.setEnabled(true);
        abort.setEnabled(true);
        saveAsInputFile.setEnabled(true);
        saveAsCustomElement.setEnabled(true);

        stop.setEnabled(false);
        chooseInputFile.setEnabled(false);
        iterations.setEnabled(false);
        start.setEnabled(false);
    }

    public void unlockStop() {
        stop.setEnabled(true);
        abort.setEnabled(true);

        auto.setEnabled(false);
        next.setEnabled(false);
        saveAsInputFile.setEnabled(false);
        saveAsCustomElement.setEnabled(false);
        iterations.setEnabled(false);
        chooseInputFile.setEnabled(false);
        start.setEnabled(false);
    }
}