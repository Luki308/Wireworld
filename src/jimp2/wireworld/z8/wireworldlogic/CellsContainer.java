package jimp2.wireworld.z8.wireworldlogic;


public class CellsContainer {

    protected int height;
    protected int width;

    public Cell cells[][];


    public CellsContainer(int width, int height) {
        // TODO implement here
        cells = new Cell[height][width];
    }

    /**
     * 
     */
    private void makeEmpty() {
        // TODO implement here
    }

    /**
     * @return
     */
    public int getHeight() {
        // TODO implement here
        return 0;
    }

    /**
     * @return
     */
    public int getWidth() {
        // TODO implement here
        return 0;
    }

}