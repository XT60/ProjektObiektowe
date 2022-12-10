package oop;

public enum AnimalVariant implements InputValidation {
    PREDESTINAION, SOME_CRAZYNESS;

    public PlantVariant parse(String inputValue){
        String[][] validValues = {
                {"predestination"},
                {"some crazyness", "some_crazyness", "some-crazyness"}
        };

        int y = InputValidation.getValueIndex(validValues, inputValue);
        return y != -1 ? PlantVariant.values()[y] : null;
    }
}
