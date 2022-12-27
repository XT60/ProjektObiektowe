package oop.MapInterface.MapBorders;

import oop.MapInterface.MapObjects.Animal;
import oop.Vector2d;

public interface IMap {

    void addAnimal(Animal animal, Vector2d position);

    void removeAnimal(Animal animal);

    Vector2d changePosition(Animal animal, Vector2d newPosition);

    boolean canMoveTo(Vector2d position);

}
