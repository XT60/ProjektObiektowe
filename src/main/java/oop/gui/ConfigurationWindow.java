package oop.gui;

import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import oop.Config;
import oop.Vector2d;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConfigurationWindow {
    private ChoiceBox<Object> choiceBox;
    private Label errorMsg;

    private final static String windowName = "Configuration window";
    private final static Vector2d windowSize = new Vector2d(200,150);

    private final static String submitButtonMessage = "Create New Simulation";
    private static final int inputVBoxesSpacing = 10;
    private final static Vector2d submitButtonSize = new Vector2d(200, 50);
    private final static String choiceBoxNotCheckedExceptionMsg = "Configuration file must be chosen";


    /**
     * creates window with fields to input data for simulation
     */
    public ConfigurationWindow(){
        Stage inputWindow = new Stage();
        inputWindow.setTitle(windowName);
        VBox vBox = new VBox(inputVBoxesSpacing);
        vBox.setAlignment(Pos.CENTER);

        //create main label
        Label mainLabel = new Label("Choose configuration file");

        // create error msg
        errorMsg = new Label();
        errorMsg.setTextFill(Color.RED);

        // create choiceBox
        List<String> fileList = listFiles(Config.CONFIG_DIR_PATH);
        choiceBox = new ChoiceBox<Object>(FXCollections.observableArrayList(fileList));

        // create submission button
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

        vBox.getChildren().addAll(mainLabel, choiceBox, button, errorMsg);
        inputWindow.setScene(new Scene(vBox, windowSize.x, windowSize.y));
        inputWindow.show();
    }

    /**
     * lists files that are in given directory
     * @param dirPath   directory path
     * @return          list of file names
     */
    public List<String> listFiles(String dirPath) {
        return Stream.of(new File(dirPath).listFiles())
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toList());
    }

    /**
     * attempts to create new simulation instance
     * if not successful shows error in errorMsg label and in console
     */
    public void createNewSimulation(String fileName){
        try{
            new SimulationWindow(fileName);
            errorMsg.setText("");
        }
        catch (IllegalArgumentException | FileNotFoundException e){
            System.out.println(e.getMessage());
            errorMsg.setText(e.getMessage());
        }
    }
}
