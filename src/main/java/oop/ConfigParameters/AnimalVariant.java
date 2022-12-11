package oop.ConfigParameters;

public enum AnimalVariant implements InputValidation {
    PREDESTINAION, SOME_CRAZYNESS;

    static AnimalVariant parse(String inputValue){
        String[][] validValues = {
                {"predestination"},
                {"some crazyness", "some_crazyness", "some-crazyness"}
        };

        int y = InputValidation.getValueIndex(validValues, inputValue);
        if (y == -1){
            throw new IllegalArgumentException(InputValidation.getErrorMessage(validValues, inputValue));
        }
        return AnimalVariant.values()[y];
    }
}
