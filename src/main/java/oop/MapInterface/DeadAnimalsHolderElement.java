package oop.MapInterface;

import oop.Vector2d;

public class DeadAnimalsHolderElement {
    Vector2d position;
    int deadAnimalsCount=0;
    boolean currentlyOnMap=false;
    public DeadAnimalsHolderElement(Vector2d position){
        this.position=position;
    }
    public void incrementCount(){
        deadAnimalsCount+=1;
    }

    public void setOnMap(){
        currentlyOnMap=true;
    }

    public void removeFromMap(){
        currentlyOnMap=false;
    }


}
