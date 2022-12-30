package oop.MapInterface;


import oop.Vector2d;

public enum MapDirection {
    N(0),
    NE(1),
    E(2),
    SE(3),
    S(4),
    SW(5),
    W(6),
    NW(7);

    private final static MapDirection[] clockWiseOrder = {MapDirection.N, MapDirection.NE, MapDirection.E, MapDirection.SE,
            MapDirection.S, MapDirection.SW, MapDirection.W, MapDirection.NW};
    private final int orderIndex;

    private MapDirection(int orderIndex){
        this.orderIndex = orderIndex;
    };

    @Override
    public String toString() {
        return switch (this) {
            case N -> "north";
            case NE -> "north-east";
            case E -> "east";
            case SE -> "south-east";
            case S -> "south";
            case SW -> "south-west";
            case W -> "west";
            case NW -> "north-west";
        };
    }

    public MapDirection turn(int moveValue){
        return clockWiseOrder[(this.orderIndex + moveValue + clockWiseOrder.length) % clockWiseOrder.length];
    }

    public Vector2d toUnitVector(){
        return switch (this) {
            case N -> new Vector2d(0, -1);
            case NE -> new Vector2d(1, -1);
            case E -> new Vector2d(1, 0);
            case SE -> new Vector2d(1, 1);
            case S -> new Vector2d(0, 1);
            case SW -> new Vector2d(-1, 1);
            case W -> new Vector2d(-1, 0);
            case NW -> new Vector2d(-1, -1);
        };
    }

}
