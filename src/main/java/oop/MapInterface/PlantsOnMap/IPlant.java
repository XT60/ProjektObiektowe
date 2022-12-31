package oop.MapInterface.PlantsOnMap;

import oop.MapInterface.MapObjects.Plant;
import oop.Vector2d;

public interface IPlant {

    void addPlant();

    void removePlant(Plant plant);

    Plant plantAtPosition(Vector2d position);

    int getNumberOfPlants();
    int getPlantsEnergy();
}
