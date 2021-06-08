package jimp2.wireworld.z8.window;

import jimp2.wireworld.z8.datamangment.Orientation;
import jimp2.wireworld.z8.externalclasses.TextPrompt;
import jimp2.wireworld.z8.wireworldlogic.State;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Set;


public class WorldEditor extends JPanel {

    private final JButton makeEmptyWorld = new StandardButton("New empty world", GUI.EDITOR_BUTTON_SIZE);
    private final NumericTextField worldWidth = new NumericTextField("Width", GUI.EDITOR_TEXT_FIELD_SIZE);
    private final NumericTextField worldHeight = new NumericTextField("Height", GUI.EDITOR_TEXT_FIELD_SIZE);

    private final JButton makeCustomElement = new StandardButton("Make custom element", GUI.EDITOR_BUTTON_SIZE);
    private final StandardComboBox<String> customElementName = new StandardComboBox();
    private final StandardComboBox<Orientation> customElementOrientation = new StandardComboBox(Orientation.values());

    private final JButton makeGenerator = new StandardButton("Make generator", GUI.EDITOR_BUTTON_SIZE);
    private final NumericTextField generatorWidth = new NumericTextField("Width",GUI.EDITOR_TEXT_FIELD_SIZE);
    private final NumericTextField generatorHeight = new NumericTextField("Height", GUI.EDITOR_TEXT_FIELD_SIZE);

    private final JButton makeCell = new StandardButton("Make cell", GUI.EDITOR_BUTTON_SIZE);
    private final StandardComboBox<State> cellState = new StandardComboBox(State.values());

    private final JButton makeElectron = new StandardButton("Make electron", GUI.EDITOR_BUTTON_SIZE);
    private final StandardComboBox<Orientation> electronOrientation = new StandardComboBox(Orientation.values());

    private final JButton makeWire = new StandardButton("Make wire", GUI.EDITOR_BUTTON_SIZE);


    public WorldEditor(ActionListener editorManager) {
        Point point = new Point(GUI.ORIGIN_POINT, GUI.ORIGIN_POINT);
        Dimension dimension = new Dimension(GUI.LEFT_PANEL_WIDTH, GUI.LEFT_PANEL_HEIGHT);
        Rectangle rectangle = new Rectangle(point, dimension);
        setBounds(rectangle);

        setBorder(new MatteBorder(0, 0, 0, 2, Color.BLACK));

        add(makeEmptyWorld);
        add(worldWidth);
        add(worldHeight);

        add(makeCustomElement);
        add(customElementName);
        add(customElementOrientation);

        add(makeGenerator);
        add(generatorWidth);
        add(generatorHeight);

        add(makeCell);
        add(cellState);

        add(makeElectron);
        add(electronOrientation);

        add(makeWire);

        setLayout(new FlowLayout(FlowLayout.CENTER, GUI.STANDARD_LAYOUT_GAP, GUI.STANDARD_LAYOUT_GAP));
    }

    private void setButtons(boolean shouldUnlock) {
        for (Component component : getComponents()) {
            component.setEnabled(shouldUnlock);
        }
    }

    public void lockButtons() {
        setButtons(false);
    }

    public void unlockButtons() {
        setButtons(true);
    }


    public Point getWorldParameters() {
        // TODO implement here
        return null;
    }


    public State getCellParameter() {
        // TODO implement here
        return null;
    }

    public Orientation getElectronParameter() {
        // TODO implement here
        return null;
    }

    public Point getGeneratorParameters() {
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