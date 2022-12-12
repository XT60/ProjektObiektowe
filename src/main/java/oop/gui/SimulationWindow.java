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
    public SimulationWindow(String configFileName) throws FileNotFoundException, IllegalArgumentException, NumberFormatException{
        List<String> lines = new ArrayList<>();
        File myObj = new File(Config.CONFIG_DIR_PATH +configFileName);
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            lines.add(data);
        }
        myReader.close();
        int len = Config.CONFIG_FILE_STRUCTURE.length;
        if (lines.size() < len){
            throw new IllegalArgumentException("Config file has to little arguments");
        }
        if (lines.size() > len){
            throw new IllegalArgumentException("Config file has much little arguments");
        }

        Map<WorldParamType, Object> paramValues = new HashMap<>();
        for(int i = 0; i < len; i++){
            String[] parts = lines.get(i).split(" ");
            WorldParamType type = Config.CONFIG_FILE_STRUCTURE[i];
            if (parts.length != 2){
                throw new IllegalArgumentException("Invalid input for " + type);
            }
            int value = Integer.parseInt(parts[1]);
            Object parsedValue = type.parse(value);
            paramValues.put(type, parsedValue);
        }
        world = new World(paramValues);
        showSimulationWindow();
    }

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
