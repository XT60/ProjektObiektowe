package oop.gui;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import java.io.FileNotFoundException;

import oop.World;
import oop.MapInterface.MapBorders.*;
import oop.*;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class SimulationWindow {

    int horizontal,vertical,width=80, height=80;
        Vector2d lowerLeft, upperRight;
        final GridPane gridPane = new GridPane();

        public void createGrid(){
//            this.lowerLeft = map.getLowerLeft();
//            this.upperRight =map.getUpperRight();
//            int xMin = min(lowerLeft.x, upperRight.x);
//            int xMax = max(lowerLeft.x, upperRight.x);
//            int yMin = min(lowerLeft.y, upperRight.y);
//            int yMax = max(lowerLeft.y, upperRight.y);

            int xMin = 0;
            int xMax = 9;
            int yMin = 0;
            int yMax = 5;
            this.horizontal = xMax - xMin + 1;
            this.vertical = yMax - yMin + 1;

            Label headerLabel = new Label("y/x");
            this.gridPane.add(headerLabel,0,0,1,1);
            this.gridPane.getColumnConstraints().add(new ColumnConstraints(width));
            this.gridPane.getRowConstraints().add(new RowConstraints(height));
            GridPane.setHalignment(headerLabel, HPos.CENTER);

            int column = xMin;
            for (int i = 1; i <= horizontal; i++) {
                Label columnNumber = new Label("" + column);
                this.gridPane.add(columnNumber, i, 0, 1, 1);
                GridPane.setHalignment(columnNumber, HPos.CENTER);
                this.gridPane.getColumnConstraints().add(new ColumnConstraints(width));
                column++;
            }

            int row = yMax;
            for (int i = 1; i <= vertical; i++) {
                Label rowNumber = new Label("" + row);
                this.gridPane.add(rowNumber, 0, i, 1, 1);
                GridPane.setHalignment(rowNumber, HPos.CENTER);
                this.gridPane.getRowConstraints().add(new RowConstraints(height));
                row--;
            }
        }

//        public void placeObjectsOnGrid(IMap map) throws FileNotFoundException {
//            for (int x = 1; x <= horizontal; x++)
//                for (int y = 1; y <= vertical; y++) {
//                    IMapElement mapObject = (IMapElement) map.objectAt(new Vector2d((x - 1) + lowerLeft.x, upperRight.y - (y - 1)));
//                    if (mapObject != null) {
//                        GuiElementBox guiElementBox = new GuiElementBox(mapObject);
//                        this.gridPane.add(guiElementBox.getvBox(), x, y, 1, 1);
//                        GridPane.setHalignment(guiElementBox.getvBox(), HPos.CENTER);
//                    }
//                }
//        }

        public void createMap(){
            this.gridPane.getChildren().clear();
            this.gridPane.getColumnConstraints().clear();
            this.gridPane.getRowConstraints().clear();
            this.gridPane.setGridLinesVisible(false);
            this.gridPane.setGridLinesVisible(true);
            createGrid();

        }




    public SimulationWindow(World world){
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
//        newWindow.setScene(new Scene(container));

        createMap();
        HBox hBox = new HBox(this.gridPane);
        Scene scene = new Scene(hBox, 800, 800);
        newWindow.setScene(scene);
        //Launch
        newWindow.show();

    }
}
