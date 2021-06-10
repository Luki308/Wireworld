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
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataManager {
    private File inputFile = new File(DataNames.EXAMPLE_FILE_PATH);
    public FactoryOfCustomElements factory = new FactoryOfCustomElements();

    private boolean isNOTElectron (Element element){
        return !element.name.equals(DataNames.Electron);
    }
    private boolean isNOTCellContainerTailOrHead (Element element){
        return (!(element.name.equals(DataNames.CellElement) && (element.cells[0][0].getState().equals(State.TAIL) || element.cells[0][0].getState().equals(State.HEAD))));
    }
    private boolean cellIsNOTEmpty (Cell currentCell){
        return !currentCell.getState().equals(State.EMPTY);
    }

    private List<Element> seekForNewElements(World startingWorld, World currentWorld) {
        List<Element> newElements = new ArrayList<>();
        World copyCurrentWorld = new World(currentWorld.getWidth(),currentWorld.getHeight());
        copyCurrentWorld.copyCells(currentWorld);
        Cell currentCell;

        //for each cell we're checking if cell's state has changed
        for(int column = 0; column < currentWorld.getWidth(); column++) {
            for (int row = 0; row < currentWorld.getHeight(); row++) {

                currentCell = copyCurrentWorld.cells[column][row];

                //changing cells that has been not changed to empty
                if (cellIsNOTEmpty (currentCell))
                    if (currentCell.getState().equals(startingWorld.cells[column][row].getState()))
                        currentCell.setState(State.EMPTY);

                //Creating cells that changed as new Elements
                else if (currentCell.getState().equals(State.CONDUCTOR))
                    newElements.add(new CellElement(new Point(column, row), State.CONDUCTOR));
                else if (currentCell.getState().equals(State.TAIL))
                    newElements.add(new CellElement(new Point(column, row), State.TAIL));
                else if(currentCell.getState().equals(State.HEAD))
                    newElements.add(new CellElement(new Point(column, row), State.HEAD));
            }
        }

        return newElements;
    }

    public void writeIterationToFile(int iteration, World startingWorld, World currentWorld, List<Element> elements, File savingFile) {
        List<Element> newElements = new ArrayList<>();
        for(Element element : elements) {

            //copying elements that dont change
            if (isNOTElectron(element)) {
                if (isNOTCellContainerTailOrHead(element)) {
                    newElements.add(element);
                }
            }
        }
        //looking for cells that changed
        List<Element> changedElements;
        changedElements = seekForNewElements(startingWorld, currentWorld);
        newElements.addAll(changedElements);

        if(savingFile == null)
            savingFile = new File("Iterations/iteration"+iteration);


        //saving world parameters
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("world_width",currentWorld.getWidth());
        jsonObject.put("world_height",currentWorld.getHeight());


        //saving to json file elements
        JSONArray jsonArray =  new JSONArray();
        JSONObject jsonObject2;
        for(Element element : newElements) {
            jsonObject2 = new JSONObject();
            switch (element.name){
                case DataNames.CellElement:
                    CellElement cellElement = (CellElement) element;
                    jsonObject2.put(DataNames.name, DataNames.CellElement);
                    jsonObject2.put(DataNames.x1, cellElement.getPosition().x);
                    jsonObject2.put(DataNames.y1, cellElement.getPosition().y);
                    jsonObject2.put(DataNames.state, cellElement.getState().toString());
                    jsonArray.add(jsonObject2);
                    break;
                case DataNames.Wire:
                    Wire wire = (Wire) element;
                    jsonObject2.put(DataNames.name, DataNames.Wire);
                    jsonObject2.put(DataNames.x1, wire.getPosition().x);
                    jsonObject2.put(DataNames.y1, wire.getPosition().y);
                    jsonObject2.put(DataNames.x2, wire.getPosition2().x);
                    jsonObject2.put(DataNames.y2, wire.getPosition2().y);
                    jsonArray.add(jsonObject2);
                    break;
                case DataNames.Electron:
                    Electron electron = (Electron) element;
                    jsonObject2.put(DataNames.name, DataNames.Electron);
                    jsonObject2.put(DataNames.x1, electron.getPosition().x);
                    jsonObject2.put(DataNames.y1, electron.getPosition().y);
                    jsonArray.add(jsonObject2);
                    break;
                case DataNames.Generator:
                    Generator generator = (Generator) element;
                    jsonObject2.put(DataNames.name, DataNames.Generator);
                    jsonObject2.put(DataNames.x1, generator.getPosition().x);
                    jsonObject2.put(DataNames.y1, generator.getPosition().y);
                    jsonObject2.put(DataNames.width ,generator.getWidth());
                    jsonObject2.put(DataNames.height, generator.getHeight());
                    jsonArray.add(jsonObject2);
                    break;
                default:
                    CustomElement customElement = (CustomElement) element;
                    jsonObject2.put(DataNames.name, customElement.getName());
                    jsonObject2.put(DataNames.x1, customElement.getPosition().x);
                    jsonObject2.put(DataNames.y1, customElement.getPosition().y);
                    jsonObject2.put(DataNames.orientation, customElement.getOrientation().toString());
                    jsonArray.add(jsonObject2);
            }
            jsonObject.put("elements",jsonArray);

            //writing in json file
            try {
                FileWriter fileWriter = new FileWriter(savingFile);
                fileWriter.write(jsonObject.toString());
                fileWriter.flush();
            } catch (IOException e) {
                System.err.println("Cannot access chosen file to save iteration");
            }
        }
    }

    private Element interpretInputPiece(JSONObject jsonObject, String name) {
        Point point, point2;
        String enumName;
        Orientation orient;
        State state;
        Dimension dimension;

        //interpreting each jsonObject
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