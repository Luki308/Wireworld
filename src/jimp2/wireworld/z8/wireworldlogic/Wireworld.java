package jimp2.wireworld.z8.wireworldlogic;


import jimp2.wireworld.z8.datamangment.Wire;

public class Wireworld {

    private World world;
    private World newWorld;
    private Rules rules;

    public Wireworld(int width, int height)
    {
        this.world = new World(width,height);
        this.rules = new Rules();
    }

    public void update() { //zmiana World na void
        newWorld = new World(world.width, world.height);
        newWorld = world.copyCells();
        int width = newWorld.width;
        int height = newWorld.height;
        for(int i = 0; i < width; i++)
        {
            for(int j = 0; j < height; j++)
            {
                rules.apply(newWorld.cells[i][j], world);
            }
        }
        world = newWorld.copyCells();
    }

    public World getWorld() {
        return this.world;
    }

}