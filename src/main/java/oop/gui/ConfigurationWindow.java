package oop.gui;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import oop.Config;
import oop.Vector2d;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConfigurationWindow {
    private final ChoiceBox choiceBox =
            new ChoiceBox<Object>(FXCollections.observableArrayList(listFiles(Config.CONFIG_DIR_PATH)));
    private final Label errorMsg = new Label();
    private final CheckBox csvCheckBox = new CheckBox("Save simulation statistics in CSV file");
    private final TextField csvDirectoryPathField = new TextField(Config.CSV_FILES_DIR_PATH);
    private final TextField csvFileNameField = new TextField();
    private final static String windowName = "Configuration window";
    private final static Vector2d windowSize = new Vector2d(300,300);

    private final static Vector2d textInputSize = new Vector2d(100, 20);

    private final static String submitButtonMessage = "Create New Simulation";
    private static final int inputVBoxesSpacing = 10;
    private final static Vector2d submitButtonSize = new Vector2d(200, 50);
    private final static String choiceBoxNotCheckedExceptionMsg = "Configuration file must be chosen";

    private VBox csvPathSelection;


//    private

    /**
     * creates window with fields to input data for simulation
     */
    public ConfigurationWindow(){
        Stage inputWindow = new Stage();
        inputWindow.setTitle(windowName);
        VBox vBox = new VBox(inputVBoxesSpacing);
        vBox.setAlignment(Pos.CENTER);

        //config file selection area

        VBox configFileSelectionVbox = createConfigFileSelection();

        //csv saving path selection
        VBox csvBox = createCSVInputField(inputWindow);

        errorMsg.setTextFill(Color.RED);
        //create csv files option



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
            createAndHandleNewSimulation((String)val);
        });

        vBox.getChildren().addAll(configFileSelectionVbox, csvBox, button, errorMsg);
        inputWindow.setScene(new Scene(vBox, windowSize.x, windowSize.y));
        inputWindow.show();
    }

    /**
     * creates fields to input data necessary to choose config file or create one
     * @return  vbox containing UI
     */
    private VBox createConfigFileSelection(){
        Label choiceBoxLabel = new Label("Choose configuration file");
        Button RefreshButton = new Button("\uD83D\uDD04");
        RefreshButton.setOnAction(event -> {
            choiceBox.getItems().clear();
            choiceBox.getItems().addAll(listFiles(Config.CONFIG_DIR_PATH));
        });
        Button newConfigFileBtn = new Button("New File");
        newConfigFileBtn.setOnAction(event -> new InputConfigurationWindow());

        HBox choiceBoxHBox = new HBox(choiceBox, RefreshButton, newConfigFileBtn);
        VBox configFileSelection = new VBox(choiceBoxLabel, choiceBoxHBox);
        choiceBoxHBox.setAlignment(Pos.CENTER);
        configFileSelection.setAlignment(Pos.CENTER);

        return configFileSelection;
    }

    /**
     * creates fields to input data necessary to create csv file containing simulation statistics data
     * @param mainConfigWindow  main widow
     * @return                  vbox containing UI
     */
    private VBox createCSVInputField(Stage mainConfigWindow){
        Label textFieldLabel = new Label("Select Directory: ");
        Button browseButton = new Button("Browse");
        browseButton.setOnAction(event -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Select Directory");
            File directory = directoryChooser.showDialog(mainConfigWindow);
            if (directory != null) {
                csvDirectoryPathField.setText(directory.getAbsolutePath());
            }
        });
        HBox csvHBoxUpper = new HBox(textFieldLabel, csvDirectoryPathField, browseButton);
        HBox csvHBoxLower = new HBox(new Label ("File name: "), csvFileNameField);
        csvHBoxUpper.setAlignment(Pos.CENTER_LEFT);
        csvHBoxLower.setAlignment(Pos.CENTER_LEFT);
        csvPathSelection = new VBox(csvHBoxUpper, csvHBoxLower);
        csvPathSelection.setAlignment(Pos.CENTER);


        VBox mainVBox = new VBox(csvCheckBox);
        mainVBox.setAlignment(Pos.CENTER);

        csvCheckBox.selectedProperty().addListener(
                (ObservableValue <? extends Boolean> ov, Boolean oldVal, Boolean newVal) -> {
            if (!oldVal && newVal){
                mainVBox.getChildren().add(csvPathSelection);
            } else if (!newVal && oldVal){
                mainVBox.getChildren().remove(csvPathSelection);
            }
        });

        return mainVBox;
    }


    /**
     * lists files that are in given directory
     * @param dirPath   directory path
     * @return          list of file names
     */
    public List<String> listFiles(String dirPath) {
        try{
            return Stream.of(Objects.requireNonNull(new File(dirPath).listFiles()))
                    .filter(file -> !file.isDirectory())
                    .map(File::getName)
                    .collect(Collectors.toList());
        }
        catch (NullPointerException e){
            System.out.print(e);
            return new ArrayList<String>();
        }
    }


    /**
     * retrieves form UI fields path of csv file to save statistics
     * @return                              csv file path
     * @throws IllegalArgumentException     when data in UI input fields is not valid
     */
    private String getFullCsvFilePath() throws IllegalArgumentException{
        String csvDirPath = csvDirectoryPathField.getText();
        File directory = new File(csvDirPath);
        if (!directory.isDirectory() || !directory.exists()) {
            throw new IllegalArgumentException("Given directory for saving csv file is not valid");
        }
        String csvFileName = csvFileNameField.getText();
        if (csvFileName == ""){
            throw new IllegalArgumentException("Csv file has to have a name");
        }
        String csvFilePath = csvDirPath + '/' + csvFileName + ".csv";
        File csvFile = new File(csvFilePath);
        if (csvFile.exists()){
            throw new IllegalArgumentException("There already is file: " + csvFilePath);
        }
        return csvFilePath;
    }


    /**
     * attempts to create new simulation if not successful handles error display
     * @param ConfigFileName        file name of simulation configuration file
     */
    private void createAndHandleNewSimulation(String ConfigFileName) {
        if (csvCheckBox.isSelected()){
            try{
                String csvFilePath = getFullCsvFilePath();
                errorMsg.setText(SimulationWindow.createNewSimulation(ConfigFileName, csvFilePath));
            }
            catch (IllegalArgumentException e){
                errorMsg.setText(e.getMessage());
            }
        }
        else{
            errorMsg.setText(SimulationWindow.createNewSimulation(ConfigFileName));
        }
    }


}
