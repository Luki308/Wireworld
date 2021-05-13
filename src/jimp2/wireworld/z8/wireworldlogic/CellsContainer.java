package jimp2.wireworld.z8.wireworldlogic;


import java.awt.*;

public class CellsContainer {

    protected int height;
    protected int width;

    public Cell[][] cells;


    public CellsContainer(int width, int height) {
        this.height = height;
        this.width = width;
        this.cells = new Cell[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Point p = new Point(2,3);
                cells[i][j]= new Cell(p,State.EMPTY);
                cells[i][j].setState(State.EMPTY);
            }
        }
    }

    private void makeEmpty() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
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