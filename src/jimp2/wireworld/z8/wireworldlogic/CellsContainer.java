package jimp2.wireworld.z8.wireworldlogic;


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

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}