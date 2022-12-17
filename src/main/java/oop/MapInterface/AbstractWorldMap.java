package oop.MapInterface;

import oop.MapInterface.MapObjects.Animal;
import oop.MapInterface.MapObjects.Plant;
import oop.Vector2d;

import java.util.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Comparator;
import java.lang.Math;

abstract class AbstractWorldMap implements IWorldMap{
    protected Set<Vector2d> plants = new HashSet<>();
    protected Map<Vector2d, SortedSet<Animal>> animals = new HashMap<>();

    protected Set<Vector2d> positions = new HashSet<>();
    // contains only animals positions

    @Override
    public void addAnimal(Animal animal){
        Vector2d position = animal.getPosition();
        if(!animals.containsKey(position)){
            animals.put(position, new TreeSet<>(new AnimalComparator()) {
            });
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
        }
    }

    @Override
    public void addPlant(Plant plant){
        plants.add(plant.getPosition());
    }

    public Boolean canAddPlantAtPosition(Vector2d position){
        return !plants.contains(position);
    }

    @Override
    public void removePlant(Plant plant){
        plants.remove(plant.getPosition());
    }

    abstract public boolean canMoveTo(Vector2d position);


    public void changePosition(Animal animal, Vector2d newPosition){
        removeAnimal(animal);
        addAnimal(animal);
    }

    public void growPlant(){
        int chance = Math.random();
    }


}
