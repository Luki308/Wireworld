package jimp2.wireworld.z8.datamangment;


import jimp2.wireworld.z8.wireworldlogic.State;
import jimp2.wireworld.z8.wireworldlogic.World;

import java.awt.*;


public class CellElement extends Element {

    private static final int CELL_SIZE = 1;
    private static final int CELL_INDEX = 0;


    public CellElement(Point position, State state) {
        super(position, CELL_SIZE, CELL_SIZE);
        name = DataNames.CellElement;

        cells[CELL_INDEX][CELL_INDEX].setState(state);
    }

    @Override
    public boolean validateSpace(World world) {
        // TODO implement here
        return true;
    }
}