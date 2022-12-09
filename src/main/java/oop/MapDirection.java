package oop;


public enum MapDirection {
    N, NE, E, SE, S, SW, W, NW;

    @Override
    public String toString() {
        switch (this){
            case N:
                return "north";
            case NE:
                return "north-east";
            case E:
                return "east";
            case SE:
                return "south-east";
            case S:
                return "south";
            case SW:
                return "south-west";
            case W:
                return "west";
            case NW:
                return "north-west";
        }
        return "";
    }

    public MapDirection turned(int moveValue){
        return MapDirection.values()[(moveValue + this.ordinal()) % 8];
    }

    public Vector2d toUnitVector(){
        switch (this){
            case N:
                return new Vector2d(0, -1);
            case NE:
                return new Vector2d(1, -1);
            case E:
                return new Vector2d(1, 0);
            case SE:
                return new Vector2d(1, 1);
            case S:
                return new Vector2d(0, 1);
            case SW:
                return new Vector2d(-1, 1);
            case W:
                return new Vector2d(-1, 0);
            case NW:
                return new Vector2d(-1, -1);
        }
        return null;
    }

}
