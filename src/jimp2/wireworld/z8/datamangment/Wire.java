package jimp2.wireworld.z8.datamangment;


import jimp2.wireworld.z8.wireworldlogic.CellsContainer;
import jimp2.wireworld.z8.wireworldlogic.World;

import java.awt.*;

public class Wire extends Element {

    private Point position2;

    public Wire(Point position1, Point position2) {
        this.position = position1;
        this.position2 = position2;
        name = "Wire";
        //cellsContainer = new CellsContainer();
    }

    @Override
    public boolean validateSpace(World world) {
        // TODO implement here
        return false;
    }

    @Override
    public void insertIntoWorld(World world) {
        // TODO implement here
    }

    @Override
    public void eraseFromWorld(World world) {
        // TODO implement here
    }
}