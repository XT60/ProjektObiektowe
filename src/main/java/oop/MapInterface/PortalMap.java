package oop.MapInterface;
import java.lang.Math;
import oop.ConfigParameters.WorldParamType;
import oop.MapInterface.MapObjects.Animal;
import oop.Vector2d;

public class PortalMap extends AbstractMap {

    MapConstants mapConstants;

    public PortalMap(MapConstants mapConstants){
        this.mapConstants=mapConstants;
    }

    public boolean canMoveTo(Vector2d position){
        return true;
    }
    public Vector2d changePosition(Animal animal, Vector2d newPosition) {
        if(newPosition.y < 0 || newPosition.x < 0 || newPosition.y>=mapConstants.get(WorldParamType.MAP_HEIGHT) || newPosition.x>=mapConstants.get(WorldParamType.MAP_WIDTH)) {
            animal.applyTeleportPenalty();
            Vector2d position = new Vector2d((int) (Math.random()*(mapConstants.get(WorldParamType.MAP_WIDTH))), (int) (Math.random()*(mapConstants.get(WorldParamType.MAP_HEIGHT))));
            return super.changePosition(animal,position);
        }
        return super.changePosition(animal, newPosition);
    }



}
