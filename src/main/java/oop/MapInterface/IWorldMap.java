package oop.MapInterface;

import oop.MapInterface.MapObjects.Animal;
import oop.MapInterface.MapObjects.Plant;

public interface IWorldMap {

    void addAnimal(Animal animal);

    void removeAnimal(Animal animal);

    void addPlant(Plant plant);

    void removePlant(Plant plant);

}
