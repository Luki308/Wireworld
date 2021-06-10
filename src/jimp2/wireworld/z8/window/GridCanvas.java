package jimp2.wireworld.z8.window;

import java.awt.*;


public class GridCanvas extends StandardCanvas {

    public GridCanvas(){
        setBorder(GUI.THIN_BORDER);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // draw vertical stripes
        g.setColor(Color.BLACK);
        for(int column = 0; column < getWidth() / squareSize; column++) {
            g.drawLine(squareSize * column,0,squareSize * column, getHeight());
        }

        // draw horizontal stripes
        for(int row = 0; row < getHeight() / squareSize; row++) {
            g.drawLine(0,squareSize * row, getWidth(),squareSize * row);
        }
    }
}