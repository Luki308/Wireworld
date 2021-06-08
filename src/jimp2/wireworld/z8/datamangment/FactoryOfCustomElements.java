package jimp2.wireworld.z8.datamangment;


import jimp2.wireworld.z8.wireworldlogic.World;

import java.awt.*;
import java.util.HashMap;

public class FactoryOfCustomElements {

    private HashMap<String, CustomElement> availableCustomElements = new HashMap<>();


    public FactoryOfCustomElements() {
        initalizeElements();
        // TODO implement here
    }

    /**
     * 
     */
    private void initalizeElements() {
        // placeholder data
        availableCustomElements.put("test1", null);
        availableCustomElements.put("test2", null);
        // TODO implement here

    }

    public HashMap<String, CustomElement> getAvailableCustomElements() {
        return availableCustomElements;
    }

    public void saveNewCustomElement(World world, Point startingPoint) {

    }

}