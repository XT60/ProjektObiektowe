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
        checkConsistency();

        //initialize map variable
//        map = new
    }

    private void checkConsistency() throws IllegalArgumentException{
        WorldParamType[] mustBePositiveParams = {
            WorldParamType.MAP_HEIGHT,
            WorldParamType.MAP_WIDTH,
            WorldParamType.INIT_PLANT_COUNT,
            WorldParamType.PLANT_ENERGY,
            WorldParamType.PLANT_GROWTH_RATE,
            WorldParamType.INIT_ANIMAL_COUNT,
            WorldParamType.INIT_ANIMAL_ENERGY,
            WorldParamType.REPRODUCTION_ENERGY_THRESHOLD,
            WorldParamType.REPRODUCTION_COST,
            WorldParamType.MIN_MUTATION_COUNT,
            WorldParamType.MAX_MUTATION_COUNT,
            WorldParamType.ANIMAL_GENOME_LENGTH,
        };

        for (WorldParamType param : mustBePositiveParams){
            mustBePositive(param);
        }

        Integer mapHeight = (Integer) getParamValue(WorldParamType.MAP_HEIGHT);
        Integer mapWidth = (Integer) getParamValue(WorldParamType.MAP_WIDTH);
        Integer mapCellCountVal = mapHeight * mapWidth;
        String mapCellCountDesc = WorldParamType.MAP_WIDTH + " * " + WorldParamType.MAP_HEIGHT;

//        INIT_PLANT_COUNT < MAP_HEIGHT * MAP_WIDTH
        mustBeLower(WorldParamType.INIT_PLANT_COUNT, mapCellCountVal, mapCellCountDesc);

//        PLANT_GROWTH_RATE < MAP_HEIGHT * MAP_WIDTH
        mustBeLower(WorldParamType.PLANT_GROWTH_RATE, mapCellCountVal, mapCellCountDesc);

//        MIN_MUTATION_COUNT < MAX_MUTATION_COUNT + 1
        mustBeLower(
                WorldParamType.MIN_MUTATION_COUNT,
                (Integer)getParamValue(WorldParamType.MAX_MUTATION_COUNT) + 1,
                "" + WorldParamType.MAX_MUTATION_COUNT
        );

//        MAX_MUTATION_COUNT < ANIMAL_GENOME_LENGTH
        mustBeLower(
                WorldParamType.MIN_MUTATION_COUNT,
                (Integer) getParamValue(WorldParamType.ANIMAL_GENOME_LENGTH),
                "" + WorldParamType.ANIMAL_GENOME_LENGTH);
    }

    private Object getParamValue(WorldParamType paramType) throws IllegalArgumentException {
        Object val = worldParams.get(paramType);
        if (val == null)
            throw new IllegalArgumentException("no value for " + paramType + "provided");
        return val;
    }

    private void mustBePositive(WorldParamType param) throws IllegalArgumentException{
        Integer val = (Integer) getParamValue(param);
        if(val < 0)
            throw new IllegalArgumentException(param + "must be positive");
    }

    private void mustBeLower(WorldParamType param, Integer limit, String limitDesc) throws IllegalArgumentException{
        Integer val = (Integer) getParamValue(param);
        if(limit < val)
            throw new IllegalArgumentException(param + "cannot be greater than" + limitDesc);
    }
}
