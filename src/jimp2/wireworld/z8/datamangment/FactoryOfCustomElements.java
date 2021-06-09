package jimp2.wireworld.z8.datamangment;


import jimp2.wireworld.z8.wireworldlogic.State;
import jimp2.wireworld.z8.wireworldlogic.World;

import java.awt.*;
import java.io.*;
import java.util.HashMap;

public class FactoryOfCustomElements {

    private HashMap<String, CustomElement> availableCustomElements = new HashMap<>();

    private final String folderName = "Data/CustomElements";


    public FactoryOfCustomElements() {
        try {
            initializeElements();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("There is problem with CustomElements");
        }
    }




    private void initializeElements() throws IOException {

        String [] files;
        File folder = new File(folderName);
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles != null){
            files = new String[listOfFiles.length];

            for (int i = 0; i < listOfFiles.length; i++) {
                files[i] = listOfFiles[i].getName();
                FileReader fileReader;
                BufferedReader reader;
                try {
                    fileReader = new FileReader(folderName+"/"+files[i]);
                    reader = new BufferedReader(fileReader);
                } catch (FileNotFoundException e) {
                    System.err.println("There is problem with reading "+files[i]+" file in CustomElement");
                    continue;
                }


                int width;
                int height;
                Point inConnectorPoint;

                String line;
                String [] numbers;

                //reading width and height of custom element
                line = reader.readLine();
                numbers = line.split("\\s+");
                if (numbers.length == 2) {
                    width = Integer.parseInt(numbers[0]);
                    height = Integer.parseInt(numbers[1]);
                } else {
                    System.err.println("Wrong data in custom element file (width,height)");
                    continue;
                }

                //reading input point of custom element
                line = reader.readLine();
                numbers = line.split("\\s+");
                if (numbers.length == 2)
                    inConnectorPoint = new Point(Integer.parseInt(numbers[0]), Integer.parseInt(numbers[1]));
                else {
                    System.err.println("Wrong data in custom element file (input point)");
                    continue;
                }
                CustomElement customElement = new CustomElement(width,height,files[i],inConnectorPoint);

                //reading table
                boolean stoppedReading = false;
                int row = 0;
                while((line = reader.readLine()) != null) {
                    numbers = line.split("\\s+");
                    if (numbers.length != width) {
                        System.err.println("There is not enough points (width)");
                        stoppedReading = true;
                        break;
                    }
                    else {
                        for (int column = 0; column < width; column++) {
                            int number =  Integer.parseInt(numbers[column]);
                            if (number == 0)
                                customElement.cells[column][row].setState(State.EMPTY);
                            if (number == 1)
                                customElement.cells[column][row].setState(State.CONDUCTOR);
                            if (number == 2)
                                customElement.cells[column][row].setState(State.HEAD);
                            if (number == 3)
                                customElement.cells[column][row].setState(State.TAIL);
                        }

                    }

                    row++;
                }
                if(!stoppedReading)
                    availableCustomElements.put(files[i].substring(0,files[i].length()-4),customElement);
            }
        }
    }

    public HashMap<String, CustomElement> getAvailableCustomElements() {
        return availableCustomElements;
    }

    public void saveNewCustomElement(World world, Point startingPoint, String inputName) {

        //do usunięcia później
        //TODO
        startingPoint = new Point(0,0);
        String filename;
        if(inputName!=null){
            filename = inputName+".txt";
        }
        else {
            final String CustomName = "CustomElement";
            File folder = new File(folderName);
            File[] listOfFiles = folder.listFiles();
            int j = 1;
            if (listOfFiles != null) {
                for (File file : listOfFiles) {
                    filename = file.getName();
                    if (filename.contains(CustomName))
                        j++;
                }
                filename = "CustomElement" + j + ".txt";
            } else
                filename = "CustomElement1.txt";
        }
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(folderName + "/" + filename);
            fileWriter.write(world.getWidth() + " " + world.getHeight() + "\n"); //first line of txt custom element file

            fileWriter.write(startingPoint.x + " " + startingPoint.y + "\n");  //second line of custom element txt file

            for (int row = 0; row < world.getHeight(); row++) {
                for (int column = 0; column < world.getWidth(); column++) {
                    String state = world.cells[column][row].getState().toString();
                    switch (state) {
                            case "EMPTY":
                                fileWriter.write(0 + " ");
                                break;
                            case "CONDUCTOR":
                                fileWriter.write(1 + " ");
                                break;
                            case "HEAD":
                                fileWriter.write(2 + " ");
                                break;
                            case "TAIL":
                                fileWriter.write(3 + " ");
                                break;
                    }
                }
                fileWriter.write("\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.err.println("Cannot write custom element to file" + filename);
        }
    }
}