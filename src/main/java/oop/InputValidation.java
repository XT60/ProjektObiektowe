package oop;

public interface InputValidation {
    public Object parse(String inputValue);

    static int getValueIndex(String[][] validValues, String inputValue){
        String loweredInput = inputValue.toLowerCase();
        for(int y = 0; y < validValues.length; y++){
            for(int x = 0; x < validValues[y].length; x++){
                if (loweredInput.equals(validValues[y][x])){
                    return y;
                }
            }
        }
        return -1;
    }
}
