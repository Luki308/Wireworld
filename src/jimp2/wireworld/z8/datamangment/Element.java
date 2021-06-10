package jimp2.wireworld.z8.datamangment;

import jimp2.wireworld.z8.wireworldlogic.CellsContainer;
import jimp2.wireworld.z8.wireworldlogic.State;
import jimp2.wireworld.z8.wireworldlogic.World;

import java.awt.*;


public abstract class Element extends CellsContainer{

    protected String name;
    protected Point position;


    public Element(Point position, int width, int height) {
        super(width, height);

        this.position = position;
    }

    public Element(Point position, Dimension dimension) {
        this(position, dimension.width, dimension.height);
    }

    protected boolean validateSpace(World world)
    {
        return position.x >= 0 && position.y >= 0 && position.x + (width - 1) < world.getWidth() && position.y + (height - 1) < world.getHeight();
    }

    public void insertIntoWorld(World world) {
        if (validateSpace(world)) {
            State state;
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    state = cells[i][j].getState();
                    if (state != State.EMPTY) {
                        world.cells[i + position.x][j + position.y].setState(state);
                    }
                }
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setPosition(Point worldPosition) {
        position = worldPosition;
    }

    public Point getPosition() {
        return position;
    }
}