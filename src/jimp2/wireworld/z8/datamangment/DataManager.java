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
            case DataNames.Wire:
                x1 = ((Long) jsonObject.get(DataNames.x1)).intValue();
                x2 = ((Long) jsonObject.get(DataNames.x2)).intValue();
                y1 = ((Long) jsonObject.get(DataNames.y1)).intValue();
                y2 = ((Long) jsonObject.get(DataNames.y2)).intValue();
                Point posiotion1 = new Point(x1, y1);
                Point posiotion2 = new Point(x2, y2);
                return new Wire(posiotion1, posiotion2);
            case DataNames.Electron:
                x1 = ((Long) jsonObject.get(DataNames.x1)).intValue();
                y1 = ((Long) jsonObject.get(DataNames.y1)).intValue();
                point = new Point(x1, y1);
                String orientation = (String) jsonObject.get(DataNames.orientation);
                Orientation orient = null;
                for (Orientation o : Orientation.values()) {
                    if (o.toString().equals(orientation))
                        orient = o;
                }
                if (orient == null)
                    System.err.println("Wrong orientation in data files (Electron)" + point.toString());
                return new Electron(point, orient);
            case DataNames.Generator:
                    x1 = ((Long) jsonObject.get(DataNames.x1)).intValue();
                    y1 = ((Long) jsonObject.get(DataNames.y1)).intValue();
                    width = ((Long) jsonObject.get(DataNames.width).intValue();
                    height = ((Long) jsonObject.get(DataNames.height)).intValue();
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

            //iterating through array of elements
            while(i.hasNext()){
                JSONObject jsonObject = (JSONObject) i.next();
                String name = (String) jsonObject.get(DataNames.name);
                System.out.println(name);
                elements.add(interpretInputPiece(jsonObject,name));
            }
        }

        catch (FileNotFoundException e) { e.printStackTrace(); }
        catch (IOException e) { e.printStackTrace(); }
        catch (org.json.simple.parser.ParseException e) { e.printStackTrace(); }
        System.out.println(elements);
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