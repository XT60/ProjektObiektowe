//
//public class OptionsParser {
//    public MoveDirection[] parse(String[] args ) throws IllegalArgumentException{
//        int n = args.length;
//        MoveDirection[] data = new MoveDirection[n];
//        int currIndex = 0;
//        for(int i = 0; i < n; i++){
//            switch(args[i]) {
//                case "f", "forward":
//                    data[currIndex] = MoveDirection.FORWARD;
//                    currIndex ++;
//                    break;
//                case "b", "backward":
//                    data[currIndex] = MoveDirection.BACKWARD;
//                    currIndex ++;
//                    break;
//                case "l", "left":
//                    data[currIndex] = MoveDirection.LEFT;
//                    currIndex ++;
//                    break;
//                case "r", "right":
//                    data[currIndex] = MoveDirection.RIGHT;
//                    currIndex ++;
//                    break;
//                default:
//                    throw new IllegalArgumentException("\"" + args[i] + "\" is not allowed move specification");
//            }
//        }
//        if (currIndex == n){
//            return data;
//        }
//        MoveDirection[] result = new MoveDirection[currIndex];
//        for(int i = 0; i < currIndex; i++){
//            result[i] = data[i];
//        }
//        return result;
//    }
//}
