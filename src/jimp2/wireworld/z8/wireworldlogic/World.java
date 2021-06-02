package jimp2.wireworld.z8.wireworldlogic;

import jimp2.wireworld.z8.datamangment.Element;

import java.util.List;


public class World extends CellsContainer {

    public World(int width, int height) {
        super(width, height);
    }

    public void buildTheWorld(List<Element> elements) {
        // TODO implement here
    }

    public int countNeighbouringHeadsOfCellAtPosition(int column, int row) {
        int numberOfHeads = 0;

        for (int i = column - 1; i <= column + 1; i++)
            for (int j = row - 1; j <= row + 1; j++) {
                if (i >= 0 && i < width && j >= 0 && j < height && !(i == column && j == row)) //In world borders and its not cell itself
                    if (cells[i][j].getState() == State.HEAD)
                        numberOfHeads++;
            }
        return numberOfHeads;
    }
}