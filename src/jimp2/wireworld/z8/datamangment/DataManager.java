package jimp2.wireworld.z8.datamangment;


import jimp2.wireworld.z8.wireworldlogic.World;

import java.util.List;

public class DataManager {
    private String fileName;
    public FactoryOfCustomElements factory;

    private List<Element> seekForNewElements(World world) {
        // TODO implement here
        return null;
    }

    public void writeIterationToFile(int iteration, World world, List<Element> elements) {
        // TODO implement here
    }

    private Element interpretInputPiece(String input) {
        // TODO implement here
        return null;
    }

    public List<Element> readInputFile(String input) {
        // TODO implement here
        return null;
    }

    public void setFileName() {
        // TODO implement here
    }

    private void ensureFileIsJson(String fileName) {
        // TODO implement here
    }
}