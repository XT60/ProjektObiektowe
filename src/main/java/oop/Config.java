package oop;

import oop.ConfigParameters.WorldParamType;

import java.util.HashMap;
import java.util.Map;

public class Config {
    public static final int MAX_INT = 2147483647;
    public static final String CONFIG_DIR_PATH = "./ConfigFiles";

    public static final String CSV_FILES_DIR_PATH = "./CSVFiles";

    public static final WorldParamType[] CONFIG_FILE_STRUCTURE = {
            WorldParamType.MAP_HEIGHT,
            WorldParamType.MAP_WIDTH,
            WorldParamType.MAP_VARIANT,
            WorldParamType.INIT_PLANT_COUNT,
            WorldParamType.PLANT_ENERGY,
            WorldParamType.PLANT_GROWTH_RATE,
            WorldParamType.PLANT_VARIANT,
            WorldParamType.INIT_ANIMAL_COUNT,
            WorldParamType.INIT_ANIMAL_ENERGY,
            WorldParamType.REPRODUCTION_ENERGY_THRESHOLD,
            WorldParamType.REPRODUCTION_COST,
            WorldParamType.MIN_MUTATION_COUNT,
            WorldParamType.MAX_MUTATION_COUNT,
            WorldParamType.MUTATION_VARIANT,
            WorldParamType.ANIMAL_GENOME_LENGTH,
            WorldParamType.ANIMAL_VARIANT
    };
}
