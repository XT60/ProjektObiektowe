package oop;

import oop.ConfigParameters.*;
import oop.MapInterface.IWorldMap;


import java.util.Map;

public class World {
    IWorldMap map;
    Map<WorldParamType, Object> worldParams;


    public World (Map<WorldParamType, Object> worldParams, Integer epochCount, Double epochDuration) throws IllegalArgumentException{
        this.worldParams = worldParams;
    }

    public World (Map<WorldParamType, Object> worldParams, Integer epochCount, Double epochDuration, String csvFilePath) throws IllegalArgumentException{
        this.worldParams = worldParams;
        // csvFilePath where to pass it to save statisitcs
    }

}
