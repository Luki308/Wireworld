package jimp2.wireworld.z8.datamangment;

import jimp2.wireworld.z8.wireworldlogic.State;
import jimp2.wireworld.z8.wireworldlogic.World;

import java.awt.*;


public class Electron extends Element {

    // HEAD in the center and TAIL in one of surrounding positions
    private static final int ELECTRON_SIZE = 3;
    private static final int HEAD_INDEX = 1;

    private final Orientation orientation;

    public Electron(Orientation orientation) {
        this(DataNames.TEMPLATE_POINT, orientation);
    }

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
        return true;
    }

    @Override
    public void insertIntoWorld(World world) {
        insertIntoWorld(world, true);
    }

    public Orientation getOrientation() {
        return orientation;
    }

    @Override
    public Point getPosition() {
        return new Point(position.x + HEAD_INDEX, position.y + HEAD_INDEX);
    }

    @Override
    public void setPosition(Point position) {
        super.setPosition(new Point(position.x - HEAD_INDEX, position.y - HEAD_INDEX));
    }
}