package jimp2.wireworld.z8;

import jimp2.wireworld.z8.datamangment.WorldData;
import jimp2.wireworld.z8.window.GUI;
import jimp2.wireworld.z8.window.Window;
import jimp2.wireworld.z8.wireworldlogic.*;
import jimp2.wireworld.z8.datamangment.DataManager;
import jimp2.wireworld.z8.datamangment.Element;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;


public class WireworldManager {

    private final Window window;
    private final Wireworld wireworld = new Wireworld();
    private final DataManager dataManager = new DataManager();

    private final static int automationInterval = 200;
    private Timer automationTimer;

    private WorldData worldData = null;

    private int iterationsNumber = 0;

    private Point lastClickedPoint = null;
    private Point previouslyClickedPoint = null;
    private Element drawableElement = null;

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

            switch (command) {
                case GUI.NEW_EMPTY_WORLD:
                    newWorld(window.worldEditor.getWorldSize());
                    break;
                case GUI.INSERT_CUSTOM_ELEMENT:
                    break;
                case GUI.INSERT_GENERATOR:
                    break;
                case GUI.INSERT_CELL:
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
        }
    };


    public WireworldManager() {
        window = new Window(mainEventManager, editorEventManager, canvasEventManager);
        window.worldEditor.initializeCustomElementsNames(dataManager.factory.getAvailableCustomElements().keySet());

        // placeholder initialization not to leave empty space at start
        worldData = dataManager.readInputFile();
        start();
    }

    private void next() {
        wireworld.update();
        window.menu.setIterationNumber(--iterationsNumber);
        window.graphicWorld.drawWorld();
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
        dataManager.writeIterationToFile(iterationsNumber, wireworld.getWorld(), worldData.elements);
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
        dataManager.setInputFile();
        worldData = dataManager.readInputFile();
    }

    private void start() {
        if(worldData != null){

            iterationsNumber = window.menu.getIterationNumber();

            if (iterationsNumber >= 0) {
                wireworld.initializeWorld(worldData);
                window.graphicWorld.initialize(wireworld.getWorld());

                window.menu.unlockNavigationFields();
                window.worldEditor.lockEditor();

                // in case provided number of iterations equals 0
                checkIfFinishedIterating();
            }
        } else {
            JOptionPane.showMessageDialog(window, "Current world data is set to none!");
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
    }

    private void newWorld(Dimension size) {
        worldData = size != null ? new WorldData(size.width, size.height, null) : null;
    }
}