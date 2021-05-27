package jimp2.wireworld.z8.wireworldlogic;


import jimp2.wireworld.z8.datamangment.Wire;

public class Wireworld {

    private World world;
    private World newWorld;
    private Rules rules;

    public Wireworld(int width, int height)
    {
        this.world = new World(width,height);
        this.newWorld = new World(width,height);
        this.rules = new Rules();
    }

    public void update() { //zmiana World na void (względem specyfikacji)
        newWorld = world.copyCells();
        int width = newWorld.width;
        int height = newWorld.height;

        //for each cell apply rules of WireWorld
        for(int i = 0; i < width; i++)
        {
            for(int j = 0; j < height; j++)
            {
                if(newWorld.cells[i][j].getState()==State.CONDUCTOR)
                    rules.apply(newWorld.cells[i][j],world.cells[i][j].countNeighbouringHeads(world));
                else if(newWorld.cells[i][j].getState()!=State.EMPTY)
                    rules.apply(newWorld.cells[i][j]);
            }
        }
        world = newWorld.copyCells();           //copy changes that were made to the world
    }

    public World getWorld() {
        return this.world;
    }

}