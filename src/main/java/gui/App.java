package gui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

//        //Create Stage
//        Stage newWindow = new Stage();
//        newWindow.setTitle("New Scene");
//        //Create view in Java
//        Label title = new Label("This is a pretty simple view!");
//        TextField textField = new TextField("Enter your name here");
//        Button button = new Button("OK");
//        VBox container = new VBox(title, textField, button);
//        //Style container
//        container.setSpacing(15);
//        container.setAlignment(Pos.CENTER);
//        //Set view in window
//        newWindow.setScene(new Scene(container));
//        //Launch
//        newWindow.show();

    }

    private Stage createInputWindow(){

        Stage inputWindow = new Stage();
        inputWindow.setTitle("New Scene");
//        int mapHeight
//        enum mapVariant
//        int initPlantCount
//        int plantEnergy
//        int plantGrowthRate
//        enum plantVariant
//        int initAnimalCount
//        int initAnimalEnergy
//        int reproductionEnergyTreshold
//        int reproductionCost
//        int minMutationCount
//        int maxMutationCount
//        enum mutationVariant
//        int animalGenomeLength
//        enum animalVariant

        String[] labelMessages = {
                "wysokość mapy",
                "szerokość mapy",
                "wariant mapy",
                "startowa liczba roślin",
                "energia zapewniana przez zjedzenie jednej rośliny",
                "liczba roślin wyrastająca każdego dnia",
                "wariant wzrostu roślin",
                "startowa liczba zwierzaków",
                "startowa energia zwierzaków",
                "energia konieczna, by uznać zwierzaka za najedzonego (i gotowego do rozmnażania)",
                "energia rodziców zużywana by stworzyć potomka",
                "minimalna liczba mutacji u potomków",
                "maksymalna liczba mutacji u potomków",
                "wariant mutacji",
                "długość genomu zwierzaków",
                "wariant zachowania zwierzaków"
        };

//        interface Validation{
//            public boolean isValid();
//        }
//
//        Validation validFunctions = {
//          new Validation()
//
//        };



        //Create view in Java
        Label title = new Label("This is a pretty simple view!");
        TextField textField = new TextField("Enter your name here");
        Button button = new Button("OK");
        button.setOnAction(event -> {

        });
        VBox container = new VBox(title, textField, button);

        container.setSpacing(15);
        container.setAlignment(Pos.CENTER);

        inputWindow.setScene(new Scene(container));

        return inputWindow;
    }



    private VBox createVariableInput (String labelMessage, int fieldWidth, int fieldHeight){
        Label label = new Label(labelMessage);
        TextField textField = new TextField();
        textField.setMinSize(fieldWidth, fieldHeight);
        return new VBox(label, textField);

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
