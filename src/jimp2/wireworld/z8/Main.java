package jimp2.wireworld.z8;

import jimp2.wireworld.z8.window.Window;
import jimp2.wireworld.z8.wireworldlogic.Wireworld;
import jimp2.wireworld.z8.datamangment.DataManager;
import jimp2.wireworld.z8.datamangment.Element;

import java.awt.event.ActionListener;
import java.util.List;

public class Main {

    private Wireworld wireWorld;
    private DataManager dataManager;
    private Window window;

    private List<Element> elementsOnWorld;

    private ActionListener mainEventManager;
    private int iterationsNumber;
    private boolean isAutomationOn;
    private boolean shouldIterateOnce;

//              OPTIONAL FEATURE
//    private ActionListener editorEventManager;
//    private MouseAdapter canvasEventManager;
//    private Point lastClicked;
//    private Point previouslyClicked;
//    private Element drawableElement;


    public static void main(String[] args) {
        System.out.println("Good morning World!");
    }


    private void initiate() {
        // TODO implement here
    }

    private void start() {
        // TODO implement here
    }

    private void startAutomation() {
        // TODO implement here
    }

    private void stopAutomation() {
        // TODO implement here
    }

    private void next() {
        // TODO implement here
    }
}