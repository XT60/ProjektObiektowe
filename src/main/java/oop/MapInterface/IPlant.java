package oop.MapInterface;

import oop.MapInterface.MapObjects.Plant;
import oop.Vector2d;

public interface IPlant {

    void addPlant();

    void removePlant(Plant plant);

    boolean isPlantAtPosition(Vector2d position);
}
