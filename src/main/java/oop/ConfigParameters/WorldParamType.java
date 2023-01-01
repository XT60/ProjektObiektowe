package oop.ConfigParameters;

import oop.Vector2d;

public enum WorldParamType {
    MAP_HEIGHT, MAP_WIDTH, MAP_VARIANT,
    INIT_PLANT_COUNT, PLANT_ENERGY, PLANT_GROWTH_RATE, PLANT_VARIANT,
    INIT_ANIMAL_COUNT, INIT_ANIMAL_ENERGY, REPRODUCTION_ENERGY_THRESHOLD,
    REPRODUCTION_COST, MIN_MUTATION_COUNT, MAX_MUTATION_COUNT, MUTATION_VARIANT,
    ANIMAL_GENOME_LENGTH, ANIMAL_VARIANT;



    public String getKey(){
        return switch(this){
            case MAP_HEIGHT -> "MAP_HEIGHT";
            case MAP_WIDTH -> "MAP_WIDTH";
            case MAP_VARIANT -> "MAP_VARIANT";
            case INIT_PLANT_COUNT -> "INIT_PLANT_COUNT";
            case PLANT_ENERGY -> "PLANT_ENERGY";
            case PLANT_GROWTH_RATE -> "PLANT_GROWTH_RATE";
            case PLANT_VARIANT -> "PLANT_VARIANT";
            case INIT_ANIMAL_COUNT -> "INIT_ANIMAL_COUNT";
            case INIT_ANIMAL_ENERGY -> "INIT_ANIMAL_ENERGY";
            case REPRODUCTION_ENERGY_THRESHOLD -> "REPRODUCTION_ENERGY_THRESHOLD";
            case REPRODUCTION_COST -> "REPRODUCTION_COST";
            case MIN_MUTATION_COUNT -> "MIN_MUTATION_COUNT";
            case MAX_MUTATION_COUNT -> "MAX_MUTATION_COUNT";
            case MUTATION_VARIANT -> "MUTATION_VARIANT";
            case ANIMAL_GENOME_LENGTH -> "ANIMAL_GENOME_LENGTH";
            case ANIMAL_VARIANT -> "ANIMAL_VARIANT";
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
            case REPRODUCTION_ENERGY_THRESHOLD -> "Reproduction energy threshold";
            case REPRODUCTION_COST -> "Reproduction cost";
            case MIN_MUTATION_COUNT -> "Min mutation count";
            case MAX_MUTATION_COUNT -> "Max mutation count";
            case MUTATION_VARIANT -> "Mutation variant";
            case ANIMAL_GENOME_LENGTH -> "Animal genome length";
            case ANIMAL_VARIANT -> "Animal variant";
        };
    }

    // variant ranges should be retrieved from variant classes
    /**
     * get range of permitted values for variable type
     * @return      range vector
     */
    public Vector2d getValueRange(){
        return switch(this){
            case MAP_VARIANT,
                    PLANT_VARIANT,
                    MUTATION_VARIANT,
                    ANIMAL_VARIANT -> new Vector2d(0, 1);
            case PLANT_ENERGY,
                    PLANT_GROWTH_RATE,
                    INIT_ANIMAL_ENERGY,
                    REPRODUCTION_ENERGY_THRESHOLD,
                    REPRODUCTION_COST,
                    MIN_MUTATION_COUNT,
                    MAX_MUTATION_COUNT,
                    ANIMAL_GENOME_LENGTH -> new Vector2d(0, 20);
            case INIT_ANIMAL_COUNT, MAP_HEIGHT, MAP_WIDTH, INIT_PLANT_COUNT -> new Vector2d(0, 50);
        };
    }

    public int getDefaultValue(){
        return switch(this){
            case MAP_HEIGHT,
                    INIT_ANIMAL_ENERGY,
                    MAP_WIDTH -> 10;
            case MAP_VARIANT,
                    PLANT_VARIANT,
                    MIN_MUTATION_COUNT,
                    MUTATION_VARIANT,
                    ANIMAL_VARIANT -> 0;
            case INIT_PLANT_COUNT,
                    REPRODUCTION_COST,
                    MAX_MUTATION_COUNT -> 2;
            case PLANT_ENERGY,
                    PLANT_GROWTH_RATE,
                    REPRODUCTION_ENERGY_THRESHOLD -> 1;
            case INIT_ANIMAL_COUNT -> 3;
            case ANIMAL_GENOME_LENGTH -> 7;
        };
    }


    /**
     * check validity of a value (doesn't check if it's valid in relation to other values)
     * @param value                         param value
     * @throws IllegalArgumentException     if value is not in expected range
     */
    private void mustBeValid(int value) throws IllegalArgumentException{
        Vector2d range = this.getValueRange();
        if (value < range.x || range.y < value){
            throw new IllegalArgumentException(this + "must be in range: " + range);
        }
    }


    public Object parse(int value) throws IllegalArgumentException{
        this.mustBeValid(value);
        return switch(this){
            case MAP_HEIGHT, MAP_WIDTH,
                    INIT_PLANT_COUNT, PLANT_ENERGY, PLANT_GROWTH_RATE,
                    INIT_ANIMAL_COUNT, INIT_ANIMAL_ENERGY, REPRODUCTION_ENERGY_THRESHOLD, REPRODUCTION_COST,
                    MIN_MUTATION_COUNT, MAX_MUTATION_COUNT, ANIMAL_GENOME_LENGTH
                    -> value;
            case MAP_VARIANT -> MapVariant.parse(value);
            case PLANT_VARIANT -> PlantVariant.parse(value);
            case MUTATION_VARIANT -> MutationVariant.parse(value);
            case ANIMAL_VARIANT -> AnimalVariant.parse(value);
        };
    }
}

