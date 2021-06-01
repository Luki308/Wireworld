package jimp2.wireworld.z8.datamangment;

import jimp2.wireworld.z8.wireworldlogic.World;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
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
        inputFile = new File("data.json");
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(inputFile));
            JSONObject json = (JSONObject) obj;
            Long height = (Long) json.get("world_height");
            Long width = (Long) json.get("world_width");
            System.out.println(height);
            System.out.println(width);
            JSONArray array =  (JSONArray) json.get("elements");
            Iterator i = array.iterator();

            while(i.hasNext()){
                JSONObject jsonObject = (JSONObject) i.next();
                String name = (String) jsonObject.get("name");
                System.out.println(name);
            }
        }

        catch (FileNotFoundException e) { e.printStackTrace(); }
        catch (IOException e) { e.printStackTrace(); }
        catch (org.json.simple.parser.ParseException e) { e.printStackTrace(); }
        // returning placeholder data
        return new WorldData(10, 10, null);
    }

    public void setInputFile() {
        // JFileChooser should be used there to set inputFile field
        // then ensureFileIsJson should be called
        // if it's not, set null
    }

    private boolean isFileJson(String fileName) {
        // TODO implement here
        return true;
    }
}