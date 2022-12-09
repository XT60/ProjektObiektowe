package oop;

abstract class MapInhabitant {
    protected Vector2d position;

    public Vector2d getPosition() {
        return new Vector2d(position);
    }

    public void setPosition(Vector2d position) {
        this.position = position;
    }


}
