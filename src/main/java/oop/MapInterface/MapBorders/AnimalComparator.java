package oop.MapInterface.MapBorders;

import oop.MapInterface.MapObjects.Animal;

class AnimalComparator implements java.util.Comparator<Animal> {
    @Override
//    public int compare(Animal o1, Animal o2){
//        if(o1.getEnergy()<o2.getEnergy())
//            return 1;
//        else if(o1.getEnergy()>o2.getEnergy()) return -1;
//        return 1;
//    }
    public int compare(Animal o1, Animal o2){
        return o1.compareTo(o2);
    }
}