package jimp2.wireworld.z8.datamangment;

import jimp2.wireworld.z8.wireworldlogic.World;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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

    private Element interpretInputPiece(JSONObject jsonObject, String name) {
        int x1,x2,y1,y2;
        int width, height;
        Point point;
        switch (name) {
            case "Wire":
                x1 = ((Long) jsonObject.get("x1")).intValue();
                x2 = ((Long) jsonObject.get("x2")).intValue();
                y1 = ((Long) jsonObject.get("y1")).intValue();
                y2 = ((Long) jsonObject.get("y2")).intValue();
                Point posiotion1 = new Point(x1, y1);
                Point posiotion2 = new Point(x2, y2);
                return new Wire(posiotion1, posiotion2);
            case "Electron":
                x1 = ((Long) jsonObject.get("x")).intValue();
                y1 = ((Long) jsonObject.get("y")).intValue();
                point = new Point(x1, y1);
                String orientation = (String) jsonObject.get("orientation");
                Orientation orient = null;
                for (Orientation o : Orientation.values()) {
                    if (o.toString().equals(orientation))
                        orient = o;
                }
                if (orient == null)
                    System.err.println("Wrong orientation in data files (Electron)" + jsonObject.toString());
                return new Electron(point, orient);
            case "Generator":
                    x1 = ((Long) jsonObject.get("x")).intValue();
                    y1 = ((Long) jsonObject.get("y")).intValue();
                    width = ((Long) jsonObject.get("width")).intValue();
                    height = ((Long) jsonObject.get("width")).intValue();
                    point = new Point(x1, y1);
                    return new Generator(point, width, height);
        }
        return null;
    }

    public WorldData readInputFile() {
        inputFile = new File("data.json");
        JSONParser parser = new JSONParser();
        int width = 0;
        int height = 0;
        List<Element> elements = new ArrayList<Element>();
        try {
            Object obj = parser.parse(new FileReader(inputFile));
            JSONObject json = (JSONObject) obj;
            height = ((Long)json.get("world_height")).intValue();
            width = ((Long)json.get("world_width")).intValue();
            System.out.println(height);
            System.out.println(width);

            JSONArray array =  (JSONArray) json.get("elements");
            Iterator i = array.iterator();

            while(i.hasNext()){
                JSONObject jsonObject = (JSONObject) i.next();
                String name = (String) jsonObject.get("name");
                System.out.println(name);
                elements.add(interpretInputPiece(jsonObject,name));
            }
        }

        catch (FileNotFoundException e) { e.printStackTrace(); }
        catch (IOException e) { e.printStackTrace(); }
        catch (org.json.simple.parser.ParseException e) { e.printStackTrace(); }
        // returning placeholder data
        return new WorldData(width, height, elements);
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