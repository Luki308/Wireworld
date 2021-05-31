package jimp2.wireworld.z8.datamangment;

import jimp2.wireworld.z8.wireworldlogic.World;

import java.io.File;
import java.util.List;


public class DataManager {
    private File inputFile = null;
    public FactoryOfCustomElements factory = new FactoryOfCustomElements();

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

    public WorldData readInputFile() {
        // TODO implement here
        // returning placeholder data
        return new WorldData(10, 10, null);
    }

    public void setInputFile() {
        // TODO implement here
        // JFileChooser should be used there to set inputFile field
        // then ensureFileIsJson should be called
        // if it's not, set null
    }

    private boolean isFileJson(String fileName) {
        // TODO implement here
        return true;
    }
}