package jimp2.wireworld.z8.datamangment;


import jimp2.wireworld.z8.wireworldlogic.World;

import java.awt.*;

public class Electron extends Element {


    private Orientation orientation;

    public Electron(Point position, Orientation orientation) {
        this.position=position;
        this.orientation=orientation;
    }

    @Override
    public boolean validateSpace(World world) {
        // TODO implement here
        return false;
    }

    @Override
    public void insertIntoWorld(World world) {
        // TODO implement here
    }

    @Override
    public void eraseFromWorld(World world) {
        // TODO implement here
    }
}