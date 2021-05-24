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
        setBounds(Window.LEFT_PANEL_WIDTH, Window.UPPER_PANEL_HEIGHT, Window.RIGHT_PANELS_WIDTH, Window.LOWER_PANEL_HEIGHT);
        setBorder(Window.STANDARD_BORDER);

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