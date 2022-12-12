package oop;

import oop.ConfigParameters.*;
import oop.MapInterface.WorldMap;

import java.util.HashMap;
import java.util.Map;

public class World {
    WorldMap map;
    int mapHeight;
    int mapWidth;
    MapVariant mapVariant;
    int initPlantCount;
    int plantEnergy;
    int plantGrowthRate;
    PlantVariant plantVariant;
    int initAnimalCount;
    int initAnimalEnergy;
    int reproductionEnergyThreshold;
    int reproductionCost;
    int minMutationCount;
    int maxMutationCount;
    MutationVariant mutationVariant;
    int animalGenomeLength;
    AnimalVariant animalVariant;

    public World (Map<WorldParamType, Object> paramValues) throws IllegalArgumentException{
        // initialize "this" variables
        mapHeight = (Integer)paramValues.get(WorldParamType.MAP_HEIGHT);
        mapWidth = (Integer)paramValues.get(WorldParamType.MAP_WIDTH);
        initPlantCount = (Integer)paramValues.get(WorldParamType.INIT_PLANT_COUNT);
        plantEnergy = (Integer)paramValues.get(WorldParamType.PLANT_ENERGY);
        plantGrowthRate = (Integer)paramValues.get(WorldParamType.PLANT_GROWTH_RATE);
        initAnimalCount = (Integer)paramValues.get(WorldParamType.INIT_ANIMAL_COUNT);
        initAnimalEnergy = (Integer)paramValues.get(WorldParamType.INIT_ANIMAL_ENERGY);
        reproductionEnergyThreshold = (Integer)paramValues.get(WorldParamType.REPRODUCTION_ENERGY_THRESHOLD);
        reproductionCost = (Integer)paramValues.get(WorldParamType.REPRODUCTION_COST);
        minMutationCount = (Integer)paramValues.get(WorldParamType.MIN_MUTATION_COUNT);
        maxMutationCount = (Integer)paramValues.get(WorldParamType.MAX_MUTATION_COUNT);
        animalGenomeLength = (Integer)paramValues.get(WorldParamType.ANIMAL_GENOME_LENGTH);

        mapVariant = (MapVariant)paramValues.get(WorldParamType.MAP_VARIANT);
        mutationVariant = (MutationVariant) paramValues.get(WorldParamType.MUTATION_VARIANT);
        plantVariant = (PlantVariant) paramValues.get(WorldParamType.PLANT_VARIANT);
        animalVariant = (AnimalVariant) paramValues.get(WorldParamType.ANIMAL_VARIANT);

        //initialize map variable
//        map = new
    }



    private void checkConsistency(Map<WorldParamType, Integer> intParamsMap ) throws IllegalArgumentException{
        for (WorldParamType param : intParamsMap.keySet()){
            mustBePositive(intParamsMap, param);
        }

        Integer mapHeight = intParamsMap.get(WorldParamType.MAP_HEIGHT);
        Integer mapWidth = intParamsMap.get(WorldParamType.MAP_WIDTH);
        Integer mapCellCount = mapHeight * mapWidth;
        String mapCellCountLabel = WorldParamType.MAP_WIDTH + " * " + WorldParamType.MAP_HEIGHT;

//        INIT_PLANT_COUNT < MAP_HEIGHT * MAP_WIDTH
        mustBeLower(intParamsMap, WorldParamType.INIT_PLANT_COUNT, mapCellCount, mapCellCountLabel);

//        PLANT_GROWTH_RATE < MAP_HEIGHT * MAP_WIDTH
        mustBeLower(intParamsMap, WorldParamType.PLANT_GROWTH_RATE, mapCellCount, mapCellCountLabel);

//        MIN_MUTATION_COUNT < MAX_MUTATION_COUNT + 1
        mustBeLower(intParamsMap, WorldParamType.MIN_MUTATION_COUNT,
                intParamsMap.get(WorldParamType.MAX_MUTATION_COUNT) + 1 , "" + WorldParamType.MAX_MUTATION_COUNT);

//        MAX_MUTATION_COUNT < ANIMAL_GENOME_LENGTH
        mustBeLower(intParamsMap, WorldParamType.MIN_MUTATION_COUNT,
                intParamsMap.get(WorldParamType.ANIMAL_GENOME_LENGTH), "" + WorldParamType.ANIMAL_GENOME_LENGTH);
    }

    private void mustBePositive(Map<WorldParamType, Integer> intParamsMap, WorldParamType param) throws IllegalArgumentException{
        Integer val = intParamsMap.get(param);
        if(val < 0){
            throw new IllegalArgumentException(
                    param + "must be positive"
            );
        }
    }

    private void mustBeLower(Map<WorldParamType, Integer> intParamsMap, WorldParamType param, Integer upperBorder, String borderLabel) throws IllegalArgumentException{
        Integer val = intParamsMap.get(param);
        if(upperBorder < val){
            throw new IllegalArgumentException(
                    param+ "cannot be geater than" + borderLabel
            );
        }
    }
}
