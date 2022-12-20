package oop.gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import oop.Config;
import oop.ConfigParameters.WorldParamType;
import oop.World;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class SimulationWindow {
    World world;

    /**
     * attempts to create simulation instance
     * if not successful throws exception with error message
     * @param configFileName        name of config file in config directory
     * @throws FileNotFoundException
     * @throws IllegalArgumentException
     */
    public SimulationWindow(String configFileName) throws FileNotFoundException, IllegalArgumentException{
        List<String> fileContent = getFileContent(Config.CONFIG_DIR_PATH  + '/' + configFileName);

        int len = Config.CONFIG_FILE_STRUCTURE.length;
        if (fileContent.size() < len){
            throw new IllegalArgumentException("Config file has to little arguments");
        }
        if (fileContent.size() > len){
            throw new IllegalArgumentException("Config file has much little arguments");
        }

        Map<WorldParamType, Object> paramValues = new HashMap<>();
        for(int i = 0; i < len; i++){
            String[] parts = fileContent.get(i).split(" ");
            WorldParamType type = Config.CONFIG_FILE_STRUCTURE[i];
            if (parts.length != 2){
                throw new IllegalArgumentException("Invalid input for " + type);
            }
            int value;
            try{
                value = Integer.parseInt(parts[1]);
            }
            catch (NumberFormatException e){
                throw new IllegalArgumentException("Invalid input for " + type);
            }
            Object parsedValue = type.parse(value);
            paramValues.put(type, parsedValue);
        }
        world = new World(paramValues);
        showSimulationWindow();
    }

    /**
     * attempts to create simulation instance
     * if not successful throws exception with error message
     * @param configFileName        name of config file in config directory
     * @param csvFileName           name of csv file for saving simulation statistics
     * @throws FileNotFoundException        when config file was not found
     * @throws IllegalArgumentException     when given config file is not well-formatted
     */
    public SimulationWindow(String configFileName, String csvFileName) throws FileNotFoundException, IllegalArgumentException{
        this(configFileName);
//        csvFileName <-- save simulation statistics here, before that check if file still doesn't exists, do not overwrite!
    }


    /**
     * retrieve file content
     * @param filePath  path to file for read
     * @return          ArrayList where index relate to one line in file
     * @throws FileNotFoundException
     */
    private List<String> getFileContent(String filePath) throws FileNotFoundException{
        List<String> lines = new ArrayList<>();
        File myObj = new File(filePath);
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            lines.add(data);
        }
        myReader.close();
        return lines;
    }

    /**
     * attempts to create new simulation instance
     * if not successful returns error message
     * @param ConfigFileName  new simulation from filename
     * @return          error message or "" otherwise
     */

    static String createNewSimulation(String ConfigFileName){
        try{
            new SimulationWindow(ConfigFileName);
            return "";
        }
        catch (IllegalArgumentException | FileNotFoundException e){
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }

    static String createNewSimulation(String ConfigFileName, String csvFileName){
        try{
            new SimulationWindow(ConfigFileName, csvFileName);
            return "";
        }
        catch (IllegalArgumentException | FileNotFoundException e){
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }


    // not finished yet
    /**
     * creates GUI for simulation
     */
    private void showSimulationWindow(){
        //Create Stage
        Stage newWindow = new Stage();
        newWindow.setTitle("New Scene");
        //Create view in Java
        Label title = new Label("This is a pretty simple view!");
        TextField textField = new TextField("Enter your name here");
        Button button = new Button("OK");
        VBox container = new VBox(title, textField, button);
        //Style container
        container.setSpacing(15);
        container.setAlignment(Pos.CENTER);
        //Set view in window
        newWindow.setScene(new Scene(container));
        //Launch
        newWindow.show();
    }
}
