package oop.MapInterface;

import oop.ConfigParameters.WorldParamType;

public class MapConstants {
    private final int MAP_HEIGHT;
    private final int MAP_WIDTH;

    private final int INIT_PLANT_COUNT;
    private final int PLANT_ENERGY;
    private final int PLANT_GROWTH_PER_DAY;


    public MapConstants(int mapHeight, int mapWidth, int initPlantCount, int plantEnergy, int plantGrowthPerDay){
        this.MAP_HEIGHT=mapHeight;
        this.MAP_WIDTH=mapWidth;
        this.INIT_PLANT_COUNT=initPlantCount;
        this.PLANT_ENERGY=plantEnergy;
        this.PLANT_GROWTH_PER_DAY=plantGrowthPerDay;
    }
    public int get(WorldParamType paramType) throws IllegalArgumentException{
        return switch(paramType){
            case MAP_HEIGHT -> this.MAP_HEIGHT;
            case MAP_WIDTH -> this.MAP_WIDTH;
            case INIT_PLANT_COUNT -> this.INIT_PLANT_COUNT;
            case PLANT_ENERGY -> this.PLANT_ENERGY;
            case PLANT_GROWTH_RATE -> this.PLANT_GROWTH_PER_DAY;
            default -> throw new IllegalArgumentException();
        };
    }

}
