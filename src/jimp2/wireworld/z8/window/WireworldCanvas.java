package jimp2.wireworld.z8.window;

import jimp2.wireworld.z8.wireworldlogic.World;

import java.awt.*;


public class WireworldCanvas extends StandardCanvas {

    private World world;

    @Override
    public void initialize(int squareSize, World world) {
        super.initialize(squareSize, world);
        setWorld(world);
        repaint();
    }

    public void setWorld(World newWorld) {
        world = newWorld;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // guard statement just in case of some weird processing order in java
        if(world == null) return;

        // fill every cell depending on it's logic state
        for(int column = 0; column < world.getWidth(); column++) {
            for(int row = 0; row < world.getHeight(); row++) {
                switch (world.cells[column][row].getState()) {
                    case EMPTY:
                        g.setColor(Color.WHITE);
                        break;
                    case CONDUCTOR:
                        g.setColor(Color.ORANGE);
                        break;
                    case HEAD:
                        g.setColor(Color.BLUE);
                        break;
                    case TAIL:
                        g.setColor(Color.RED);
                        break;
                }
                g.fillRect(squareSize * column, squareSize * row, squareSize, squareSize);
            }
        }
    }
}