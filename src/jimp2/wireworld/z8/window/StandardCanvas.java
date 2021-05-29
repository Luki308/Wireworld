package jimp2.wireworld.z8.window;

import jimp2.wireworld.z8.wireworldlogic.World;

import javax.swing.*;
import java.awt.*;


public class StandardCanvas extends JComponent {
    private final static int BORDER_OFFSET = 1;

    protected int squareSize;


    public void initialize(int squareSize, World world) {
        this.squareSize = squareSize;

        int canvasWidth = squareSize * world.getWidth();
        int canvasHeight = squareSize * world.getHeight();

        Point point = new Point(BORDER_OFFSET + getCenteredPosition(GUI.RIGHT_PANELS_WIDTH, canvasWidth), BORDER_OFFSET + getCenteredPosition(GUI.LOWER_PANEL_HEIGHT,canvasHeight));
        Dimension dimension = new Dimension(canvasWidth, canvasHeight);
        Rectangle rectangle = new Rectangle(point, dimension);

        setBounds(rectangle);
    }

    private int getCenteredPosition(int maxDimLen, int actualDimLen){
        return (maxDimLen - actualDimLen) / 2;
    }
}