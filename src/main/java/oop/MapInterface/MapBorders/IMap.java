package oop.MapInterface.MapBorders;

import oop.MapInterface.IMapElement;
import oop.MapInterface.MapConstants;
import oop.MapInterface.MapObjects.Animal;
import oop.MapInterface.PlantsOnMap.DeadAnimalsHolder;
import oop.MapInterface.PlantsOnMap.IPlant;
import oop.Vector2d;

import java.util.LinkedList;

public interface IMap {

    void addAnimal(Animal animal, Vector2d position);

    void removeAnimal(Animal animal);

    void feedAnimals(IPlant plants);

    Vector2d changePosition(Animal animal, Vector2d newPosition);

    boolean canMoveTo(Vector2d position);

    int getHeight();

    int getWidth();

    IMapElement objectAt(Vector2d position);

    Vector2d getLowerLeft();

    Vector2d getUpperRight();

    MapConstants getMapConstants();

    DeadAnimalsHolder getDeadAnimalsHolder();

    LinkedList<Animal> procreateAnimals();



}

