package jimp2.wireworld.z8.window;

import jimp2.wireworld.z8.wireworldlogic.World;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;


public class GraphicWorld extends JLayeredPane {

    private final WireworldCanvas worldCanvas = new WireworldCanvas();
    private final GridCanvas gridCanvas = new GridCanvas();

    private int squareSize;


    public GraphicWorld(MouseAdapter canvasManager) {
        Point point = new Point(GUI.LEFT_PANEL_WIDTH, GUI.UPPER_PANEL_HEIGHT);
        Dimension dimension = new Dimension(GUI.RIGHT_PANELS_WIDTH, GUI.LOWER_PANEL_HEIGHT);
        Rectangle rectangle = new Rectangle(point, dimension);
        setBounds(rectangle);

        setBorder(new MatteBorder(2, 2, 0, 0, Color.BLACK));

        add(worldCanvas);
        add(gridCanvas);

        setLayer(worldCanvas, 0);
        setLayer(gridCanvas, 1);

        initialize(new World(10, 10));
    }

    // proper square size equals minimum value between each dimension maximum GUI length, divided by it's corresponding logic length (rounded to an integer)
    private void calculateSquareSize(World world) {
        int potentialSquareWidth = GUI.RIGHT_PANELS_WIDTH / world.getWidth();
        int potentialSquareHeight = GUI.LOWER_PANEL_HEIGHT / world.getHeight();

        squareSize = Math.min(potentialSquareWidth, potentialSquareHeight);
    }

    /* probably useless now
    private void drawGrid(World world) {
        // TODO implement here
    }
    */

    public void drawGivenWorld(World world) {
        worldCanvas.setWorld(world);
    }

    public void initialize(World world) {
        calculateSquareSize(world);

        gridCanvas.initialize(squareSize, world);
        worldCanvas.initialize(squareSize, world);

        drawGivenWorld(world);
    }

    // for the editor (in the future)
    public Point calculateClickPosition() {
        // TODO implement here
        return null;
    }
}