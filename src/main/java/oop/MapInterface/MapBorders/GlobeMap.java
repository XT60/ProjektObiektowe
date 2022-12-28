package oop.MapInterface.MapBorders;

import oop.ConfigParameters.WorldParamType;
import oop.MapInterface.PlantsOnMap.DeadAnimalsHolder;
import oop.MapInterface.MapConstants;
import oop.MapInterface.MapObjects.Animal;
import oop.Vector2d;

public class GlobeMap extends AbstractMap {
    MapConstants mapConstants;
    int height;
    int width;

    public GlobeMap(MapConstants mapConstants){
        this.mapConstants=mapConstants;
        this.deadAnimalsHolder = new DeadAnimalsHolder(mapConstants);
        height=mapConstants.get(WorldParamType.MAP_HEIGHT);
        width=mapConstants.get(WorldParamType.MAP_WIDTH);
    }

    public boolean canMoveTo(Vector2d position){
        return (position.y >= 0 &&  position.y < mapConstants.get(WorldParamType.MAP_HEIGHT));
    }

    @Override
    public Vector2d changePosition(Animal animal, Vector2d newPosition) {
        if(newPosition.y < 0 || newPosition.y>=mapConstants.get(WorldParamType.MAP_WIDTH)){
            animal.reverse();
            return newPosition;
        }
        else if(newPosition.x<0)
            return super.changePosition(animal,new Vector2d(mapConstants.get(WorldParamType.MAP_WIDTH)-1, newPosition.y));
        else if(newPosition.x>=mapConstants.get(WorldParamType.MAP_WIDTH))
            return super.changePosition(animal,new Vector2d(0,newPosition.y));
        return super.changePosition(animal, newPosition);
    }

    public int getHeight(){
        return this.height;
    }
    public int getWidth(){
        return this.width;
    }

    @Override
    public Vector2d getLowerLeft() {
        return new Vector2d(0,0);
    }

    @Override
    public Vector2d getUpperRight() {
        return new Vector2d(this.width,this.height);
    }

    public MapConstants getMapConstants(){ return this.mapConstants;}

}
