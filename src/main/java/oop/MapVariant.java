package oop;

public enum MapVariant implements InputValidation {
    GLOBE, PORTAL;

    public PlantVariant parse(String inputValue){
        String[][] validValues = {
                {"globe"},
                {"portal"}
        };

        int y = InputValidation.getValueIndex(validValues, inputValue);
        return y != -1 ? PlantVariant.values()[y] : null;
    }
}
