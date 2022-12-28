package oop.MapInterface;

import oop.Vector2d;

public interface IMapElement {
    Vector2d getPosition();
    String toString();

    String getView();
    String label();
}
