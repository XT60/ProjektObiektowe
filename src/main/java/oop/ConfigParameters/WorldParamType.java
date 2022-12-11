package oop.ConfigParameters;

import oop.Config;
import oop.Vector2d;

import oop.ConfigParameters.*;
import oop.MapInterface.WorldMap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;


public enum WorldParamType {
    MAP_HEIGHT, MAP_WIDTH, MAP_VARIANT,
    INIT_PLANT_COUNT, PLANT_ENERGY, PLANT_GROWTH_RATE, PLANT_VARIANT,
    INIT_ANIMAL_COUNT, INIT_ANIMAL_ENERGY, REPRODUCTION_ENERGY_THRESHOLD,
    REPRODUCTION_COST, MIN_MUTATION_COUNT, MAX_MUTATION_COUNT, MUTATION_VARIANT,
    ANIMAL_GENOME_LENGTH, ANIMAL_VARIANT;


    public String getDescription(){
        return switch(this){
            case MAP_HEIGHT -> "map height";
            case MAP_WIDTH -> "map width";
            case MAP_VARIANT -> "map border variant";
            case INIT_PLANT_COUNT -> "initial plant count ";
            case PLANT_ENERGY -> "amount of energy provided by one plant";
            case PLANT_GROWTH_RATE -> "amount of plants that appear every day";
            case PLANT_VARIANT -> "plant growth variant";
            case INIT_ANIMAL_COUNT -> "initial animal population";
            case INIT_ANIMAL_ENERGY -> "initial animal energy";
            case REPRODUCTION_ENERGY_THRESHOLD -> "energy needed to start procreation";
            case REPRODUCTION_COST -> "procreation energy cost";
            case MIN_MUTATION_COUNT -> "minimal mutation count";
            case MAX_MUTATION_COUNT -> "maximal mutation count";
            case MUTATION_VARIANT -> "mutation variant";
            case ANIMAL_GENOME_LENGTH -> "animal genome length";
            case ANIMAL_VARIANT -> "animal behaviour variant";
        };
    }

    public String toString(){
        return switch (this){
            case MAP_HEIGHT -> "Map height";
            case MAP_WIDTH -> "Map width";
            case MAP_VARIANT -> "Map variant";
            case INIT_PLANT_COUNT -> "Init plant count";
            case PLANT_ENERGY -> "Plant energy";
            case PLANT_GROWTH_RATE -> "Plant growth rate";
            case PLANT_VARIANT -> "Plant variant";
            case INIT_ANIMAL_COUNT -> "Init animal count";
            case INIT_ANIMAL_ENERGY -> "Init animal energy";
            case REPRODUCTION_ENERGY_THRESHOLD -> "Reproduction energy treshold";
            case REPRODUCTION_COST -> "Reproduction cost";
            case MIN_MUTATION_COUNT -> "Min mutation count";
            case MAX_MUTATION_COUNT -> "Max mutation count";
            case MUTATION_VARIANT -> "Mutation variant";
            case ANIMAL_GENOME_LENGTH -> "Animal genome length";
            case ANIMAL_VARIANT -> "Animal variant";
        };

    }


    public Object parse(String text) throws IllegalArgumentException{
        return switch(this){
            case MAP_HEIGHT, MAP_WIDTH,
                    INIT_PLANT_COUNT, PLANT_ENERGY, PLANT_GROWTH_RATE,
                    INIT_ANIMAL_COUNT, INIT_ANIMAL_ENERGY, REPRODUCTION_ENERGY_THRESHOLD, REPRODUCTION_COST,
                    MIN_MUTATION_COUNT, MAX_MUTATION_COUNT, ANIMAL_GENOME_LENGTH
                    -> Integer.parseInt(text);
            case MAP_VARIANT -> MapVariant.parse(text);
            case PLANT_VARIANT -> PlantVariant.parse(text);
            case MUTATION_VARIANT -> MutationVariant.parse(text);
            case ANIMAL_VARIANT -> AnimalVariant.parse(text);
        };
    }


    private Vector2d getRange(){
        return switch(this){
            case MAP_HEIGHT, MAP_WIDTH,
                    INIT_PLANT_COUNT, PLANT_ENERGY, PLANT_GROWTH_RATE,
                    INIT_ANIMAL_COUNT, INIT_ANIMAL_ENERGY, REPRODUCTION_ENERGY_THRESHOLD, REPRODUCTION_COST,
                    MIN_MUTATION_COUNT, MAX_MUTATION_COUNT, ANIMAL_GENOME_LENGTH
                    -> new Vector2d(0, Config.MAX_INT);
            case MAP_VARIANT -> new Vector2d(0, MapVariant.values().length);
            case PLANT_VARIANT -> new Vector2d(0, PlantVariant.values().length);
            case MUTATION_VARIANT -> new Vector2d(0, MutationVariant.values().length);
            case ANIMAL_VARIANT -> new Vector2d(0, AnimalVariant.values().length);
        };
    }






//    public String toString(){
//        return switch (this){
//            case MAP_HEIGHT -> "MapHeight";
//            case MAP_WIDTH -> "MapWidth";
//            case MAP_VARIANT -> "MapVariant";
//            case INIT_PLANT_COUNT -> "InitPlantCount";
//            case PLANT_ENERGY -> "PlantEnergy";
//            case PLANT_GROWTH_RATE -> "PlantGrowthRate";
//            case PLANT_VARIANT -> "PlantVariant";
//            case INIT_ANIMAL_COUNT -> "InitAnimalCount";
//            case INIT_ANIMAL_ENERGY -> "InitAnimalEnergy";
//            case REPRODUCTION_ENERGY_THRESHOLD -> "ReproductionEnergyTreshold";
//            case REPRODUCTION_COST -> "ReproductionCost";
//            case MIN_MUTATION_COUNT -> "MinMutationCount";
//            case MAX_MUTATION_COUNT -> "MaxMutationCount";
//            case MUTATION_VARIANT -> "MutationVariant";
//            case ANIMAL_GENOME_LENGTH -> "AnimalGenomeLength";
//            case ANIMAL_VARIANT -> "AnimalVariant";
//        };
//    }

}

