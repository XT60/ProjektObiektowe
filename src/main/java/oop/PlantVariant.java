package oop;

public enum PlantVariant implements InputValidation{
    TOXIC_CORPSE, FERTILE_EQUATOR;

    public PlantVariant parse(String inputValue){
        String[][] validValues = {
                {"toxic corpse", "toxic_corpse", "toxic-corpse"},
                {"fertile equator", "fertile_equator", "fertile-equator"}
        };

        int y = InputValidation.getValueIndex(validValues, inputValue);
        return y != -1 ? PlantVariant.values()[y] : null;
    }
}

