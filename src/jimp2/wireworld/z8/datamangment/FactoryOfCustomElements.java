package jimp2.wireworld.z8.datamangment;


import jimp2.wireworld.z8.wireworldlogic.State;
import jimp2.wireworld.z8.wireworldlogic.World;

import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.List;

public class FactoryOfCustomElements {

    private HashMap<String, CustomElement> availableCustomElements = new HashMap<>();

    private final String folderName = "CustomElements";


    public FactoryOfCustomElements() {
        try {
            initializeElements();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("There is problem with CustomElements");
        }
    }

    private void initializeElements() throws IOException {

        int width = 0;
        int height = 0;
        Point inConnectorPoint = new Point();


        String [] files = null;
        File folder = new File(folderName);
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles != null){
            files = new String[listOfFiles.length];

            for (int i = 0; i < listOfFiles.length; i++) {

                files[i] = listOfFiles[i].getName();
                FileReader fileReader = null;
                BufferedReader reader = null;
                try {
                    fileReader = new FileReader("CustomElements/"+files[i]);
                    reader = new BufferedReader(fileReader);
                } catch (FileNotFoundException e) {
                    System.err.println("There is problem with reading "+files[i]+" file in CustomElement");
                    continue;
                }


                //reading width and height of custom element
                String line = reader.readLine();
                String[] w = line.split("\\s+");
                if (w.length == 2) {
                    width = Integer.parseInt(w[0]);
                    height = Integer.parseInt(w[1]);
                } else {
                    System.err.println("Wrong data in custom element file (width,height)");
                }

                //reading input point of custom element
                line = reader.readLine();
                w = line.split("\\s+");
                if (w.length == 2)
                    inConnectorPoint = new Point(Integer.parseInt(w[0]), Integer.parseInt(w[1]));
                else
                    System.err.println("Wrong data in custom element file (input point)");

                CustomElement cs = new CustomElement(width,height,files[i],inConnectorPoint);

                //reading table
                int row = 0;
                while((line = reader.readLine()) != null) {
                    w = line.split("\\s+");
                    if (w.length != width)
                        System.err.println("There is not enough points (width)");
                    else {
                        for (int column = 0; column < width; column++) {
                            if (Integer.parseInt(w[column]) == 0)
                                cs.cells[column][row].setState(State.EMPTY);
                            if (Integer.parseInt(w[column]) == 1)
                                cs.cells[column][row].setState(State.CONDUCTOR);
                            if (Integer.parseInt(w[column]) == 2)
                                cs.cells[column][row].setState(State.HEAD);
                            if (Integer.parseInt(w[column]) == 3)
                                cs.cells[column][row].setState(State.TAIL);
                        }
                    }
                    row++;
                }
                availableCustomElements.put(files[i].substring(0,files[i].length()-4),cs);
            }
        }
        /* else
            System.err.println("There is no custom elements in the folder!");
        */


    }

    public HashMap<String, CustomElement> getAvailableCustomElements() {
        return availableCustomElements;
    }

    public void saveNewCustomElement(World world, Point startingPoint) {

        //do usunięcia później
        //TODO
        startingPoint = new Point(0,0);

        final String CustomName = "CustomElement";
        File folder = new File(folderName);
        File [] listOfFiles = folder.listFiles();
        String filename;
        int j = 1;
        if(listOfFiles != null) {
            for (int  i = 0 ; i < listOfFiles.length; i++) {
                filename = listOfFiles[i].getName();
                if (filename.contains(CustomName))
                    j++;
            }
            filename = "CustomElement"+j+".txt";
        }
        else
            filename = "CustomElement1";

        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("CustomElements/"+filename);
            fileWriter.write(world.getWidth()+" "+world.getHeight()+"\n"); //first line of txt custom element file

            fileWriter.write(startingPoint.x+" "+startingPoint.y+"\n");  //second line of custom element txt file

            for(int row = 0; row < world.getHeight(); row++) {
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
            System.err.println("Cannot write custom element to file"+filename);
        }
    }

}