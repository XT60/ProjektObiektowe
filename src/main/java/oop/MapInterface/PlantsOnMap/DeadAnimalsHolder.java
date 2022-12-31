package oop.MapInterface.PlantsOnMap;

import oop.ConfigParameters.WorldParamType;
import oop.MapInterface.MapConstants;
import oop.Vector2d;

import java.util.SortedSet;
import java.util.TreeSet;
import java.util.*;

public class DeadAnimalsHolder {

    MapConstants mapConstants;
    TreeSet<DeadAnimalsHolderElement> elements = new TreeSet<>( new DeadAnimalsHolderComparator());
    Map<Vector2d,DeadAnimalsHolderElement> positions = new HashMap<>();


    public DeadAnimalsHolder(MapConstants mapConstants){
        int mapHeight = mapConstants.get(WorldParamType.MAP_HEIGHT);
        int mapWidth = mapConstants.get(WorldParamType.MAP_WIDTH);

        for(int i=0; i<mapWidth; i++){
            for(int j=0; j<mapHeight; j++){
                Vector2d position = new Vector2d(i,j);
                DeadAnimalsHolderElement element = new DeadAnimalsHolderElement(position);
                elements.add(element);
                positions.put(position,element);
            }
        }

    }

    public Vector2d getPreferredField(){
        Iterator<DeadAnimalsHolderElement> iterator = elements.iterator();
        while(iterator.hasNext()){
            DeadAnimalsHolderElement element = iterator.next();
            if (!element.currentlyOnMap){
                iterator.remove();
                element.setOnMap();
                elements.add(element);
                return element.position;
            }
        }
        return null;
    }

    public Vector2d getOtherField(){
        Iterator<DeadAnimalsHolderElement> iterator = elements.descendingIterator();
        while(iterator.hasNext()){
            DeadAnimalsHolderElement element = iterator.next();
            if (!element.currentlyOnMap){
                iterator.remove();
                element.setOnMap();
                elements.add(element);
                return element.position;
            }
        }
        return null;

    }

    public void freePosition(Vector2d position){
        positions.get(position).removeFromMap();
    }

    public void deathAtPosition(Vector2d position){
        elements.remove(positions.get(position));
        positions.get(position).incrementCount();
        elements.add(positions.get(position));
    }
}
