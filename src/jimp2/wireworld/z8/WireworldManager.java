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
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class WireworldManager {

    private final Window window;
    private final Wireworld wireworld = new Wireworld();
    private final DataManager dataManager = new DataManager();

    private final static int automationInterval = 200;
    private Timer automationTimer;

    private List<Element> elementsOnWorld;

    private int iterationsNumber = 0;


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
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
        }
    };
    private Point lastClickedPoint;
    //private Point previouslyClickedPoint;
    //private Element drawableElement;


    public WireworldManager() {
        window = new Window(mainEventManager, editorEventManager, canvasEventManager);
        window.worldEditor.initializeCustomElementsNames(dataManager.factory.getAvailableCustomElements().keySet());

        // placeholder initialization not to leave empty space at start
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
        dataManager.writeIterationToFile(iterationsNumber, wireworld.getWorld(), elementsOnWorld);
    }

    private void saveAsNewCustomElement() {
        String userResponse;
        userResponse = JOptionPane.showInputDialog(window, "Please write a name of the new Custom Element");
        if(userResponse != null) {
            dataManager.factory.saveNewCustomElement(wireworld.getWorld(), lastClickedPoint, userResponse);

            window.worldEditor.initializeCustomElementsNames(dataManager.factory.getAvailableCustomElements().keySet());
        }
    }

    private void chooseInputFile() {
        dataManager.setInputFile();
    }

    private void start() {
        WorldData inputWorldData = dataManager.readInputFile();

        if(inputWorldData != null){

            iterationsNumber = window.menu.getIterationNumber();

            if (iterationsNumber >= 0) {
                wireworld.initializeWorld(inputWorldData);
                window.graphicWorld.initialize(wireworld.getWorld());

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
            stopAutomation();
            window.menu.unlockStartingFields();
            window.worldEditor.unlockEditor();
        }
    }
}