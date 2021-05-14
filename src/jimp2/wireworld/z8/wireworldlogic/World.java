package jimp2.wireworld.z8.wireworldlogic;

import jimp2.wireworld.z8.datamangment.Element;

import java.util.List;

public class World {

    public CellsContainer cellsContainer;

    public World(int width, int height) {
        this.cellsContainer= new CellsContainer(width,height);
    }

    /*
     * @param width 
     * @param height 
     * @param elements
     */
    public void buildTheWorld(int width, int height, List<Element> elements) {
        // TODO implement here
    }

    public World copyCells(World world) {
        int width = world.cellsContainer.width;
        int height = world.cellsContainer.height;

        World worldTo = new World(width,height);
        for (int i = 0; i <= width; i++) {
            for (int j = 0; j <= height; j++) {
                worldTo.cellsContainer.cells[i][j] = world.cellsContainer.cells[i][j];
            }
        }
        return worldTo;
    }

}