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

    /**
     * @param world
     */
    public void copyCells(World world) {
        // TODO implement here
    }

}