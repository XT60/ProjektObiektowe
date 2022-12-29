package oop.MapInterface.PlantsOnMap;

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeadAnimalsHolderElement that)) return false;

        if (deadAnimalsCount != that.deadAnimalsCount) return false;
        if (currentlyOnMap != that.currentlyOnMap) return false;
        return position.equals(that.position);
    }

    @Override
    public int hashCode() {
        int result = position.hashCode();
        result = 31 * result + deadAnimalsCount;
        result = 31 * result + (currentlyOnMap ? 1 : 0);
        return result;
    }
}
