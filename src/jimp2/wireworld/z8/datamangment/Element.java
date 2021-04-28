package jimp2.wireworld.z8.datamangment;


import jimp2.wireworld.z8.wireworldlogic.CellsContainer;
import jimp2.wireworld.z8.wireworldlogic.World;

import java.awt.*;

public abstract class Element {

    protected CellsContainer cellsContainer;

    protected String name;

    protected Point position;


    public boolean validateSpace(World world)
    {
        return true;
    }

    public void insertIntoWorld(World world)
    {

    }

    public void eraseFromWorld(World world)
    {

    }


    public String getName() {
        // TODO implement here
        return "";
    }

    public void setPosition() {
        // TODO implement here
    }

}