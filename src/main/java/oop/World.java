package oop;

import oop.ConfigParameters.*;
import oop.MapInterface.IWorldMap;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class World {
    IWorldMap map;
    Map<WorldParamType, Object> worldParams;


    public World (Map<WorldParamType, Object> worldParams) throws IllegalArgumentException{
        this.worldParams = worldParams;
    }

    public World (Map<WorldParamType, Object> worldParams, String csvFilePath) throws IllegalArgumentException{
        this.worldParams = worldParams;
        // csvFilePath where to pass it to save statisitcs
    }

}
