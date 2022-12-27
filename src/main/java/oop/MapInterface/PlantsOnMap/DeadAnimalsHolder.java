package oop.MapInterface.PlantsOnMap;

import oop.ConfigParameters.WorldParamType;
import oop.MapInterface.MapConstants;
import oop.Vector2d;

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
        for(DeadAnimalsHolderElement element : elements){
            if (!element.currentlyOnMap){
                element.setOnMap();
                return element.position;
            }
        }
        return null;
    }

    public Vector2d getOtherField(){
        for(DeadAnimalsHolderElement element :  elements.descendingSet()){
            if (!element.currentlyOnMap){
                element.setOnMap();
                return element.position;
            }
        }
        return null;
    }

    public void freePosition(Vector2d position){
        positions.get(position).removeFromMap();
    }

    public void deathAtPosition(Vector2d position){
        positions.get(position).incrementCount();
        elements.remove(positions.get(position));
        elements.add(positions.get(position));
    }
}
