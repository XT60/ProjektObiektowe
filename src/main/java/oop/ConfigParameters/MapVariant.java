package oop.ConfigParameters;

public enum MapVariant{
    GLOBE, PORTAL;

     static MapVariant parse(int value){
        return switch (value){
            case 0 -> MapVariant.GLOBE;
            case 1 -> MapVariant.PORTAL;
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
        return WorldParamType.MAP_VARIANT;
    }
}
