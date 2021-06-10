package jimp2.wireworld.z8;

import jimp2.wireworld.z8.datamangment.CellElement;
import jimp2.wireworld.z8.datamangment.WorldData;
import jimp2.wireworld.z8.window.GUI;
import jimp2.wireworld.z8.window.Window;
import jimp2.wireworld.z8.wireworldlogic.*;
import jimp2.wireworld.z8.datamangment.DataManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class WireworldManager {

    private final Window window;
    private final Wireworld wireworld = new Wireworld();
    private final DataManager dataManager = new DataManager();

    private final static int automationInterval = 200;
    private Timer automationTimer;

    private WorldData worldData;
    private World startingWorld;

    private int iterationsNumber = 0;
    private int whichIteration = 0;

    public static final String UNDO_ACTION = "UNDO_ACTION";
    private Point lastClickedPoint = null;
    private Point previouslyClickedPoint = null;
    private Element drawableElement = null;
    private ActionEvent lastClickedEditorButtonEvent;
    private final ArrayList<World> undoList = new ArrayList<>();

    private final ActionListener mainEventManager = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command == null) return;

            switch (command) {
                case GUI.NEXT:
                    next();
                    break;
                case GUI.AUTO:
                    startAutomation();
                    break;
                case GUI.STOP:
                    stopAutomation();
                    break;
                case GUI.SAVE_AS_FILE:
                    saveAsInputFile();
                    break;
                case GUI.SAVE_AS_ELEMENT:
                    saveAsNewCustomElement();
                    break;
                case GUI.CHOOSE_FILE:
                    chooseInputFile();
                    break;
                case GUI.START:
                    start();
                    break;
                case GUI.ABORT:
                    abort();
                    break;
                default:
                    JOptionPane.showMessageDialog(window, "Unexpected main manager event source!");
                    break;
            }
        }
    };

    private final ActionListener editorEventManager = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command == null) return;
            lastClickedEditorButtonEvent = e;

            switch (command) {
                case GUI.NEW_EMPTY_WORLD:
                    newWorld(window.worldEditor.getWorldSize());
                    break;
                case GUI.INSERT_CUSTOM_ELEMENT:

                    break;
                case GUI.INSERT_GENERATOR:
                    break;
                case GUI.INSERT_CELL:
                    drawableElement = new CellElement(window.worldEditor.getCellElementState());
                    break;
                case GUI.INSERT_ELECTRON:
                    break;
                case GUI.INSERT_WIRE:
                    break;
                default:
                    JOptionPane.showMessageDialog(window, "Unexpected editor manager event source!");
                    break;
            }
        }
    };

    private final MouseAdapter canvasEventManager = new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent click) {
            super.mousePressed(click);

            previouslyClickedPoint = lastClickedPoint;
            lastClickedPoint = window.graphicWorld.calculateClickPosition(click);

            if(drawableElement != null) {
                World previousWorld = new World(worldData.width, worldData.height);
                previousWorld.copyCells(wireworld.getWorld());
                undoList.add(previousWorld);

                drawableElement.setPosition(lastClickedPoint);
                drawableElement.insertIntoWorld(wireworld.getWorld());
                window.graphicWorld.drawWorld();

                worldData.elements.add(drawableElement);
                editorEventManager.actionPerformed(lastClickedEditorButtonEvent);
            }
        }
    };

    private final Action undoAction = new AbstractAction(UNDO_ACTION) {
        @Override
        public void actionPerformed(ActionEvent e) {

            if (undoList.size() > 0) {

                int lastUndoIndex = undoList.size() - 1;
                wireworld.setWorld(undoList.get(lastUndoIndex));
                undoList.remove(lastUndoIndex);

                window.graphicWorld.setNewGraphicWorld(wireworld.getWorld());
            }
        }
    };

    public WireworldManager() {
        window = new Window(mainEventManager, editorEventManager, canvasEventManager, undoAction);
        window.worldEditor.initializeCustomElementsNames(dataManager.factory.getAvailableCustomElements().keySet());
        restartInterface();

        // placeholder initialization not to leave empty space at start
        worldData = dataManager.readInputFile();
        initializeNewWorld();
    }

    private void next() {
        wireworld.update();
        window.menu.setIterationNumber(--iterationsNumber);
        window.graphicWorld.drawWorld();
        whichIteration++;
        checkIfFinishedIterating();
    }

    private void startAutomation() {
        automationTimer = new Timer();
        TimerTask iterateOnce = new TimerTask() {
            @Override
            public void run() {
                next();
            }
        };

        automationTimer.scheduleAtFixedRate(iterateOnce, 0, automationInterval);
        window.menu.unlockStop();
    }

    private void stopAutomation() {
        if (automationTimer != null) {
            automationTimer.cancel();
            automationTimer.purge();
        }
        window.menu.unlockNavigationFields();
    }

    private void saveAsInputFile() {
        JFileChooser jFileChooser = new JFileChooser(System.getProperty("user.dir"));
        jFileChooser.setDialogTitle("Select input .json file");
        jFileChooser.showSaveDialog(null);
        File saveFile = jFileChooser.getSelectedFile();

       dataManager.writeIterationToFile(whichIteration, startingWorld , wireworld.getWorld(), worldData.elements, saveFile);
    }

    private void saveAsNewCustomElement() {
        if (lastClickedPoint != null) {

            String userResponse = JOptionPane.showInputDialog(window, "Please write a name of the new Custom Element");
             if (userResponse != null) {
                dataManager.factory.saveNewCustomElement(wireworld.getWorld(), lastClickedPoint, userResponse);

                window.worldEditor.initializeCustomElementsNames(dataManager.factory.getAvailableCustomElements().keySet());
            }

        } else {
            JOptionPane.showMessageDialog(window, "You have to click element starting point first");
        }
    }

    private void chooseInputFile() {
        dataManager.chooseInputFile();
        WorldData newWorld = dataManager.readInputFile();
        if(newWorld != null) {
            worldData = newWorld;
            initializeNewWorld();
        }
    }

    private void start() {
        if(worldData != null){
            iterationsNumber = window.menu.getIterationNumber();

            if (iterationsNumber >= 0) {
                wireworld.resetWireworld();

                window.menu.unlockNavigationFields();
                window.worldEditor.lockEditor();

                // in case provided number of iterations equals 0
                checkIfFinishedIterating();
            }
        }
    }

    private void abort() {
        iterationsNumber = 0;
        window.menu.setIterationNumber(iterationsNumber);
        checkIfFinishedIterating();
    }

    private boolean hasFinishedIterating() {
        return iterationsNumber == 0;
    }

    private void checkIfFinishedIterating() {
        if(hasFinishedIterating()) {
            restartInterface();
        }
    }

    private void restartInterface() {
        stopAutomation();

        window.menu.unlockStartingFields();
        window.worldEditor.unlockEditor();

        lastClickedPoint = null;
        drawableElement = null;
        undoList.clear();
    }

    private void initializeNewWorld() {
        wireworld.initializeWorld(worldData);
        window.graphicWorld.initialize(wireworld.getWorld());
    }

    private void newWorld(Dimension size) {
        WorldData newWorld = size != null ? new WorldData(size.width, size.height, new ArrayList<>()) : null;
        if(newWorld != null) {
            worldData = newWorld;
            initializeNewWorld();
        }
    }
}