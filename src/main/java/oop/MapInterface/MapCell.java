package oop.MapInterface;

import java.util.HashSet;
import java.util.Set;

public class MapCell {
    public Set<Animal> animals;
    public Set<Plant> plants;

    public MapCell(){
        animals = new HashSet<>();
        plants = new HashSet<>();
    }
}
