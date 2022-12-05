import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class MapDirectionTest {
    @Test
    public void toStringTest(){
        MapDirection[] beginValues = {
                MapDirection.N,
                MapDirection.NE,
                MapDirection.E,
                MapDirection.SE,
                MapDirection.S,
                MapDirection.SW,
                MapDirection.W,
                MapDirection.NW
        };
        int arguments[] = {0, 1, 2, 3, 4, 5, 6, 7};

        MapDirection[] resutls = {
                MapDirection.N,
                MapDirection.E,
                MapDirection.SE,
                MapDirection.W,
                MapDirection.N,
                MapDirection.E,
                MapDirection.S,
                MapDirection.W
        };

        for(int i = 0; i < beginValues.length; i++){
//            Assertions.assertEquals(beginValues[i].turned(arguments[i]));
        }
    }

//    public void validate(MapDirection[] beginValues, int[] arguments, MapDirection[] results){
//
//    }
}
