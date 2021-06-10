package jimp2.wireworld.z8;

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
import java.io.File;
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


    //              OPTIONAL FEATURE
    private final ActionListener editorEventManager = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

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
        window.menu.unlockStartingFields();

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
        JFileChooser jFileChooser = new JFileChooser(System.getProperty("user.dir"));
        jFileChooser.setDialogTitle("Select input .json file");
        jFileChooser.showSaveDialog(null);
        File saveFile = jFileChooser.getSelectedFile();

       dataManager.writeIterationToFile(iterationsNumber, startingWorld , wireworld.getWorld(), worldData.elements, saveFile); //TODO zmienic iterations number bo jest zawsze 0
    }

    private void saveAsNewCustomElement() {
        String userResponse;
        userResponse = JOptionPane.showInputDialog(window, "Please write a name of the new Custom Element");
        if(userResponse != null)
            dataManager.factory.saveNewCustomElement(wireworld.getWorld(), lastClickedPoint, userResponse);
    }

    private void chooseInputFile() {
        dataManager.setInputFile();
    }

    private void start() {
        worldData = dataManager.readInputFile();
        if(worldData != null){
            startingWorld = wireworld.getWorld();
            iterationsNumber = window.menu.getIterationNumber();

            if (iterationsNumber >= 0) {
                wireworld.initializeWorld(worldData);
                window.graphicWorld.initialize(wireworld.getWorld());
                window.menu.unlockNavigationFields();

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
        }
    }
}