package oop.ConfigParameters;

public enum PlantVariant{
    TOXIC_CORPSE, FERTILE_EQUATOR;

    static PlantVariant parse(int value){
        return switch (value){
            case 0 -> PlantVariant.TOXIC_CORPSE;
            case 1 -> PlantVariant.FERTILE_EQUATOR;
            default -> null;
        };
    }
    static boolean mustBeValid(int value) throws IllegalArgumentException{
        if (value != 0 && value != 1){
            throw new IllegalArgumentException("value for " + getParamType() + "has to be 0 or 1");
        }
        return true;
    }

    static WorldParamType getParamType(){
        return WorldParamType.PLANT_VARIANT;
    }
}

