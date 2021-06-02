package jimp2.wireworld.z8.datamangment;

import jimp2.wireworld.z8.wireworldlogic.State;

import java.awt.*;


public class Generator extends Element {

    // these fields will be useful in THE EDITOR
    private final int width;
    private final int height;

    public Generator(Point position, int width, int height) {
        super(position, width, height);
        name = "Generator";

        this.width = width;
        this.height = height;

        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                if (row == 0 || row == height - 1 || column == 0 || column == width - 1)
                    if (!((row == column) || ((row == 0) && (column == width - 1)) || (row == height - 1 && column == 0)))
                        cells[column][row].setState(State.CONDUCTOR);
            }
        }
    }
}