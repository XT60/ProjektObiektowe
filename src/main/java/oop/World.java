package oop;

import oop.ConfigParameters.*;
import oop.MapInterface.WorldMap;

import java.util.HashMap;
import java.util.Map;

public class World {
    WorldMap map;
    Integer mapHeight;
    Integer mapWidth;
    MapVariant mapVariant;
    Integer initPlantCount;
    Integer plantEnergy;
    Integer plantGrowthRate;
    PlantVariant plantVariant;
    Integer initAnimalCount;
    Integer initAnimalEnergy;
    Integer reproductionEnergyThreshold;
    Integer reproductionCost;
    Integer minMutationCount;
    Integer maxMutationCount;
    MutationVariant mutationVariant;
    Integer animalGenomeLength;
    AnimalVariant animalVariant;

    public World (Map<WorldParamType, String> paramData) throws IllegalArgumentException{
        // parse data to variables
        Map<WorldParamType, Object> paramValues = new HashMap<WorldParamType, Object>();
        for (Map.Entry<WorldParamType, String> entry : paramData.entrySet()) {
            paramValues.put(entry.getKey(), entry.getKey().parse(entry.getValue()));
        }

        // check consistency of variables (throw exceptions)
        WorldParamType[] intParamTypes = {
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
        Map<WorldParamType, Integer> intParamsMap = new HashMap<>();
        for (WorldParamType param : intParamTypes){
            Integer val = (Integer)paramValues.get(param);
            intParamsMap.put(param, val);
        }
        checkConsistency(intParamsMap);

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
