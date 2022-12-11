package oop.MapInterface;

import java.util.ArrayList;

abstract class AbstractWorldMap implements WorldMap{
    private ArrayList<ArrayList<MapCell>> worldMap;
    private ArrayList<Animal> animals;
    private ArrayList<Plant> plants;
//    Map<oop.Vector2d, IMapElement> elementList = new HashMap<oop.Vector2d, IMapElement>();
//    MapVisualizer visualizer;

//    public AbstractWorldMap(){
//        visualizer = new MapVisualizer(this);
//    }
//
//    public void positionChanged(oop.Vector2d oldPosition, oop.Vector2d newPosition){
//        IMapElement movedElement = elementList.get(oldPosition);
//        elementList.remove(oldPosition);
//        elementList.put(newPosition, movedElement);
//    }
//
//    public boolean place(Animal animal) throws IllegalArgumentException{
//        oop.Vector2d currPosition = animal.getPosition();
//        if (canMoveTo(currPosition)){
//            elementList.put(animal.getPosition(), animal);
//            return true;
//        }
//        throw new IllegalArgumentException( currPosition + " is not appropriate position for animal to place");
//    }
//
//    public boolean isOccupied(oop.Vector2d position) {
//        if(objectAt(position) == null){
//            return false;
//        }
//        return true;
//    }
//
//    public Object objectAt(oop.Vector2d position) {
//        return elementList.get(position);
//    }
//
//    protected abstract oop.Vector2d upperRightMapCorner();
//
//    protected abstract oop.Vector2d lowerLeftMapCorner();
//
//    public String toString() {
//        oop.Vector2d lowerLeft = lowerLeftMapCorner();
//        oop.Vector2d upperRight = upperRightMapCorner();
//        return visualizer.draw(lowerLeft, upperRight);
//    }
}
