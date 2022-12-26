package oop.MapInterface;

import oop.MapInterface.MapObjects.Animal;
import oop.Vector2d;

import java.util.*;
import java.util.HashMap;


abstract class AbstractMap implements IMap {

    protected Map<Vector2d, SortedSet<Animal>> animals = new HashMap<>();

    @Override
    public void addAnimal(Animal animal, Vector2d position){
        if(!animals.containsKey(position)){
            animals.put(position, new TreeSet<>(new AnimalComparator()) {
            });
        }
        animals.get(position).add(animal);
    }

    @Override
    public void removeAnimal(Animal animal){
        Vector2d position = animal.getPosition();
        animals.get(position).remove(animal);
        if (animals.get(position).isEmpty()){
            animals.remove(position);
        }
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