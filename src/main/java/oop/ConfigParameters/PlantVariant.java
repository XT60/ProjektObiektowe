package oop.ConfigParameters;

import oop.Vector2d;

public enum PlantVariant{
    TOXIC_CORPSE,
    FERTILE_EQUATOR;

    static PlantVariant parse(int value){
        return switch (value){
            case 0 -> PlantVariant.TOXIC_CORPSE;
            case 1 -> PlantVariant.FERTILE_EQUATOR;
            default -> null;
        };
    }
}

