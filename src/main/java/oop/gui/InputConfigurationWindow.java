package oop.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import oop.Config;
import oop.ConfigParameters.WorldParamType;
import oop.Vector2d;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class InputConfigurationWindow {
    private final Map<WorldParamType, Spinner<Integer>> paramSpinners= new HashMap<>();

    private final TextField fileNameInputField = new TextField();
    private final Label errorMsg = new Label();

    /**
     * Creates setup window for choosing new configuration file parameters values
     */
    public InputConfigurationWindow(){
        //Stage
        Stage newWindow = new Stage();
        newWindow.setTitle("Create new simulation configuration file");

        //File name input
        HBox spacingBox = new HBox();
        HBox.setHgrow(spacingBox, Priority.ALWAYS);
        HBox fileNameContainer = new HBox(
                new Label("File name: "),
                spacingBox,
                fileNameInputField
        );

        //Param inputs
        WorldParamType[] paramTypeValues = WorldParamType.values();
        HBox[] paramInputContainers = new HBox[paramTypeValues.length];
        for (int i = 0; i < paramTypeValues.length; i++){
            paramInputContainers[i] = createParamInput(paramTypeValues[i]);
        }

        //Create submission button
        Button submitButton = new Button("Create new configuration file");
        HBox.setHgrow(submitButton, Priority.ALWAYS);
        submitButton.setOnAction(event -> attemptToCreateNewFile());

        //Main container
        VBox container = new VBox(fileNameContainer);
        container.getChildren().addAll(paramInputContainers);
        container.getChildren().addAll(errorMsg, submitButton);

        //Style container
        container.setSpacing(15);
        container.setPadding(new Insets(10,10,10, 10));
        container.setAlignment(Pos.CENTER);

        //Set view in window
        newWindow.setScene(new Scene(container));

        //Launch
        newWindow.show();
    }

    /**
     * Creates HBox containing parameter setup interface,
     * updates spinner value referencing to key of given paramType
     * @param paramType     parameter type
     * @return              container
     */
    private HBox createParamInput(WorldParamType paramType){
        //Label
        Label label = new Label(paramType.toString() + ':');
        label.setAlignment(Pos.CENTER_RIGHT);

        //Spacing
        HBox spacingBox = new HBox();
        HBox.setHgrow(spacingBox, Priority.ALWAYS);

        //Spinner
        Vector2d paramValueRange = paramType.getValueRange();
        Spinner<Integer> spinner = new Spinner<>(
                paramValueRange.x,
                paramValueRange.y,
                paramType.getDefaultValue()
        );
        paramSpinners.put(paramType, spinner);
        spinner.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(spinner, Priority.NEVER);

        //Main container
        HBox mainBox = new HBox(
            label,
            spacingBox,
            spinner
        );

        mainBox.setAlignment(Pos.CENTER_LEFT);
        return mainBox;
    }

    /**
     * Updates errorMsg label to display given error message in red color
      * @param message      error message content
     */
    private void showError(String message){
        errorMsg.setText(message);
        errorMsg.setTextFill(Color.RED);
    }

    /**
     * Updates errorMsg label to display given message in green color
     * @param message      message content
     */
    private void showSuccess(String message){
        errorMsg.setText(message);
        errorMsg.setTextFill(Color.GREEN);
    }


    /**
     * Attempts to create new configuration file
     * if successful updates errorMsg to inform user about success
     * otherwise updates errorMsg with information suited to encountered troubles
     */
    private void attemptToCreateNewFile(){
        String fileName = fileNameInputField.getText();
        if (Objects.equals(fileName, "")){
            showError("File has to have name");
            return;
        }
        String filePath = Config.CONFIG_DIR_PATH + '/' + fileName + ".txt";
        File file = new File(filePath);

        try {
            if (!file.createNewFile()) {
                showError("There already is file " + filePath);
                return;
            }

            // Fills file with data
            FileOutputStream fos = new FileOutputStream(file);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            for (WorldParamType paramType: Config.CONFIG_FILE_STRUCTURE) {
                bw.write(paramType.getKey() + ' ' + paramSpinners.get(paramType).getValue());
                bw.newLine();
            }

            bw.close();
            showSuccess("File: " + filePath + " created successfully");
        }
        catch (IOException e) {
            showError(e.getMessage());
        }
    }

}
