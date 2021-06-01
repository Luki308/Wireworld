package jimp2.wireworld.z8.datamangment;


import jimp2.wireworld.z8.wireworldlogic.CellsContainer;
import jimp2.wireworld.z8.wireworldlogic.State;
import jimp2.wireworld.z8.wireworldlogic.World;

import java.awt.*;

public class Electron extends Element {


    private Orientation orientation;


    public Electron(Point position, Orientation orientation) {
        this.position = position;
        this.orientation = orientation;
        name = "Electron";
        cellsContainer = new CellsContainer(1,1);
        cellsContainer.cells[0][0].setState(State.HEAD);
    }

    @Override
    public boolean validateSpace(World world) {
        // TODO implement here
        return false;
    }

    @Override
    public void insertIntoWorld(World world) {
        if(world.cells[position.x][position.y].getState()==State.CONDUCTOR) {
            world.cells[position.x][position.y].setState(State.HEAD);
            switch (orientation) {
                case NORTH:
                    if (world.cells[position.x][position.y + 1].getState().equals(State.CONDUCTOR)&& position.y+1 < world.getHeight())
                        world.cells[position.x][position.y + 1].setState(State.TAIL);
                case WEST:
                    if (world.cells[position.x+1][position.y].getState().equals(State.CONDUCTOR) && position.x+1 < world.getWidth())
                        world.cells[position.x+1][position.y].setState(State.TAIL);
                case EAST:
                    if (world.cells[position.x-1][position.y].getState().equals(State.CONDUCTOR) && position.x-1 >= 0)
                        world.cells[position.x-1][position.y].setState(State.TAIL);
                case SOUTH:
                    if (world.cells[position.x][position.y-1].getState().equals(State.CONDUCTOR) && position.y-1 >= 0)
                        world.cells[position.x][position.y-1].setState(State.TAIL);
                case NORTH_WEST:
                    if (world.cells[position.x+1][position.y+1].getState().equals(State.CONDUCTOR) && position.x+1 < world.getWidth() && position.y+1 < world.getHeight())
                        world.cells[position.x+1][position.y+1].setState(State.TAIL);
                case NORTH_EAST:
                    if (world.cells[position.x-1][position.y+1].getState().equals(State.CONDUCTOR) && position.x-1 >= 0 && position.y+1 < world.getHeight())
                        world.cells[position.x-1][position.y+1].setState(State.TAIL);
                case SOUTH_WEST:
                    if (world.cells[position.x+1][position.y-1].getState().equals(State.CONDUCTOR) && position.x+1 <= world.getWidth() && position.y-1 >= 0)
                        world.cells[position.x+1][position.y-1].setState(State.TAIL);
                case SOUTH_EAST:
                    if (world.cells[position.x-1][position.y-1].getState().equals(State.CONDUCTOR) && position.x-1 >= 0 && position.y-1 >= 0)
                        world.cells[position.x-1][position.y-1].setState(State.TAIL);
            }
        }
        else System.err.println("Electron's head or tail won't be put on Conductor\nx:"+position.x+" y:"+position.y);
    }

    @Override
    public void eraseFromWorld(World world) {
        // TODO implement here
    }
}