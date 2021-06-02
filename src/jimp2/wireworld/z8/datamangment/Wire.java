package jimp2.wireworld.z8.datamangment;


import jimp2.wireworld.z8.wireworldlogic.State;
import jimp2.wireworld.z8.wireworldlogic.World;

import java.awt.*;

public class Wire extends Element {

    // this field will be useful in THE EDITOR
    private Point position2;

    public Wire(Point position1, Point position2) {
        super(position1, calculateElementSize(position1, position2, true), calculateElementSize(position1, position2, false));
        name = "Wire";

        this.position2 = position2;

        int steps = Math.max(width, height);


        Point point1 = new Point(position1);
        Point point2 = new Point(position2);
        int widthSteps = decreaseAbsoluteValue(calculateVector(position1, position2, true), 1);
        int heightSteps = decreaseAbsoluteValue(calculateVector(position1, position2, false), 1);

        while (steps-- > 0) {

            cells[point1.x][point1.y].setState(State.CONDUCTOR);
            if (steps-- > 0) {
                cells[point2.x][point2.y].setState(State.CONDUCTOR);
            }

            if (widthSteps >= heightSteps) {
                point1.x += Math.signum(widthSteps);
                point2.x -= Math.signum(widthSteps);
                widthSteps = decreaseAbsoluteValue(widthSteps, 2);
            } else {
                point1.y += Math.signum(heightSteps);
                point2.y -= Math.signum(heightSteps);
                heightSteps = decreaseAbsoluteValue(heightSteps, 2);
            }
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

    @Override
    public void eraseFromWorld(World world) {
        // TODO implement here
    }


    private static int calculateElementSize(Point position1, Point position2, boolean returnX) {
        return 1 + Math.abs(calculateVector(position1, position2, returnX));
    }

    private static int calculateVector(Point position1, Point position2, boolean returnX) {
        return returnX ? position2.x - position1.x : position2.y - position1.y;
    }

    private static int decreaseAbsoluteValue(int value, int decreasedNumber) {
        return value - (int) Math.signum(value) * decreasedNumber;
    }

}