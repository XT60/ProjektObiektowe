package oop.ConfigParameters;

public enum AnimalVariant{
    PREDESTINATION, SOME_CRAZINESS;

    static AnimalVariant parse(int value){
        return switch (value){
            case 0 -> AnimalVariant.PREDESTINATION;
            case 1 -> AnimalVariant.SOME_CRAZINESS;
            default -> null;
        };
    }
}
