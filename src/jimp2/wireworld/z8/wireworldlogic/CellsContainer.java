package jimp2.wireworld.z8.wireworldlogic;


import jimp2.wireworld.z8.datamangment.Orientation;

public class CellsContainer {

    protected int height;
    protected int width;

    public Cell[][] cells;


    public CellsContainer(int width, int height) {
        this.height = height;
        this.width = width;

        this.cells = new Cell[width][height];

        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
                cells[i][j]= new Cell(State.EMPTY);
    }

    public void copyCells(CellsContainer from) {
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
                cells[i][j].setState(from.cells[i][j].getState());
    }

    public void copyCells(CellsContainer from, Orientation orientation) {

        switch (orientation)
        {
            case NORTH:
                copyCells(from);
                break;
            case EAST:
                for (int i = 0; i < width; i++)
                    for (int j = 0 ; j < height; j++)
                        cells[i][j].setState(from.cells[j][i].getState());
                break;
            case SOUTH:
                for(int i = 0; i < width; i++)
                    for(int j = 0; j < height; j++)
                        cells[i][j].setState(from.cells[width-1-i][height-1-j].getState());
                break;
            case WEST:
                for (int i = 0; i < width; i++)
                    for (int j = 0 ; j < height; j++)
                        cells[i][j].setState(from.cells[height-1-j][width-1-i].getState());
                break;
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}