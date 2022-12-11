package oop.gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import oop.ConfigParameters.WorldParamType;
import oop.Vector2d;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigurationWindow {
    private Map<WorldParamType, TextField> dataFields = new HashMap<>();
    private final static String windowName = "Configuration window";
    private final static Vector2d inputFieldSize = new Vector2d(300, 20);

    private static final int labelInputSpacing = 5;
    private static final int inputVBoxesSpacing = 10;

    private final static Vector2d submitButtonSize = new Vector2d(400, 50);
    private final static String submitButtonMessage = "Create New World";

    /**
     * creates window with fields to input data for simulation
     */
    public ConfigurationWindow(){
        Stage inputWindow = new Stage();
        inputWindow.setTitle(windowName);
        VBox vBox = new VBox(inputVBoxesSpacing);

        // creates input fields
        WorldParamType[] keys = WorldParamType.values();
        for(WorldParamType key : keys){
            vBox.getChildren().add(createInputField(key));
        }

        // creates submition button
        Button button = new Button(submitButtonMessage);
        button.minHeight(submitButtonSize.x);
        button.maxHeight(submitButtonSize.y);
        button.setOnAction(actionEvent -> {
            createNewSimulation();
        });
        vBox.getChildren().add(button);

        inputWindow.setScene(new Scene(vBox));
        inputWindow.show();
    }


    /**
     * creates new simulation instance
     * @return
     */
    public boolean createNewSimulation(){
        Map<WorldParamType, String> data = gatherData();
        try{
            new SimulationWindow(data);
            return true;
        }
        catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            return false;
        }
    }


    /**
     * gathers data from input fields
     * @return gathered data
     */
    private Map<WorldParamType, String> gatherData() {
        Map<WorldParamType, String> gatheredData = new HashMap<WorldParamType, String>();

        for (Map.Entry<WorldParamType, TextField> entry : dataFields.entrySet()) {
            WorldParamType key = entry.getKey();
            TextField value = entry.getValue();
            String data = value.getText();
            gatheredData.put(key, data);
        }
        return gatheredData;
    }


    /**
     * creats field for the data input and puts it in dataFields datastructure
     * @param worldParam
     * @return VBox
     */
    private HBox createInputField(WorldParamType worldParam){
        Label label = new Label(worldParam.getDescription());
        TextField textField = new TextField();
        textField.setMinSize(inputFieldSize.x, inputFieldSize.y);
        this.dataFields.put(worldParam, textField);
        return new HBox(labelInputSpacing, label, textField);
    }

}
