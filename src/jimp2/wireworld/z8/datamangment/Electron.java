package jimp2.wireworld.z8.datamangment;

import jimp2.wireworld.z8.wireworldlogic.State;
import jimp2.wireworld.z8.wireworldlogic.World;

import java.awt.*;


public class Electron extends Element {

    // HEAD in the center and TAIL in one of surrounding positions
    private static final int ELECTRON_SIZE = 3;
    private static final int HEAD_INDEX = 1;

    // these fields will be useful in THE EDITOR
    private Orientation orientation;


    public Electron(Point position, Orientation orientation) {
        super(new Point(position.x - HEAD_INDEX, position.y - HEAD_INDEX), ELECTRON_SIZE, ELECTRON_SIZE);
        name = DataNames.Electron;

        this.orientation = orientation;

        cells[HEAD_INDEX][HEAD_INDEX].setState(State.HEAD);

        switch (orientation) {
            case NORTH:
                cells[HEAD_INDEX][HEAD_INDEX + 1].setState(State.TAIL);
                break;
            case WEST:
                cells[HEAD_INDEX + 1][HEAD_INDEX].setState(State.TAIL);
                break;
            case EAST:
                cells[HEAD_INDEX - 1][HEAD_INDEX].setState(State.TAIL);
                break;
            case SOUTH:
                cells[HEAD_INDEX][HEAD_INDEX - 1].setState(State.TAIL);
                break;
            case NORTH_WEST:
                cells[HEAD_INDEX + 1][HEAD_INDEX + 1].setState(State.TAIL);
                break;
            case NORTH_EAST:
                cells[HEAD_INDEX - 1][HEAD_INDEX + 1].setState(State.TAIL);
                break;
            case SOUTH_WEST:
                cells[HEAD_INDEX + 1][HEAD_INDEX - 1].setState(State.TAIL);
                break;
            case SOUTH_EAST:
                cells[HEAD_INDEX - 1][HEAD_INDEX - 1].setState(State.TAIL);
                break;
        }
    }

    @Override
    public boolean validateSpace(World world) {
        // TODO implement here
        // something may be useful
        /*
                //world.cells[i + position.x][j + position.y].getState() == State.CONDUCTOR

                if (world.cells[position.x][position.y].getState() == State.CONDUCTOR) {
            world.cells[position.x][position.y].setState(State.HEAD);
            switch (orientation) {
                case NORTH:
                    if (cells[position.x][position.y + 1].getState().equals(State.CONDUCTOR) && position.y + 1 < getHeight())
                        cells[position.x][position.y + 1].setState(State.TAIL);
                case WEST:
                    if (cells[position.x + 1][position.y].getState().equals(State.CONDUCTOR) && position.x + 1 < getWidth())
                        cells[position.x + 1][position.y].setState(State.TAIL);
                case EAST:
                    if (cells[position.x - 1][position.y].getState().equals(State.CONDUCTOR) && position.x - 1 >= 0)
                        cells[position.x - 1][position.y].setState(State.TAIL);
                case SOUTH:
                    if (cells[position.x][position.y - 1].getState().equals(State.CONDUCTOR) && position.y - 1 >= 0)
                        cells[position.x][position.y - 1].setState(State.TAIL);
                case NORTH_WEST:
                    if (cells[position.x + 1][position.y + 1].getState().equals(State.CONDUCTOR) && position.x + 1 < getWidth() && position.y + 1 < getHeight())
                        cells[position.x + 1][position.y + 1].setState(State.TAIL);
                case NORTH_EAST:
                    if (cells[position.x - 1][position.y + 1].getState().equals(State.CONDUCTOR) && position.x - 1 >= 0 && position.y + 1 < getHeight())
                        cells[position.x - 1][position.y + 1].setState(State.TAIL);
                case SOUTH_WEST:
                    if (cells[position.x + 1][position.y - 1].getState().equals(State.CONDUCTOR) && position.x + 1 <= getWidth() && position.y - 1 >= 0)
                        cells[position.x + 1][position.y - 1].setState(State.TAIL);
                case SOUTH_EAST:
                    if (cells[position.x - 1][position.y - 1].getState().equals(State.CONDUCTOR) && position.x - 1 >= 0 && position.y - 1 >= 0)
                        cells[position.x - 1][position.y - 1].setState(State.TAIL);
            }
        } else
            System.err.println("Electron's head or tail won't be put on Conductor\nx:" + position.x + " y:" + position.y);
         */
        return true;
    }

    @Override
    public void insertIntoWorld(World world) {
        insertIntoWorld(world, true);
    }

    @Override
    public void eraseFromWorld(World world) {
        // TODO implement here
    }

    public Orientation getOrientation() {
        return orientation;
    }
}