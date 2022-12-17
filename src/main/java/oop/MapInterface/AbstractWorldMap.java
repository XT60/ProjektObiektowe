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

    /**
    *contains only animals positions
     */
    protected Set<Vector2d> positions = new HashSet<>();


    @Override
    public void addAnimal(Animal animal, Vector2d position){
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

    public Vector2d changePosition(Animal animal, Vector2d newPosition){
        removeAnimal(animal);
        addAnimal(animal, newPosition);
        return newPosition;
    }

}
// idea how to implement animal movement:
// Vector2d newPosition = animal.turn();
// if map.canMoveTo(newPosition)
//      animal.move(map.changePosition(newPosition))
// else
//      animal.turn