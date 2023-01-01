package oop.MapInterface.MapBorders;

import com.sun.source.util.Trees;
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

    protected Map<Vector2d, TreeSet<Animal>> animals = new HashMap<Vector2d, TreeSet<Animal>>();
    DeadAnimalsHolder deadAnimalsHolder ;


    @Override
    public void addAnimal(Animal animal, Vector2d position){
        animal.move(position);
        if(!animals.containsKey(position)){
            animals.put(position, new TreeSet<>(new AnimalComparator()) {});
        }
        (animals.get(position)).add(animal);
    }

    @Override
    public void removeAnimal(Animal animal){
        Vector2d position = animal.getPosition();
        if (animals.get(position) != null){
            (animals.get(position)).remove(animal);
            if ((animals.get(position)).isEmpty()){
                animals.remove(position);
            }
            deadAnimalsHolder.deathAtPosition(position);
        }
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
        ArrayList<Animal> parents = new ArrayList<>();
        for (Vector2d position : animals.keySet()) {
            if ((animals.get(position)).size()>1) {
                Iterator<Animal> iterator = (animals.get(position)).iterator();
                parents.add(iterator.next());
                parents.add(iterator.next());
            }
        }
        for( int i=0; i<parents.size()-1; i+=2){
            removeAnimal(parents.get(i));
            removeAnimal(parents.get(i+1));
            Animal child = parents.get(i).procreate(parents.get(i+1));
            addAnimal(parents.get(i), parents.get(i).getPosition());
            addAnimal(parents.get(i+1), parents.get(i+1).getPosition());
            if (child != null){
                addAnimal(child, child.getPosition());
                procreatedAnimals.add(child);
            }
        }
        return procreatedAnimals;
    }

    public Animal animalAt(Vector2d position, boolean tracking){
        if(animals.containsKey(position)){
            if(tracking){
                animals.get(position).first().startTracking();
            }
            return animals.get(position).first();
        }
        return null;
    }

    abstract public boolean canMoveTo(Vector2d position);
    public abstract int getHeight();
    public abstract int getWidth();

    abstract public Vector2d getUpperRight();
    abstract public Vector2d getLowerLeft();

    public abstract MapConstants getMapConstants();


}
