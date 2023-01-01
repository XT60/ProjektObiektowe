package oop.gui;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import oop.MapInterface.IMapElement;
import javafx.scene.control.Button;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GuiElementBox {
    Image image;
    ImageView imageView;
    VBox vBox;
    Button button = new Button();

    public GuiElementBox(IMapElement mapElement) throws FileNotFoundException{
        try {
            this.image = new Image(new FileInputStream(mapElement.getView()));
            this.imageView = new ImageView(this.image);
            this.imageView.setFitWidth(20);
            this.imageView.setFitHeight(20);
            this.vBox = new VBox(this.imageView);
            this.vBox.setAlignment(Pos.CENTER);
            button.setGraphic(imageView);
            this.button.setAlignment(Pos.CENTER);
        }
        catch(FileNotFoundException exception){
            throw new FileNotFoundException("File was not found." + exception);
        }
    }
    public VBox getvBox(){
        return this.vBox;
    }

    public Button getButton() { return this.button;}

}
