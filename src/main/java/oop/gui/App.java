package oop.gui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import oop.ConfigParameters.WorldParamType;

import java.util.HashMap;
import java.util.Map;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        new ConfigurationWindow();

    }

}
