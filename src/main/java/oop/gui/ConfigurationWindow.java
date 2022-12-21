package oop.gui;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import oop.Config;
import oop.ParameterValidator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConfigurationWindow {
    private final ChoiceBox<String> choiceBox =
            new ChoiceBox<>(FXCollections.observableArrayList(listFiles(Config.CONFIG_DIR_PATH)));
    private final Label errorMsg;
    private final CheckBox csvCheckBox = new CheckBox("Save simulation statistics in CSV file");
    private final TextField csvDirectoryPathField = new TextField(Config.CSV_FILES_DIR_PATH);
    private final TextField csvFileNameField = new TextField();
    private VBox csvPathSelection;


    /**
     * Creates window with fields to chose simulation course
     */
    public ConfigurationWindow(){
        //Stage
        Stage inputWindow = new Stage();
        inputWindow.setTitle("Configuration window");

        //Config file selection container
        VBox configFileSelectionVbox = createConfigFileSelection();

        //CSV saving path selection container
        VBox csvBox = createCSVInputField(inputWindow);

        //Create error message
        errorMsg = new Label();
        errorMsg.setTextFill(Color.RED);

        // create submission button
        Button submitButton = new Button("Create New Simulation");
        submitButton.setOnAction(actionEvent -> {
            String val = choiceBox.getValue();
            if (val == null){
                showError("Configuration file must be chosen");
                return;
            }
            attemptToCreateSimulation(val);
        });

        //Create container
        VBox container = new VBox(configFileSelectionVbox, csvBox, errorMsg, submitButton);
        container.setAlignment(Pos.CENTER);
        container.setSpacing(20);

        //Style container
        container.setSpacing(15);
        container.setPadding(new Insets(10,10,10, 10));
        container.setAlignment(Pos.CENTER);

        //Set view in window
        inputWindow.setScene(new Scene(container));
        inputWindow.setWidth(350);
        inputWindow.setHeight(300);

        //Launch
        inputWindow.show();
    }


    /**
     * creates fields to input data necessary to choose config file or create one
     * @return  vbox containing UI
     */
    private VBox createConfigFileSelection(){
        //Label
        Label choiceBoxLabel = new Label("Choose configuration file");

        //Buttons
        Button RefreshButton = new Button("\uD83D\uDD04");
        RefreshButton.setOnAction(event -> {
            choiceBox.getItems().clear();
            choiceBox.getItems().addAll(listFiles(Config.CONFIG_DIR_PATH));
        });
        Button newConfigFileBtn = new Button("New File");
        newConfigFileBtn.setOnAction(event -> new InputConfigurationWindow());

        //Container with choiceBox and buttons
        HBox choiceContainer = new HBox(choiceBox, RefreshButton, newConfigFileBtn);

        //Main container
        VBox configFileSelection = new VBox(choiceBoxLabel, choiceContainer);
        choiceContainer.setAlignment(Pos.CENTER);
        configFileSelection.setAlignment(Pos.CENTER);

        return configFileSelection;
    }


    /**
     * creates fields to input data necessary to create csv file containing simulation statistics data
     * @param mainConfigWindow  main widow
     * @return                  vbox containing UI
     */
    private VBox createCSVInputField(Stage mainConfigWindow){
        //Upper box
        Label textFieldLabel = new Label("Select Directory: ");
        Button browseButton = new Button("Browse");
        HBox upperSpacingBox = new HBox();
        HBox.setHgrow(upperSpacingBox, Priority.ALWAYS);
        browseButton.setOnAction(event -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Select Directory");
            File directory = directoryChooser.showDialog(mainConfigWindow);
            if (directory != null) {
                csvDirectoryPathField.setText(directory.getAbsolutePath());
            }
        });
        HBox csvHBoxUpper = new HBox(textFieldLabel, upperSpacingBox, csvDirectoryPathField, browseButton);

        //Lower box
        HBox lowerSpacingBox = new HBox();
        HBox.setHgrow(lowerSpacingBox, Priority.ALWAYS);
        HBox csvHBoxLower = new HBox(new Label ("File name: "), lowerSpacingBox, csvFileNameField);

        //Main hiding container
        csvPathSelection = new VBox(csvHBoxUpper, csvHBoxLower);
        csvPathSelection.setAlignment(Pos.CENTER);
        csvPathSelection.setSpacing(10);

        //Main container (always visible)
        VBox mainVBox = new VBox(csvCheckBox);
        mainVBox.setAlignment(Pos.CENTER);
        mainVBox.setSpacing(20);

        //Hiding logic
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
            System.out.print(e.getMessage());
            return new ArrayList<>();
        }
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
     * Updates errorMsg label to inform that creation of new simulation was successful
     */
    private void showSuccess(){
        errorMsg.setText("Successfully created new simulation");
        errorMsg.setTextFill(Color.GREEN);
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
        if (Objects.equals(csvFileName, "")){
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
    private void attemptToCreateSimulation(String ConfigFileName) {
        if (csvCheckBox.isSelected()){
            try{
                String csvFilePath = getFullCsvFilePath();
                handleSimulationCreationResult(
                        ParameterValidator.startNewSimulation(ConfigFileName, csvFilePath)
                );
            }
            catch (IllegalArgumentException e){
                showError(e.getMessage());
            }
        }
        else{
            handleSimulationCreationResult(
                    ParameterValidator.startNewSimulation(ConfigFileName)
            );
        }
    }

    /**
     * Handles result of simulation creation
     * if error message was returned shows it to the user
     * otherwise informs user that new simulation was created
     * @param creationResult    result of simulation creation
     */
    private void handleSimulationCreationResult(String creationResult){
        if (Objects.equals(creationResult, "")){
            showSuccess();
        }
        else{
            showError(creationResult);
        }
    }


}
