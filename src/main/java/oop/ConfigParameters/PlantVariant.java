package oop.ConfigParameters;

public enum PlantVariant implements InputValidation {
    TOXIC_CORPSE, FERTILE_EQUATOR;

    static PlantVariant parse(String inputValue){
        String[][] validValues = {
                {"toxic corpse", "toxic_corpse", "toxic-corpse"},
                {"fertile equator", "fertile_equator", "fertile-equator"}
        };

        int y = InputValidation.getValueIndex(validValues, inputValue);
        if (y == -1){
            throw new IllegalArgumentException(InputValidation.getErrorMessage(validValues, inputValue));
        }
        return PlantVariant.values()[y];
    }
}

