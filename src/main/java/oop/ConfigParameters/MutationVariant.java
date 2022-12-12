package oop.ConfigParameters;

import java.util.Set;

public enum MutationVariant{
    SLIGHT_REVISION, FULL_RANDOMNESS;

    static MutationVariant parse(int value){
        return switch (value){
            case 0 -> MutationVariant.SLIGHT_REVISION;
            case 1 -> MutationVariant.FULL_RANDOMNESS;
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
        return WorldParamType.MUTATION_VARIANT;
    }
}