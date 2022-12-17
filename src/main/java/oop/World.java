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

    /**
     * checks if data inside worldParams Map is consistent between one another
     * @throws IllegalArgumentException     if parameters are not consistent
     */
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

    /**
     * retrieves value associated with paramType from paramValues Map
     * @param paramType     type of parameter
     * @return              parameter value
     * @throws IllegalArgumentException     if parameter is not present in paramValues or its value is null
     */
    private Object getParamValue(WorldParamType paramType) throws IllegalArgumentException {
        Object val = worldParams.get(paramType);
        if (val == null)
            throw new IllegalArgumentException("no value for " + paramType + "provided");
        return val;
    }

    /**
     * makes sure that value of given parameter type is positive
     * @param paramType          parameter type
     * @throws IllegalArgumentException if value of parameter type is not positive
     */
    private void mustBePositive(WorldParamType paramType) throws IllegalArgumentException{
        Integer val = (Integer) getParamValue(paramType);
        if(val < 0)
            throw new IllegalArgumentException(paramType + "must be positive");
    }

    /**
     * makes sure that value of given parameter type lower than given limit
     * @param paramType     parameter type
     * @param limit         upper limit
     * @param limitDesc     description of a limit (used in thrown exception message)
     * @throws IllegalArgumentException if condition is not fulfilled
     */
    private void mustBeLower(WorldParamType paramType, Integer limit, String limitDesc) throws IllegalArgumentException{
        Integer val = (Integer) getParamValue(paramType);
        if(limit < val)
            throw new IllegalArgumentException(paramType + "cannot be greater than" + limitDesc);
    }
}
