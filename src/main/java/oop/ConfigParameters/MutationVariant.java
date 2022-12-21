package oop.ConfigParameters;

public enum MutationVariant{
    SLIGHT_CORRECTION, FULL_RANDOMNESS;

    static MutationVariant parse(int value){
        return switch (value){
            case 0 -> MutationVariant.SLIGHT_CORRECTION;
            case 1 -> MutationVariant.FULL_RANDOMNESS;
            default -> null;
        };
    }


}