package oop.MapInterface;

import oop.MapInterface.MapObjects.Animal;
import oop.MapInterface.MapObjects.Plant;
import oop.Vector2d;

public interface IWorldMap {

    void addAnimal(Animal animal);

    void removeAnimal(Animal animal);

    void addPlant(Plant plant);

    void removePlant(Plant plant);

    boolean canMoveTo(Vector2d position);

}
