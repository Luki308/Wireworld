package jimp2.wireworld.z8.datamangment;

import jimp2.wireworld.z8.wireworldlogic.State;


import java.awt.*;


public class CellElement extends Element {

    private static final int CELL_SIZE = 1;
    private static final int CELL_INDEX = 0;
    private final State state;

    public CellElement(State state) {
        this(DataNames.TEMPLATE_POINT, state);
    }

    public CellElement(Point position, State state) {
        super(position, CELL_SIZE, CELL_SIZE);
        name = DataNames.CellElement;
        this.state = state;

        cells[CELL_INDEX][CELL_INDEX].setState(state);
    }

    public State getState() {
        return state;
    }
}