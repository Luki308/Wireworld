package jimp2.wireworld.z8;

import jimp2.wireworld.z8.datamangment.Wire;
import jimp2.wireworld.z8.window.Window;
import jimp2.wireworld.z8.wireworldlogic.*;
import jimp2.wireworld.z8.datamangment.DataManager;
import jimp2.wireworld.z8.datamangment.Element;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //Wireworld wireWorld;
        DataManager dataManager;

        List<Element> elementsOnWorld;

        int iterationsNumber;
        boolean isAutomationOn;
        boolean shouldIterateOnce;

        ActionListener mainEventManager = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        };
//              OPTIONAL FEATURE
        ActionListener editorEventManager = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        };
        MouseAdapter canvasEventManager = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        };
//        Point lastClicked;
//        Point previouslyClicked;
//        Element drawableElement;

        Window window = new Window(mainEventManager, editorEventManager, canvasEventManager);

        // END OF INITIALIZATION




        System.out.println("Good morning World!");



        //Przykładowy świat 3x3
        Wireworld wireworld = new Wireworld(3,3);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                wireworld.getWorld().cells[i][j].setState(State.CONDUCTOR);
                //System.out.println(wireworld.getWorld().cells[i][j].getState());
            }
        }
        wireworld.getWorld().cells[0][0].setState(State.HEAD);

        wireworld.update();
        for (int i = 0; i < 3; i++) {
                System.out.println(wireworld.getWorld().cells[0][i].getState()+" "+wireworld.getWorld().cells[1][i].getState()+" " + wireworld.getWorld().cells[2][i].getState());
        }
        System.out.println("\n");
        wireworld.update();
        for (int i = 0; i < 3; i++) {
            System.out.println(wireworld.getWorld().cells[0][i].getState()+" "+wireworld.getWorld().cells[1][i].getState()+" " + wireworld.getWorld().cells[2][i].getState());
        }
        System.out.println("\n");
        wireworld.update();
        for (int i = 0; i < 3; i++) {
            System.out.println(wireworld.getWorld().cells[0][i].getState()+" "+wireworld.getWorld().cells[1][i].getState()+" " + wireworld.getWorld().cells[2][i].getState());
        }
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