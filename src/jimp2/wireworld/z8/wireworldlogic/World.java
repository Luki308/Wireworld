package jimp2.wireworld.z8.wireworldlogic;

import jimp2.wireworld.z8.datamangment.Element;

import java.util.List;

public class World extends CellsContainer {

    public World(int width, int height) {
        super(width, height);
    }

    /*public CellsContainer cellsContainer;

        public World(int width, int height) {
            this.cellsContainer= new CellsContainer(width,height);
        }
        */
    /*
     * @param width 
     * @param height 
     * @param elements
     */
    public void buildTheWorld(int width, int height, List<Element> elements) {
        // TODO implement here
    }

    public World copyCells() {
        World worldTo = new World(width,height);

        for (int i = 0; i < width; i++)
        {
            System.arraycopy(cells[i], 0, worldTo.cells[i], 0, height);
        }
        return worldTo;
    }

}