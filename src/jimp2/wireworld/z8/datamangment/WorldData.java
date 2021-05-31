package jimp2.wireworld.z8.datamangment;

import java.util.List;


public class WorldData {
    public final int width;
    public final int height;
    public final List<Element> elements;


    public WorldData(int width, int height, List<Element> elements){
        this.width = width;
        this.height = height;
        this.elements = elements;
    }
}
