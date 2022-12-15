package oop.MapInterface;

import oop.MapInterface.MapObjects.Animal;
import oop.MapInterface.MapObjects.Plant;
import oop.Vector2d;

import java.util.*;
import java.util.HashMap;
import java.util.LinkedList;

abstract class AbstractWorldMap implements IWorldMap{

    protected Map<Vector2d, Plant> plants = new HashMap<>();
    protected Map<Vector2d, LinkedList<Animal>> animals = new HashMap<>();
    protected Set<Vector2d> positions = new HashSet<>();

    @Override
    public void addAnimal(Animal animal){
        Vector2d position = animal.getPosition();
        if(!animals.containsKey(position)){
            animals.put(position, new LinkedList<Animal>());
        }
        animals.get(position).add(animal);
        positions.add(position);
    }

    @Override
    public void removeAnimal(Animal animal){
        Vector2d position = animal.getPosition();
        animals.get(position).remove(animal);
        if (animals.get(position).isEmpty()){
            animals.remove(position);
            if (!plants.containsKey(position)){
                positions.remove(position);
            }
        }
    }

    @Override
    public void addPlant(Plant plant){
        plants.put(plant.getPosition(),plant);
        positions.add(plant.getPosition());
    }

    public Boolean canAddPlantAtPosition(Vector2d position){
        return !plants.containsKey(position);
    }

    @Override
    public void removePlant(Plant plant){
        Vector2d position = plant.getPosition();
        plants.remove(position);
        if (!animals.containsKey(position)){
            positions.remove(position);
        }
    }

    public void changePosition(Animal animal, Vector2d newPosition){
        
    }

}
