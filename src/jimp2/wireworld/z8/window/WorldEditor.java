package jimp2.wireworld.z8.window;

import jimp2.wireworld.z8.datamangment.Orientation;
import jimp2.wireworld.z8.wireworldlogic.State;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;


public class WorldEditor extends JPanel {

    private JButton newEmptyWorld;

    private JTextField worldWidth;

    /**
     * 
     */
    private JTextField worldHeight;

    /**
     * 
     */
    private JButton newCell;

    /**
     * 
     */
    private JComboBox cellState;

    /**
     * 
     */
    private JButton newWire;

    /**
     * 
     */
    private JButton newElectron;

    /**
     * 
     */
    private JComboBox electronOrientation;

    /**
     * 
     */
    private JButton newGenerator;

    /**
     * 
     */
    private int generatorWidth;

    /**
     * 
     */
    private int generatorHeight;

    /**
     * 
     */
    private JButton makeCustomElement;

    /**
     * 
     */
    private JComboBox customElementName;

    /**
     * 
     */
    private JComboBox customElementOrientation;


    /**
     * @param editorManager
     */
    public WorldEditor(ActionListener editorManager) {
        setBounds(GUI.ORIGIN_POINT, GUI.ORIGIN_POINT, GUI.LEFT_PANEL_WIDTH, GUI.LEFT_PANEL_HEIGHT);
        setBorder(GUI.STANDARD_BORDER);

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
     * 
     */
    public void lockButtons() {
        // TODO implement here
    }

    /**
     * 
     */
    public void unlockButtons() {
        // TODO implement here
    }

    /**
     * @return
     */
    public Point getWorldParameters() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public State getCellParameter() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public Orientation getElectronParameter() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public Point getGeneratorParameters() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public String getCustomElementName() {
        // TODO implement here
        return "";
    }

    /**
     * @return
     */
    public Orientation getCustomElementOrientation() {
        // TODO implement here
        return null;
    }

    /**
     * @param name
     */
    public void addToCustomElements(String name) {
        // TODO implement here
    }

}