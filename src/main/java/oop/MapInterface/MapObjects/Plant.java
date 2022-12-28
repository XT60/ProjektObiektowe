package oop.MapInterface.MapObjects;

import oop.MapInterface.IMapElement;
import oop.Vector2d;

public class Plant implements IMapElement {
    Vector2d position;
    public Plant(Vector2d position){this.position=position;}
    public void setPosition(Vector2d position){
        this.position=position;
    }
    public Vector2d getPosition(){
        return this.position;
    }

    public String getView(){
            return "src/main/resources/grass.png";
    }
}
