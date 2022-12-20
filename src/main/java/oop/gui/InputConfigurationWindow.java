package oop.gui;

import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import oop.Config;
import oop.Vector2d;

import java.util.List;

public class InputConfigurationWindow {
    public InputConfigurationWindow(){
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
//    private final Label errorMsg;
//
//    private final static String windowName = "Configuration window";
//    private final static Vector2d windowSize = new Vector2d(200,150);
//
//    private final static String submitButtonMessage = "Create New Simulation";
//    private static final int inputVBoxesSpacing = 10;
//    private final static Vector2d submitButtonSize = new Vector2d(200, 50);
//    private final static String choiceBoxNotCheckedExceptionMsg = "Configuration file must be chosen";
//
//
//    /**
//     * creates window with fields to input data for simulation
//     */
//    public ConfigurationWindow(){
//        Stage inputWindow = new Stage();
//        inputWindow.setTitle(windowName);
//        VBox vBox = new VBox(inputVBoxesSpacing);
//        vBox.setAlignment(Pos.CENTER);
//
//        //create main label
//        Label mainLabel = new Label("Choose configuration file");
//
//        // create error msg
//        errorMsg = new Label();
//        errorMsg.setTextFill(Color.RED);
//
//
//        // create submission button
//        Button button = new Button(submitButtonMessage);
//        button.minHeight(submitButtonSize.x);
//        button.maxHeight(submitButtonSize.y);
//        button.setOnAction(actionEvent -> {
//            Object val = choiceBox.getValue();
//            if (val == null){
//                errorMsg.setText(choiceBoxNotCheckedExceptionMsg);
//                return;
//            }
//            createAndHandleNewSimulation((String)val);
//        });
//
//        vBox.getChildren().addAll(mainLabel, choiceBox, button, errorMsg);
//        inputWindow.setScene(new Scene(vBox, windowSize.x, windowSize.y));
//        inputWindow.show();
//    }
//
//    /**
//     * attempts to create new simulation if not successful handles error display
//     * @param fileName
//     */
//    private void createAndHandleNewSimulation(String fileName) {
//        errorMsg.setText(SimulationWindow.createNewSimulation(fileName));
//    }
}
