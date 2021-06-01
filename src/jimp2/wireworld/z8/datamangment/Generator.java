package jimp2.wireworld.z8.datamangment;


import jimp2.wireworld.z8.wireworldlogic.CellsContainer;
import jimp2.wireworld.z8.wireworldlogic.State;
import jimp2.wireworld.z8.wireworldlogic.World;

import java.awt.*;

public class Generator extends Element {

    private final int width;
    private final int height;

    public Generator(Point position, int width, int height) {
        this.position = position;
        this.width = width;
        this.height = height;
        name = "Generator";
        cellsContainer = new CellsContainer(width, height);
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                if (row == 0 || row == height - 1 || column == 0 || column == width - 1)
                    if (!((row == column) || ((row == 0) && (column == width - 1)) || (row == height - 1 && column == 0)))
                        cellsContainer.cells[row][column].setState(State.CONDUCTOR);
            }
        }
    }

    @Override
    public void insertIntoWorld(World world) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                world.cells[i + position.x][j + position.y].setState(cellsContainer.cells[i][j].getState());
            }
        }

    }
}