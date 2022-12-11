package oop.ConfigParameters;

public enum MapVariant implements InputValidation {
    GLOBE, PORTAL;

     static MapVariant parse(String inputValue){
        String[][] validValues = {
                {"globe"},
                {"portal"}
        };

        int y = InputValidation.getValueIndex(validValues, inputValue);
        if (y == -1){
            throw new IllegalArgumentException(InputValidation.getErrorMessage(validValues, inputValue));
        }
        return MapVariant.values()[y];
    }
}
