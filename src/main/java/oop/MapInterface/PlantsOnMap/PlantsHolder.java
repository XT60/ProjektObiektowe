package oop.MapInterface.PlantsOnMap;

import oop.MapInterface.MapObjects.Plant;
import oop.Vector2d;

import java.util.HashMap;


public class PlantsHolder {
    protected HashMap<Vector2d,Plant> plantStorage = new HashMap<>();

    public void add(Plant plant){
        plantStorage.put(plant.getPosition(),plant);
    }

    public void removePlant(Plant plant){

        plantStorage.remove(plant.getPosition());
        System.out.println("Liczba plantow po usunieciu: " + plantStorage.size());
    }

    public Plant plantAtPosition(Vector2d position){
        return plantStorage.get(position);
    }
}
