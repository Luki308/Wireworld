package jimp2.wireworld.z8.wireworldlogic;

import jimp2.wireworld.z8.datamangment.Element;
import jimp2.wireworld.z8.datamangment.WorldData;

import java.util.List;


public class Wireworld {

    private final Rules rules = new Rules();

    private World world;
    private World newWorld;


    public void initializeWorld(WorldData worldData) {
        world = new World(worldData.width, worldData.height);
        newWorld = new World(worldData.width, worldData.height);
/*
        // placeholder world
        for (int i = 0; i < worldData.width; i++) {
            for (int j = 0; j < worldData.height; j++) {
                getWorld().cells[i][j].setState(State.CONDUCTOR);
            }
        }
        getWorld().cells[0][0].setState(State.HEAD);
*/
        buildTheWorld(worldData.elements,world);
        newWorld.copyCells(world);
    }

    public void update() {
        int width = newWorld.width;
        int height = newWorld.height;

        //for each cell apply rules of Wireworld
        for(int i = 0; i < width; i++)
        {
            for(int j = 0; j < height; j++)
            {
                rules.apply(i, j, newWorld, world);
            }
        }

        // copy changes that were made during iteration to the actual world
        world.copyCells(newWorld);
    }

    public World getWorld() {
        return world;
    }

    private void buildTheWorld(List<Element> elements, World world) {
        for(Element e : elements) {
            if (e != null) {
                e.insertIntoWorld(world);
            }
        }
    }
}