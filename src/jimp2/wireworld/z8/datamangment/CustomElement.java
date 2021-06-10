package jimp2.wireworld.z8.datamangment;

import jimp2.wireworld.z8.wireworldlogic.World;

import java.awt.*;


public class CustomElement extends Element {

    private Point inputConnectorPoint;
    private Orientation orientation = Orientation.NORTH;


    // CustomElement template constructor
    public CustomElement(int width, int height, String name, Point inConnectorPoint) {
        // point doesn't matter here
        super(DataNames.TEMPLATE_POINT, width, height);
        this.name = name;
        this.inputConnectorPoint = inConnectorPoint;
    }

    public CustomElement(Orientation orientation, CustomElement templateCustomElement) {
        this(DataNames.TEMPLATE_POINT, orientation, templateCustomElement);
    }

    // standard CustomElement constructor
    public CustomElement(Point position, Orientation orientation, CustomElement templateCustomElement) {
        super(position, determineDimension(orientation, templateCustomElement));
        name = templateCustomElement.getName();
        inputConnectorPoint = templateCustomElement.getInputConnectorPoint();

        this.orientation = orientation;

        copyCells(templateCustomElement, orientation);
        setPosition(position);
    }

    private static Dimension determineDimension(Orientation orient, CustomElement templateCustomElement) {

        return
                isElementVertical(orient)
                        ? new Dimension(templateCustomElement.getWidth(), templateCustomElement.getHeight())
                        : new Dimension(templateCustomElement.getHeight(), templateCustomElement.getWidth());
    }

    private static boolean isElementVertical(Orientation orient) {
        return orient == Orientation.NORTH || orient == Orientation.SOUTH;
    }

    public Point getInputConnectorPoint() {
        return inputConnectorPoint;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    @Override
    public Point getPosition() {
        return new Point(position.x + inputConnectorPoint.x, position.y + inputConnectorPoint.y);
    }

    @Override
    public void setPosition(Point position) {
        super.setPosition(new Point(position.x - inputConnectorPoint.x, position.y - inputConnectorPoint.y));
    }
}