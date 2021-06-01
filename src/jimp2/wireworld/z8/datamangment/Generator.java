package jimp2.wireworld.z8.datamangment;


import jimp2.wireworld.z8.wireworldlogic.World;

import java.awt.*;

public class Generator extends Element {

    private int width;
    private int height;
    public Generator(Point position, int width, int height) {
        this.position=position;
        this.width = width;
        this.height = height;
    }

    @Override
    public void insertIntoWorld(World world) {
        // TODO implement here
    }
}