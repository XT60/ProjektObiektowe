package oop.ConfigParameters;

public enum AnimalVariant{
    PREDESTINAION, SOME_CRAZYNESS;

    static AnimalVariant parse(int value){
        return switch (value){
            case 0 -> AnimalVariant.PREDESTINAION;
            case 1 -> AnimalVariant.SOME_CRAZYNESS;
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
        return WorldParamType.ANIMAL_VARIANT;
    }
}
