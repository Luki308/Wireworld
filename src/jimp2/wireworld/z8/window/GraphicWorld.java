package jimp2.wireworld.z8.window;

import jimp2.wireworld.z8.wireworldlogic.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;


public class GraphicWorld extends JLayeredPane {

    private Canvas world;
    private Canvas grid;

    private int maxHeight;
    private int maxWidth;

    private int squareSize;


    public GraphicWorld(MouseAdapter canvasManager) {
        setBounds(Window.screenWidth / 4, Window.screenHeight / 4, Window.screenWidth * 3 / 4, Window.screenHeight - (Window.screenHeight / 4));
        setBorder(Window.border);

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