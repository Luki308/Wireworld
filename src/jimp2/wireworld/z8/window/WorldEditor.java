package jimp2.wireworld.z8.window;

import jimp2.wireworld.z8.datamangment.Orientation;
import jimp2.wireworld.z8.wireworldlogic.State;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Set;


public class WorldEditor extends JPanel {

    private final JButton newEmptyWorld = new StandardButton("Create new empty world", GUI.EDITOR_BUTTON_SIZE);
    private final NumericTextField worldWidth = new NumericTextField("Width", GUI.EDITOR_TEXT_FIELD_SIZE);
    private final NumericTextField worldHeight = new NumericTextField("Height", GUI.EDITOR_TEXT_FIELD_SIZE);

    private final JButton insertCustomElement = new StandardButton("Insert new custom element", GUI.EDITOR_BUTTON_SIZE);
    private final StandardComboBox<String> customElementName = new StandardComboBox();
    private final StandardComboBox<Orientation> customElementOrientation = new StandardComboBox(Orientation.values());

    private final JButton insertGenerator = new StandardButton("Insert new generator", GUI.EDITOR_BUTTON_SIZE);
    private final NumericTextField generatorWidth = new NumericTextField("Width",GUI.EDITOR_TEXT_FIELD_SIZE);
    private final NumericTextField generatorHeight = new NumericTextField("Height", GUI.EDITOR_TEXT_FIELD_SIZE);

    private final JButton insertCell = new StandardButton("Insert new cell", GUI.EDITOR_BUTTON_SIZE);
    private final StandardComboBox<State> cellState = new StandardComboBox(State.values());

    private final JButton insertElectron = new StandardButton("Insert new electron", GUI.EDITOR_BUTTON_SIZE);
    private final StandardComboBox<Orientation> electronOrientation = new StandardComboBox(Orientation.values());

    private final JButton insertWire = new StandardButton("Insert new wire", GUI.EDITOR_BUTTON_SIZE);


    public WorldEditor(ActionListener editorManager) {
        Point point = new Point(GUI.ORIGIN_POINT, GUI.ORIGIN_POINT);
        Dimension dimension = new Dimension(GUI.LEFT_PANEL_WIDTH, GUI.LEFT_PANEL_HEIGHT);
        Rectangle rectangle = new Rectangle(point, dimension);
        setBounds(rectangle);

        setBorder(new MatteBorder(0, 0, 0, 2, Color.BLACK));

        add(newEmptyWorld);
        newEmptyWorld.setActionCommand(GUI.NEW_EMPTY_WORLD);
        add(worldWidth);
        add(worldHeight);

        add(insertCustomElement);
        insertCustomElement.setActionCommand(GUI.INSERT_CUSTOM_ELEMENT);
        add(customElementName);
        add(customElementOrientation);

        add(insertGenerator);
        insertGenerator.setActionCommand(GUI.INSERT_GENERATOR);
        add(generatorWidth);
        add(generatorHeight);

        add(insertCell);
        insertCell.setActionCommand(GUI.INSERT_CELL);
        add(cellState);

        add(insertElectron);
        insertElectron.setActionCommand(GUI.INSERT_ELECTRON);
        add(electronOrientation);

        add(insertWire);
        insertWire.setActionCommand(GUI.INSERT_WIRE);

        setLayout(new FlowLayout(FlowLayout.CENTER, GUI.STANDARD_LAYOUT_GAP, GUI.STANDARD_LAYOUT_GAP));

        for (Component component : getComponents()) {
            if (component instanceof JButton) {
                ((JButton) component).addActionListener(editorManager);
            }
        }
    }

    private void setButtons(boolean shouldUnlock) {
        for (Component component : getComponents()) {
            component.setEnabled(shouldUnlock);
        }
    }

    public void lockEditor() {
        setButtons(false);
    }

    public void unlockEditor() {
        setButtons(true);
    }

    public Dimension getWorldSize() {
        Dimension size = new Dimension(worldWidth.getNumber(), worldHeight.getNumber());
        if(size.getWidth() > 0 && size.getHeight() > 0) {
            return size;
        } else {
            JOptionPane.showMessageDialog(getRootPane(), "World's dimensions must be greater than 0!");
            return null;
        }
    }


    public State getCellParameter() {
        // TODO implement here
        return null;
    }

    public Orientation getElectronParameter() {
        // TODO implement here
        return null;
    }

    public Dimension getGeneratorParameters() {
        // TODO implement here
        return null;
    }

    public String getCustomElementName() {
        // TODO implement here
        return "";
    }

    public Orientation getCustomElementOrientation() {
        // TODO implement here
        return null;
    }

    public void initializeCustomElementsNames(Set<String> customElementsNames) {
        customElementName.setItems(customElementsNames.toArray(new String[0]));
    }
}