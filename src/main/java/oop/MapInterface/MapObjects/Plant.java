package oop.MapInterface.MapObjects;

import oop.Vector2d;

public class Plant {
    Vector2d position;
    public Plant(Vector2d position){this.position=position;}
    public void setPosition(Vector2d position){
        this.position=position;
    }
    public Vector2d getPosition(){
        return this.position;
    }
}
