package oop.gui;

import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import oop.Vector2d;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConfigurationWindow {
    private ChoiceBox choiceBox;
    private Label errorMsg;

    private final static String windowName = "Configuration window";
    private final static Vector2d windowSize = new Vector2d(300, 400);
    private final static String configFilesDirPath = "./ConfigFiles";

    private final static String choiceBoxNotCheckedExceptionMsg = "Configuration file must be chosen";

//    private final static Vector2d inputFieldSize = new Vector2d(300, 20);
//
//    private static final int labelInputSpacing = 5;
    private static final int inputVBoxesSpacing = 10;

    private final static Vector2d submitButtonSize = new Vector2d(200, 50);
    private final static String submitButtonMessage = "Create New World";

    /**
     * creates window with fields to input data for simulation
     */
    public ConfigurationWindow(){
        Stage inputWindow = new Stage();
        inputWindow.setTitle(windowName);
        VBox vBox = new VBox(inputVBoxesSpacing);

        //create main label
        Label mainLabel = new Label("Choose configuration file");

        // create error msg
        errorMsg = new Label();
        errorMsg.setTextFill(Color.RED);

        // create choiceBox
        List fileList = listConfigFiles(configFilesDirPath);
        choiceBox = new ChoiceBox(FXCollections.observableArrayList(fileList));


        // create submition button
        Button button = new Button(submitButtonMessage);
        button.minHeight(submitButtonSize.x);
        button.maxHeight(submitButtonSize.y);
        button.setOnAction(actionEvent -> {
            Object val = choiceBox.getValue();
            if (val == null){
                errorMsg.setText(choiceBoxNotCheckedExceptionMsg);
                return;
            }
            createNewSimulation((String)val);
        });

        vBox.getChildren().addAll(mainLabel, choiceBox, errorMsg, button);
        inputWindow.setScene(new Scene(vBox, windowSize.x, windowSize.y));
        inputWindow.show();
    }

    public List<String> listConfigFiles(String dir) {
        return Stream.of(new File(dir).listFiles())
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toList());
    }

//    String absolutePath = FileSystems.getDefault().getPath(dir).normalize().toAbsolutePath().toString();
    /**
     * creates new simulation instance
     * @return
     */
    public boolean createNewSimulation(String fileName){
        try{
            new SimulationWindow(fileName);
            errorMsg.setText("");
            return true;
        }
        catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            errorMsg.setText(e.getMessage());
            return false;
        }
        catch (FileNotFoundException e){
            System.out.println(e.getMessage());
            errorMsg.setText(e.getMessage());
            return false;
        }
    }
}
