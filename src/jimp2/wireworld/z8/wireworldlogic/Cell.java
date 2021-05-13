package jimp2.wireworld.z8.wireworldlogic;

import java.awt.*;


public class Cell {

    private State state;
    private Point position;

    public Cell(Point position, State state) {
        this.position=position;
        this.state=state;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state=state;
    }

    public int countNeighbouringHeads(World world) {
        // TODO implement here
        return 0;
    }

}