package oop.ConfigParameters;

public enum MutationVariant implements InputValidation {
    SLIGHT_REVISION, FULL_RANDOMNESS;

    static MutationVariant parse(String inputValue) throws IllegalArgumentException{
        String[][] validValues = {
                {"slight revision", "slight_revision", "slight-revision"},
                {"full randomness", "full_randomness", "full-randomness"}
        };

        int y = InputValidation.getValueIndex(validValues, inputValue);
        if (y == -1){
            throw new IllegalArgumentException(InputValidation.getErrorMessage(validValues, inputValue));
        }
        return MutationVariant.values()[y];
    }

}