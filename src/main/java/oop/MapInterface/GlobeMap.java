package oop.MapInterface;

import oop.ConfigParameters.WorldParamType;
import oop.MapInterface.MapObjects.Animal;
import oop.MapInterface.MapObjects.Plant;
import oop.Vector2d;
import oop.World;

public class GlobeMap extends AbstractWorldMap{

    MapConstants mapConstants;
    public boolean canMoveTo(Vector2d position){
        return (position.y >= 0 &&  position.y < mapConstants.get(WorldParamType.MAP_HEIGHT));
    }



}
