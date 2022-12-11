package oop;

import oop.MapInterface.MapDirection;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class MapDirectionTest {
    @Test
    public void turnedTest(){
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
                MapDirection.S,
                MapDirection.W,
                MapDirection.N,
                MapDirection.E,
                MapDirection.S,
                MapDirection.W
        };

        for(int i = 0; i < beginValues.length; i++){
            assertTrue(beginValues[i].turned(arguments[i]) == resutls[i]);
        }
    }
}
