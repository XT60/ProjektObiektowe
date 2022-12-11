package oop.ConfigParameters;

public interface InputValidation {
    static Object parse(String inputValue){
        return null;
    };

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

    static String getErrorMessage(String[][] validValues, String inputValue){
        String message = "Value assigned to MutationVariant: " + inputValue + "is none of: ";
        for(String[] strArr : validValues){
            for(String str : strArr){
                message += str + ", ";
            }
        }
        return message.substring(0, message.length() - 2);
    }
}
