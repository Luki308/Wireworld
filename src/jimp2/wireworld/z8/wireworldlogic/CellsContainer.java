package jimp2.wireworld.z8.wireworldlogic;


import java.awt.*;

public class CellsContainer {

    protected int height;
    protected int width;

    public Cell[][] cells;


    public CellsContainer(int width, int height) {
        this.height = height;
        this.width = width;
        this.cells = new Cell[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Point p = new Point(i,j);
                cells[i][j]= new Cell(p,State.EMPTY);
                cells[i][j].setState(State.EMPTY);
            }
        }
    }

    private void makeEmpty() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                cells[i][j].setState(State.EMPTY);
            }
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}