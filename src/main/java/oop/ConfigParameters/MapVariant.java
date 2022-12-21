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
}
