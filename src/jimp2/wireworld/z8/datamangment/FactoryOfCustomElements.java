package jimp2.wireworld.z8.datamangment;


import jimp2.wireworld.z8.wireworldlogic.World;

import java.awt.*;
import java.util.HashMap;

public class FactoryOfCustomElements {

    private HashMap<String, CustomElement> availableCustomElements;


    public FactoryOfCustomElements() {
        initalizeElements();
        // TODO implement here
    }

    /**
     * 
     */
    private void initalizeElements() {
        // TODO implement here

    }

    public HashMap<String, CustomElement> getAvailableCustomElements() {
        return availableCustomElements;
    }

    public void saveNewCustomElement(World world, Point startingPoint) {

    }

}