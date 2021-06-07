package jimp2.wireworld.z8.datamangment;

import jimp2.wireworld.z8.wireworldlogic.State;
import jimp2.wireworld.z8.wireworldlogic.World;

import java.awt.*;


public class Wire extends Element {

    // this field will be useful in THE EDITOR
    private Point position2;

    public Wire(Point position1, Point position2) {
        super(calculatePosition(position1, position2), calculateElementSize(position1, position2, true), calculateElementSize(position1, position2, false));
        name = DataNames.Wire;

        this.position2 = position2;

        calculateWirePath();
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
        return returnX ? calculateVector(position1, position2).x : calculateVector(position1, position2).y;
    }

    private static Point calculateVector(Point position1, Point position2) {
        return new Point(position2.x - position1.x, position2.y - position1.y);
    }

    private static int decreaseAbsoluteValue(int value, int decreasedNumber) {
        return value - (int) Math.signum(value) * decreasedNumber;
    }

    private static Point calculatePosition(Point pos1, Point pos2) {
        Point returnedPoint = new Point();
        Point vector = calculateVector(pos1, pos2);

        returnedPoint.x = vector.x >= 0 ? pos1.x : pos2.x;
        returnedPoint.y = vector.y >= 0 ? pos1.y : pos2.y;

        return returnedPoint;
    }

    private void calculateWirePath() {
        // determine number of needed steps
        Point vector = calculateVector(position, position2);
        int steps = Math.max(width, height);
        int widthSteps = decreaseAbsoluteValue(vector.x, 1);
        int heightSteps = decreaseAbsoluteValue(vector.y, 1);

        // set coordinates of relative position points
        Point point1 = new Point();
        Point point2 = new Point();
        Point currentPoint;

        if (vector.x >= 0) {
            point1.x = 0;
            point2.x = width - 1;
        }
        else {
            point1.x = width - 1;
            point2.x = 0;
        }

        if (vector.y >= 0) {
            point1.y = 0;
            point2.y = height - 1;
        }
        else {
            point1.y = height - 1;
            point2.y = 0;
        }

        boolean isStepEven;
        int orderModifier;

        // set proper cells to conductor, to represent actual wire
        while (steps-- > 0) {
            isStepEven = steps % 2 == 0;
            orderModifier = isStepEven ? 1 : -1;
            currentPoint = isStepEven ? point1 : point2;

            // set one cell
            cells[currentPoint.x][currentPoint.y].setState(State.CONDUCTOR);

            // calculate next cell position
            if (Math.abs(widthSteps) >= Math.abs(heightSteps)) {
                currentPoint.x += orderModifier * Math.signum(widthSteps);
                widthSteps = decreaseAbsoluteValue(widthSteps, 1);
            }

            if (Math.abs(widthSteps) < Math.abs(heightSteps)){
                currentPoint.y += orderModifier * Math.signum(heightSteps);
                heightSteps = decreaseAbsoluteValue(heightSteps, 1);
            }
        }
    }
}