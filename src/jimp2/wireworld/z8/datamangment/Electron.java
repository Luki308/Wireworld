package jimp2.wireworld.z8.datamangment;

import jimp2.wireworld.z8.wireworldlogic.State;
import jimp2.wireworld.z8.wireworldlogic.World;

import java.awt.*;


public class Electron extends Element {

    // HEAD in the center and TAIL in one of surrounding positions
    private static final int ELECTRON_SIZE = 3;
    private static final int HEAD_INDEX = 1;

    private final Orientation orientation;
    private Point tailPosition;

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
                tailPosition = new Point(HEAD_INDEX, HEAD_INDEX + 1);
                break;
            case WEST:
                tailPosition = new Point(HEAD_INDEX + 1, HEAD_INDEX);
                break;
            case EAST:
                tailPosition = new Point(HEAD_INDEX - 1, HEAD_INDEX);
                break;
            case SOUTH:
                tailPosition = new Point(HEAD_INDEX, HEAD_INDEX - 1);
                break;
            case NORTH_WEST:
                tailPosition = new Point(HEAD_INDEX + 1, HEAD_INDEX + 1);
                break;
            case NORTH_EAST:
                tailPosition = new Point(HEAD_INDEX - 1, HEAD_INDEX + 1);
                break;
            case SOUTH_WEST:
                tailPosition = new Point(HEAD_INDEX + 1, HEAD_INDEX - 1);
                break;
            case SOUTH_EAST:
                tailPosition = new Point(HEAD_INDEX - 1, HEAD_INDEX - 1);
                break;
        }

        cells[tailPosition.x][tailPosition.y].setState(State.TAIL);
    }

    @Override
    protected boolean validateSpace(World world) {
        return position.x + tailPosition.x >= 0
                && position.y + tailPosition.y >= 0
                && position.x + tailPosition.x < world.getWidth()
                && position.y + tailPosition.y < world.getHeight();
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