package oop.MapInterface.MapBorders;

import oop.MapInterface.IMapElement;
import oop.MapInterface.MapConstants;
import oop.MapInterface.MapObjects.Plant;
import oop.MapInterface.PlantsOnMap.DeadAnimalsHolder;
import oop.MapInterface.MapObjects.Animal;
import oop.MapInterface.PlantsOnMap.IPlant;
import oop.Vector2d;

import java.util.*;
import java.util.HashMap;


abstract class AbstractMap implements IMap {

    protected Map<Vector2d, SortedSet<Animal>> animals = new HashMap<Vector2d, SortedSet<Animal>>();
    DeadAnimalsHolder deadAnimalsHolder ;


    @Override
    public void addAnimal(Animal animal, Vector2d position){
        if(!animals.containsKey(position)){
            animals.put(position, new TreeSet<>(new AnimalComparator()) {});
        }
        animals.get(position).add(animal);
    }

    @Override
    public void removeAnimal(Animal animal){
        Vector2d position = animal.getPosition();
        (animals.get(position)).remove(animal);

        if ((animals.get(position)).isEmpty()){
            animals.remove(position);
        }
        deadAnimalsHolder.deathAtPosition(position);
    }


    public Vector2d changePosition(Animal animal, Vector2d newPosition){
        removeAnimal(animal);
        addAnimal(animal, newPosition);
        return newPosition;
    }

    public DeadAnimalsHolder getDeadAnimalsHolder(){
        return this.deadAnimalsHolder;
    }

    public void feedAnimals(IPlant plants) {
        for (Vector2d position : animals.keySet()) {
            Plant plant = plants.plantAtPosition(position);
            if (plant != null) {
                (animals.get(position)).first().feed();
                plants.removePlant(plant);
            }
        }
    }

    public IMapElement objectAt(Vector2d position){
        if(animals.containsKey(position)){
            return animals.get(position).first();
        }
        return null;
    }

    @Override
    public LinkedList<Animal> procreateAnimals() {
        LinkedList<Animal> procreatedAnimals = new LinkedList<Animal>();
        for (Vector2d position : animals.keySet()) {
            if ((animals.get(position)).size()>1) {
                Iterator<Animal> iterator = animals.get(position).iterator();
                Animal firstAnimal = iterator.next();
                Animal secondAnimal = iterator.next();
                Animal newAnimal = firstAnimal.procreate(secondAnimal);
                if(newAnimal!=null){
//                    this.addAnimal(newAnimal,newAnimal.getPosition());
                    procreatedAnimals.add(newAnimal);
                }
            }
        }
        for(Animal newAnimal : procreatedAnimals){
            this.addAnimal(newAnimal,newAnimal.getPosition());
        }
        return procreatedAnimals;
    }

    abstract public boolean canMoveTo(Vector2d position);
    public abstract int getHeight();
    public abstract int getWidth();

    abstract public Vector2d getUpperRight();
    abstract public Vector2d getLowerLeft();

    public abstract MapConstants getMapConstants();


}
