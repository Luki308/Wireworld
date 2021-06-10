package jimp2.wireworld.z8.datamangment;

import jimp2.wireworld.z8.wireworldlogic.State;

import java.awt.*;


public class Generator extends Element {

    private final int width;
    private final int height;


    public Generator(Dimension dimension) {
        this(DataNames.TEMPLATE_POINT, dimension);
    }

    public Generator(Point position, Dimension dimension) {
        super(position, dimension.width, dimension.height);
        name = DataNames.Generator;

        this.width = dimension.width;
        this.height = dimension.height;

        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                if (areCoordinatesBorder(column, row))
                    if (areCoordinatesNotCorner(column, row))
                        cells[column][row].setState(State.CONDUCTOR);
            }
        }
    }

    private boolean areCoordinatesBorder(int column, int row) {
        return row == 0 || row == height - 1 || column == 0 || column == width - 1;
    }

    private boolean areCoordinatesNotCorner(int column, int row) {
        return !(((row == 0) && (column == width - 1)) || (row == height - 1 && column == 0) || ((row == 0) && (column == 0)) || (row == height - 1 && column == width - 1));
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
}