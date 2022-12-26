package oop.MapInterface;

import oop.MapInterface.MapObjects.Plant;
import oop.Vector2d;

import java.util.HashSet;
import java.util.Set;

public class PlantsHolder {
    protected Set<Vector2d> plants = new HashSet<>();

    public void add(Plant plant){
        plants.add(plant.getPosition());
    }

    public void removePlant(Plant plant){
        plants.remove(plant.getPosition());
    }

    public Boolean isPlantAtPosition(Vector2d position){
        return !plants.contains(position);
    }
}
