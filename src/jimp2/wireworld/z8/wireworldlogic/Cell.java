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
        int x = position.x;
        int y = position.y;
        int numberOfHeads = 0;
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i >= 0 && i < world.height && j >= 0 && j < world.width && (i != x || j != y) )
                    if(world.cells[i][j].getState() == State.HEAD)
                        numberOfHeads++;
            }
        }
        return numberOfHeads;
    }

}