package oop;

public enum MutationVariant implements InputValidation{
    SLIGHT_REVISION, FULL_RANDOMNESS;

    public PlantVariant parse(String inputValue){
        String[][] validValues = {
                {"slight revision", "slight_revision", "slight-revision"},
                {"full randomness", "full_randomness", "full-randomness"}
        };

        int y = InputValidation.getValueIndex(validValues, inputValue);
        return y != -1 ? PlantVariant.values()[y] : null;
    }
}
