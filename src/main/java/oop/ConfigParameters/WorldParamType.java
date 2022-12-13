package oop.ConfigParameters;

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
            case REPRODUCTION_ENERGY_THRESHOLD -> "Reproduction energy threshold";
            case REPRODUCTION_COST -> "Reproduction cost";
            case MIN_MUTATION_COUNT -> "Min mutation count";
            case MAX_MUTATION_COUNT -> "Max mutation count";
            case MUTATION_VARIANT -> "Mutation variant";
            case ANIMAL_GENOME_LENGTH -> "Animal genome length";
            case ANIMAL_VARIANT -> "Animal variant";
        };

    }

    /**
     * check validity of a value (doesn't check if it is valid in relation to other values)
     * @param value
     * @return
     * @throws IllegalArgumentException
     */
    private boolean mustBeValid(int value) throws IllegalArgumentException{
        return switch (this){
            case MAP_HEIGHT, MAP_WIDTH , INIT_PLANT_COUNT, PLANT_ENERGY, PLANT_GROWTH_RATE, INIT_ANIMAL_COUNT,
                    INIT_ANIMAL_ENERGY, REPRODUCTION_ENERGY_THRESHOLD, REPRODUCTION_COST, MIN_MUTATION_COUNT,
                    MAX_MUTATION_COUNT, ANIMAL_GENOME_LENGTH -> mustBePositive(value);
            case PLANT_VARIANT -> PlantVariant.mustBeValid(value);
            case MUTATION_VARIANT -> MutationVariant.mustBeValid(value);
            case MAP_VARIANT -> MapVariant.mustBeValid(value);
            case ANIMAL_VARIANT -> AnimalVariant.mustBeValid(value);
        };
    }

    private boolean mustBePositive (int value) throws IllegalArgumentException{
        if (value < 0){
            throw new IllegalArgumentException(this + " must be positive");
        }
        return true;
    }

    public Object parse(int value) throws IllegalArgumentException{
        this.mustBeValid(value);
        return switch(this){
            case MAP_HEIGHT, MAP_WIDTH,
                    INIT_PLANT_COUNT, PLANT_ENERGY, PLANT_GROWTH_RATE,
                    INIT_ANIMAL_COUNT, INIT_ANIMAL_ENERGY, REPRODUCTION_ENERGY_THRESHOLD, REPRODUCTION_COST,
                    MIN_MUTATION_COUNT, MAX_MUTATION_COUNT, ANIMAL_GENOME_LENGTH
                    -> Integer.valueOf(value);
            case MAP_VARIANT -> MapVariant.parse(value);
            case PLANT_VARIANT -> PlantVariant.parse(value);
            case MUTATION_VARIANT -> MutationVariant.parse(value);
            case ANIMAL_VARIANT -> AnimalVariant.parse(value);
        };
    }
}

