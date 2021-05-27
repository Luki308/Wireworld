package jimp2.wireworld.z8.wireworldlogic;

import jimp2.wireworld.z8.datamangment.Element;

import java.util.List;

public class World extends CellsContainer {

    public World(int width, int height) {
        super(width, height);
    }
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
            for(int j = 0; j < height; j++)
            {
                worldTo.cells[i][j].setState(cells[i][j].getState());
            }
        }
        return worldTo;
    }

}