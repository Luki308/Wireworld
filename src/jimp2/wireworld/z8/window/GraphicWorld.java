package jimp2.wireworld.z8.window;

import jimp2.wireworld.z8.wireworldlogic.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;


public class GraphicWorld extends JLayeredPane {

    private Canvas world;

    /**
     * 
     */
    private Canvas grid;

    /**
     * 
     */
    private int squareSize;

    /**
     * 
     */
    private int maxHeight;

    /**
     * 
     */
    private int maxWidth;


    /**
     * @param canvasManager
     */
    public GraphicWorld(MouseAdapter canvasManager) {
        // TODO implement here
    }

    /**
     * 
     */
    private void calculateSquareSize() {
        // TODO implement here
    }

    /**
     * 
     */
    private void drawGrid() {
        // TODO implement here
    }

    /**
     * @param world
     */
    public void drawSquares(World world) {
        // TODO implement here
    }

    /**
     * @param world
     */
    public void initialize(World world) {
        // TODO implement here
    }

    /**
     * @return
     */
    public Point calculateClickPosition() {
        // TODO implement here
        return null;
    }

}