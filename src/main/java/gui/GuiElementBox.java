//package agh.ics.oop.gui;
//
//import agh.ics.oop.*;
//import javafx.geometry.Pos;
//import javafx.scene.control.Label;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.VBox;
//
//import java.io.File;
//
//public class GuiElementBox{
//    public VBox vBox;
//    private Label label;
//    private ImageView imageView;
//    private boolean labelHasPosition;
//    private String baseLabel;
//    private Vector2d position;
//    private MapDirection direction;
//    public VBox overlappedVBox;
//
//
//    public GuiElementBox(IMapElement element){
//        overlappedVBox = null;
//        position = element.getPosition();
//        direction = element.getDirection();
//        String path = "src/main/resources/" + element.getImageName();
//        String absPath = new File(path).getAbsolutePath();
//        Image image = new Image(absPath);
//        imageView = new ImageView(image);
//        imageView.setFitHeight(20);
//        imageView.setFitWidth(20);
//
//        if (element instanceof Animal){
//            baseLabel = "Z ";
//            labelHasPosition = true;
//        }
//        else{
//            baseLabel = "Trawa";
//            labelHasPosition = false;
//        }
//
//        label = new Label();
//        setLabel();
//        vBox = new VBox(imageView, label);
//        vBox.setAlignment(Pos.CENTER);
//        updateVBox(element);
//    }
//
//    private boolean didDirectionChange(MapDirection newDirection){
//        return !direction.equals(newDirection);
//    }
//
//    public boolean didPositionChange(Vector2d newPosition){
//        return !position.equals(newPosition);
//    }
//
//    private void setLabel(){
//        String newLabel = baseLabel;
//        if(labelHasPosition){
//            newLabel += position.toString();
//        }
//        label.setText(newLabel);
//    }
//
//    public Vector2d getOldPosition(){
//        return new Vector2d(position);
//    }
//
//    public void updateVBox(IMapElement element){
//        Vector2d newPosition = element.getPosition();
//        MapDirection newDirection = element.getDirection();
//        if(didPositionChange(newPosition)) {
//            position = newPosition;
//            setLabel();
//
//        }
//        if(didDirectionChange(newDirection)){
////            System.out.println(newDirection.toString());
//            String path = "src/main/resources/" + element.getImageName();
//            String absPath = new File(path).getAbsolutePath();
//            Image image = new Image(absPath);
//            imageView.setImage(image);
//            direction = newDirection;
//        }
//    }
//
//}
