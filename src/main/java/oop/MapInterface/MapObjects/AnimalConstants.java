package oop.MapInterface.MapObjects;

import oop.ConfigParameters.WorldParamType;

/**
 * unmodifiable animal constants data structure ('map'),
 * one object of that class can be shared between many instances of Animal class
 */
public class AnimalConstants {
    private final int PLANT_ENERGY;
    private final int REPRODUCTION_ENERGY_THRESHOLD;
    private final int REPRODUCTION_COST;
    private final int MIN_MUTATION_COUNT;
    private final int MAX_MUTATION_COUNT;
    private final int ANIMAL_GENOME_LENGTH;

    public AnimalConstants(int plantEnergy, int reproductionEnergyThreshold, int reproductionCost,
                           int minMutationCount, int maxMutationCount, int animalGenomeLength){
        PLANT_ENERGY = plantEnergy;
        REPRODUCTION_ENERGY_THRESHOLD = reproductionEnergyThreshold;
        REPRODUCTION_COST = reproductionCost;
        MIN_MUTATION_COUNT = minMutationCount;
        MAX_MUTATION_COUNT = maxMutationCount;
        ANIMAL_GENOME_LENGTH = animalGenomeLength;
    }

    public int get(WorldParamType paramType) throws IllegalArgumentException{
        return switch(paramType){
            case PLANT_ENERGY -> this.PLANT_ENERGY;
            case REPRODUCTION_ENERGY_THRESHOLD -> this.REPRODUCTION_ENERGY_THRESHOLD;
            case REPRODUCTION_COST -> this.REPRODUCTION_COST;
            case MIN_MUTATION_COUNT -> this.MIN_MUTATION_COUNT;
            case MAX_MUTATION_COUNT -> this.MAX_MUTATION_COUNT;
            case ANIMAL_GENOME_LENGTH -> this.ANIMAL_GENOME_LENGTH;
            default -> throw new IllegalArgumentException();
        };
    }
}
