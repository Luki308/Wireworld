package jimp2.wireworld.z8.window;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.HashMap;


public class Menu extends JPanel {

    private JTextField inputName;

    /**
     * 
     */
    private JTextField iterations;

    /**
     * 
     */
    private JButton start;

    /**
     * 
     */
    private JButton auto;

    /**
     * 
     */
    private JButton stop;

    /**
     * 
     */
    private JButton next;

    /**
     * 
     */
    private JButton saveAsInputFile;

    /**
     * 
     */
    private JButton saveAsCustomElement;


    /**
     * @param mainManager
     */
    public Menu(ActionListener mainManager) {
        // TODO implement here
    }

    /**
     * @return
     */
    public HashMap<String, JButton> getAllButtons() {
        // TODO implement here
        return null;
    }

    /**
     * @param fileName
     */
    private void ensureFileIsJson(String fileName) {
        // TODO implement here
    }

    /**
     * @return
     */
    public String getInputFileName() {
        // TODO implement here
        return "";
    }

    /**
     * @return
     */
    public int getIterationNumber() {
        // TODO implement here
        return 0;
    }

    /**
     * 
     */
    public void setIterationNumber() {
        // TODO implement here
    }

    /**
     * 
     */
    public void unlockStartingFields() {
        // TODO implement here
    }

    /**
     * 
     */
    public void unlockNavigationFields() {
        // TODO implement here
    }

    /**
     * 
     */
    public void unlockStop() {
        // TODO implement here
    }

}