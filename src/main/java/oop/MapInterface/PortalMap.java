package oop.MapInterface;

import oop.ConfigParameters.WorldParamType;
import oop.Vector2d;

public class PortalMap extends AbstractWorldMap{

    public boolean canMoveTo(Vector2d position){

        return true;
    }
}
