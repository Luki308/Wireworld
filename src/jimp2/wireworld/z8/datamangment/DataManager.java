package jimp2.wireworld.z8.datamangment;

import jimp2.wireworld.z8.wireworldlogic.Cell;
import jimp2.wireworld.z8.wireworldlogic.State;
import jimp2.wireworld.z8.wireworldlogic.World;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class DataManager {
    private File inputFile = new File(DataNames.EXAMPLE_FILE_PATH);
    public FactoryOfCustomElements factory = new FactoryOfCustomElements();

    private List<Element> seekForNewElements(World startingWorld, World currentWorld) {
        // TODO implement here
        List<Element> newElements = new ArrayList<>();
        Cell currentCell;
        for(int column = 0; column < currentWorld.getWidth(); column++)
            for(int row = 0; row < currentWorld.getHeight(); row++)
                if( !currentWorld.cells[column][row].getState().equals(State.EMPTY))
                    if( currentWorld.cells[column][row].getState().equals(startingWorld.cells[column][row].getState()))
                        currentWorld.cells[column][row].setState(State.EMPTY);
        for(int column = 0; column < currentWorld.getWidth(); column++) {
            for (int row = 0; row < currentWorld.getHeight(); row++) {
                currentCell = currentWorld.cells[column][row];

                if (currentCell.getState().equals(State.CONDUCTOR))
                    newElements.add(new CellElement(new Point(column, row), State.CONDUCTOR));
                else if (currentCell.getState().equals(State.TAIL))
                    newElements.add(new CellElement(new Point(column, row), State.TAIL));
                else if(currentCell.getState().equals(State.HEAD))
                    newElements.add(new CellElement(new Point(column, row), State.HEAD));
            }
        }
        return newElements;
    }

    public void writeIterationToFile(int iteration, World startingWorld, World currentWorld, List<Element> elements) {
        List<Element> newElements = new ArrayList<>();
        for(Element element : elements) {

            //przepisujemy wszystko oprócz electronów
            //Cellelementy także przepisujemy jedynie gdy mają stan Empty albo Conductor
            // nie przepisujemy tylko tych poniżej
            if (!element.name.equals(DataNames.Electron)) {
                if (!(element.name.equals(DataNames.CellElement) && (element.cells[0][0].getState().equals(State.TAIL) || element.cells[0][0].getState().equals(State.HEAD)))) {
                    newElements.add(element);
                }
            }
        }
        //następnie SeekForNewElements, żeby znaleźć te komórki, które się zmieniły
        //skuteczniej byłoby nadpisywać tylko w CustomElementach, które posiadają electrony w sobie z definicji
        List<Element> changedElements;
        changedElements = seekForNewElements(startingWorld, currentWorld);
        newElements.addAll(changedElements);
    }

    private Element interpretInputPiece(JSONObject jsonObject, String name) {
        Point point, point2;
        String enumName;
        Orientation orient;
        State state;
        Dimension dimension;

        switch (name) {
            case DataNames.CellElement:
                point = readElementPosition(jsonObject);
                enumName = readElementName(jsonObject, DataNames.state);
                state = State.getState(enumName);
                if (state == null) break;
                return new CellElement(point, state);
            case DataNames.Wire:
                point = readElementPosition(jsonObject);
                point2 = readElementPosition(jsonObject, true);
                return new Wire(point, point2);
            case DataNames.Electron:
                point = readElementPosition(jsonObject);
                enumName = readElementName(jsonObject, DataNames.orientation);
                orient = Orientation.getOrientation(enumName, false);
                if (orient == null) break;
                return new Electron(point, orient);
            case DataNames.Generator:
                point = readElementPosition(jsonObject);
                dimension = readDimension(jsonObject);
                return new Generator(point, dimension);
            default:    // custom element
                if (factory.getAvailableCustomElements().containsKey(name)) {
                    point = readElementPosition(jsonObject);
                    enumName = readElementName(jsonObject, DataNames.orientation);
                    orient = Orientation.getOrientation(enumName, true);
                    if (orient == null) break;
                    return new CustomElement(point, orient, factory.getAvailableCustomElements().get(name));
                }
        }
        return null;
    }

    public WorldData readInputFile() {
        if (this.inputFile != null) {
            JSONParser parser = new JSONParser();
            int width = 0;
            int height = 0;
            List<Element> elements = new ArrayList<>();
            try {
                Object obj = parser.parse(new FileReader(inputFile));
                JSONObject json = (JSONObject) obj;
                height = ((Long) json.get("world_height")).intValue();
                width = ((Long) json.get("world_width")).intValue();

                JSONArray array = (JSONArray) json.get("elements");
                Iterator i = array.iterator();

                //iterating through array of elements
                while (i.hasNext()) {
                    JSONObject jsonObject = (JSONObject) i.next();
                    String name = (String) jsonObject.get(DataNames.name);
                    elements.add(interpretInputPiece(jsonObject, name));
                }
            } catch (ParseException | IOException e) {
                e.printStackTrace();
            }

            return new WorldData(width, height, elements);

        } else {
            JOptionPane.showMessageDialog(null, "Please, choose proper .json input file first.");
            return null;
        }
    }

    public void setInputFile() {
        JFileChooser jFileChooser = new JFileChooser(System.getProperty("user.dir"));
        jFileChooser.setDialogTitle("Select input .json file");
        jFileChooser.showOpenDialog(null);
        File inputFile = jFileChooser.getSelectedFile();

        this.inputFile = isFileJson(inputFile) ? inputFile : null;
    }

    private static boolean isFileJson(File jsonFile) {
        if (jsonFile != null) {
            String input = jsonFile.toString();
            return input.endsWith(".json");
        }
        return false;
    }

    private static Point readElementPosition(JSONObject jsonObject, boolean isPointSecond) {
        int x, y;

        if (!isPointSecond) {
            x = ((Long) jsonObject.get(DataNames.x1)).intValue();
            y = ((Long) jsonObject.get(DataNames.y1)).intValue();
        } else {
            x = ((Long) jsonObject.get(DataNames.x2)).intValue();
            y = ((Long) jsonObject.get(DataNames.y2)).intValue();
        }

        return new Point(x, y);
    }

    private static Point readElementPosition(JSONObject jsonObject) {
        return readElementPosition(jsonObject, false);
    }

    private static String readElementName(JSONObject jsonObject, String name) {
        return (String) jsonObject.get(name);
    }

    private static Dimension readDimension(JSONObject jsonObject) {
        int width = ((Long) jsonObject.get(DataNames.width)).intValue();
        int height = ((Long) jsonObject.get(DataNames.height)).intValue();
        return new Dimension(width, height);
    }
}