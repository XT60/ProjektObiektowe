package oop.MapInterface;

import oop.MapInterface.MapObjects.Animal;
import oop.MapInterface.MapObjects.Plant;
import oop.Vector2d;

public interface IWorldMap {

    void addAnimal(Animal animal, Vector2d position);

    void removeAnimal(Animal animal);

    Vector2d changePosition(Animal animal, Vector2d newPosition);

    void addPlant(Plant plant);

    void removePlant(Plant plant);

    boolean canMoveTo(Vector2d position);

}
