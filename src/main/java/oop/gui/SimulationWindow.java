package oop.gui;

import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.ArrayList;

import oop.MapInterface.IMapElement;
import oop.MapInterface.MapObjects.Animal;
import oop.MapInterface.PlantsOnMap.IPlant;
import oop.World;
import oop.MapInterface.MapBorders.*;
import oop.*;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class SimulationWindow {

    int horizontal,vertical;
    int width=40, height=40;
    Vector2d lowerLeft, upperRight;
    final GridPane gridPane = new GridPane();
    SimulationEngine simulationEngine;
    private int freeSpaces = 0;

    public void addSimulationEngine(SimulationEngine simulationEngine){
        this.simulationEngine = simulationEngine;
    }

    public void launchSimulationWindow(IMap map){
        Stage newWindow = new Stage();
        newWindow.setOnHidden(e -> simulationEngine.stop());
        newWindow.setTitle("Simulation");
        createGrid(map);
        HBox hBox = new HBox(this.gridPane);
        Scene scene = new Scene(hBox, 800, 800);
        newWindow.setScene(scene);
        newWindow.show();
    }

    public void createGrid(IMap map){
        this.lowerLeft = map.getLowerLeft();
        this.upperRight =map.getUpperRight();
        int xMin = min(lowerLeft.x, upperRight.x);
        int xMax = max(lowerLeft.x, upperRight.x);
        int yMin = min(lowerLeft.y, upperRight.y);
        int yMax = max(lowerLeft.y, upperRight.y);
        this.horizontal = xMax - xMin;
        this.vertical = yMax - yMin+1;

        Label headerLabel = new Label("y/x");
        this.gridPane.add(headerLabel,0,0,1,1);
        this.gridPane.getColumnConstraints().add(new ColumnConstraints(width));
        this.gridPane.getRowConstraints().add(new RowConstraints(height));
        GridPane.setHalignment(headerLabel, HPos.CENTER);

        int column = xMin;
        for (int i = 1; i <=horizontal; i++) {
            Label columnNumber = new Label("" + column);
            this.gridPane.add(columnNumber, i, 0, 1, 1);
            GridPane.setHalignment(columnNumber, HPos.CENTER);
            this.gridPane.getColumnConstraints().add(new ColumnConstraints(width));
            column++;
        }

        int row = yMax-1;
        for (int i = 1; i <vertical; i++) {
            Label rowNumber = new Label("" + row);
            this.gridPane.add(rowNumber, 0, i, 1, 1);
            GridPane.setHalignment(rowNumber, HPos.CENTER);
            this.gridPane.getRowConstraints().add(new RowConstraints(height));
            row--;
            }
        }


    public void placeObjectsOnGrid(IMap map, IPlant plants, int countOfAnimals, int averageEnergy, int averageAge){
        freeSpaces = horizontal*(vertical-1);
        for (int x = 1; x <= horizontal; x++) {
            for (int y = 1; y <= vertical; y++) {
                Vector2d position = new Vector2d((x - 1) + lowerLeft.x, upperRight.y - (y));
                IMapElement mapObject = map.objectAt(position);
                if (mapObject != null) {
                    addingObjectOnMap(x, y, mapObject);
                    freeSpaces--;
                }
                else {
                    mapObject = plants.plantAtPosition(position);
                    if (mapObject != null) {
                        addingObjectOnMap(x, y, mapObject);
                        freeSpaces--;
                    }
                }
            }
        }
        Label animalCount = new Label("Animal count: " + countOfAnimals);
        this.gridPane.add(animalCount,width+3,0);

        Label plantsCount = new Label("Plants count: " + plants.getNumberOfPlants());
        this.gridPane.add(plantsCount,width+3,1);

        Label freeSpace = new Label("Number of free fields: " + freeSpaces);
        this.gridPane.add(freeSpace,width+3,2);

        Label mostPopularGen = new Label("Most popular gen: " + this.simulationEngine.getPopularGen());
        this.gridPane.add(mostPopularGen,width+3,3);

        Label avgEnergy = new Label("Average energy: " + averageEnergy);
        this.gridPane.add(avgEnergy,width+3,4);

        Label avgAge = new Label("Life expectancy: " + averageAge);
        this.gridPane.add(avgAge,width+3,5);

        Button pauseButton = new Button("Pause/Play");
        pauseButton.setOnAction((action) -> Platform.runLater(() -> simulationEngine.stopOrResume()));
        this.gridPane.add(pauseButton,width+3, 6);

        Animal trackedAnimal = simulationEngine.getTrackedAnimal();
        if(simulationEngine.isAnimalTracked() && trackedAnimal != null){

            Label genome = new Label("Animal genome: " + Arrays.toString(trackedAnimal.getGenome()));
            this.gridPane.add(genome,width+3,8);

            Label currentGen = new Label("Currently active gen: " + trackedAnimal.getCurrentGen());
            this.gridPane.add(currentGen,width+3,9);

            Label animalEnergy = new Label("Animal energy: " + trackedAnimal.getEnergy());
            this.gridPane.add(animalEnergy,width+3,10);

            Label numberOfEatenPlants = new Label("Number of eaten plants: " + trackedAnimal.getNumberOfEatenPlants());
            this.gridPane.add(numberOfEatenPlants,width+3,11);

            Label numberOfKids = new Label("Number of kids: " + trackedAnimal.getChildrenCount());
            this.gridPane.add(numberOfKids, width+3, 12);

            Label animalAge = new Label("Animal age: " + trackedAnimal.getAge());
            this.gridPane.add(animalAge, width+3, 13);

            if(trackedAnimal.getDateOfDeath() != 0 ) {
                Label animalDateOfDeath = new Label("Animal died at day: " + trackedAnimal.getDateOfDeath());
                this.gridPane.add(animalDateOfDeath, width + 3, 14);
            }
        }
    }




    private void addingObjectOnMap(int x, int y, IMapElement mapObject) {
        GuiElementBox guiElementBox = null;
        try {
            guiElementBox = new GuiElementBox(mapObject);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Button button = guiElementBox.getButton();
        button.setOnAction((action) -> this.simulationEngine.trackAnimal((x - 1) + lowerLeft.x, upperRight.y - (y)));
        this.gridPane.add(button, x, y, 1, 1);
        GridPane.setHalignment(guiElementBox.getvBox(), HPos.CENTER);
    }


    public void createMap(IMap map, IPlant plantMap, int countOfAnimals, int averageEnergy, int averageAge) {
        this.gridPane.getChildren().clear();
        this.gridPane.getColumnConstraints().clear();
        this.gridPane.getRowConstraints().clear();
        createGrid(map);
        placeObjectsOnGrid(map,plantMap,countOfAnimals, averageEnergy, averageAge);
    }

    public void createPauseMap(IMap map, int popularGen) {
        for (int x = 1; x <= horizontal; x++) {
            for (int y = 1; y <= vertical; y++) {
                Vector2d position = new Vector2d((x - 1) + lowerLeft.x, upperRight.y - (y));
                Animal animal = map.animalAt(position, false);
                if (animal != null && animal.getCurrentGen() == popularGen ) {
                    Image image;
                    try {
                        image = new Image(new FileInputStream("src/main/resources/highlighted.png"));
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    ImageView imageView = new ImageView(image);
                    imageView.setFitHeight(20);
                    imageView.setFitWidth(20);
                    Button button = new Button();
                    button.setGraphic(imageView);
                    button.setAlignment(Pos.CENTER);
                    VBox vBox = new VBox(imageView);
                    vBox.setAlignment(Pos.CENTER);
                    int finalX = x;
                    int finalY = y;
                    button.setOnAction((action) -> this.simulationEngine.trackAnimal((finalX - 1) + lowerLeft.x, upperRight.y - (finalY)));
                    this.gridPane.add(button, finalX, finalY, 1, 1);
                }

            }
        }
    }

    public int getFreeSpaces() {
        return freeSpaces;
    }
}
