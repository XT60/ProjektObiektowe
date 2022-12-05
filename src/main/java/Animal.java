//package agh.ics.oop;
//
//import javafx.scene.layout.VBox;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Animal implements IMapElement{
//    private MapDirection direction;
//    private Vector2d position;
//    private IWorldMap map;
//    private List<IPositionChangeObserver> observers = new ArrayList<IPositionChangeObserver>();
//
//    public Animal(){
//        this.direction = MapDirection.NORTH;
//        this.position = new Vector2d(2,2);
//    }
//
//    public Animal(Vector2d position){
//        this.direction = MapDirection.NORTH;
//        this.position = new Vector2d(position);
//    }
//
//    public Animal (IWorldMap map){
//        Vector2d pos = new Vector2d(2,2);
//        this.map = map;
//        this.direction = MapDirection.NORTH;
//        this.position = pos;
//        if(!map.place(this)){
//             System.out.println("ERROR: couldn't add animal at position " + position.toString());
//        }
//}
//    void addObserver(IPositionChangeObserver observer){
//        this.observers.add(observer);
//    }
//
//    void removeObserver(IPositionChangeObserver observer){
//        this.observers.remove(observer);
//    }
//
//    private void positionChanged(Vector2d oldPosition, Vector2d newPosition){
//        int len = observers.size();
//        for(int i = 0; i < len; i++){
//            IPositionChangeObserver observer = observers.get(i);
//            observer.positionChanged(oldPosition, newPosition);
//        }
//    }
//
//    public Animal(IWorldMap map, Vector2d initialPosition){
//        this.map = map;
//        this.direction = MapDirection.NORTH;
//        this.position = initialPosition;
//        if(! map.place(this)){
//            System.out.println("ERROR: couldn't add animal at position " + position.toString());
//        }
//    }
//
//    public void move(MoveDirection direction){
//        switch(direction){
//            case RIGHT:
//                this.direction = this.direction.next();
//                break;
//            case LEFT:
//                this.direction = this.direction.previous();
//                break;
//            case FORWARD:
//                Vector2d newPos = this.position.add(this.direction.toUnitVector());
//                if (this.map.canMoveTo(newPos)){
//                    positionChanged(this.position, newPos);
//                    this.position = newPos;
//                }
//                break;
//            case BACKWARD:
//                Vector2d nPos = this.position.subtract(this.direction.toUnitVector());
//                if (this.map.canMoveTo(nPos)){
//                    positionChanged(this.position, nPos);
//                    this.position = nPos;
//                }
//                break;
//        }
//    }
//
//    @Override
////    public String toString() {
////        return this.position.toString() + ", " + this.direction.toString();
////    }
//    public String toString() {
//        switch (this.direction){
//            case NORTH:
//                return "N";
//            case SOUTH:
//                return "S";
//            case EAST:
//                return "E";
//            case WEST:
//                return "W";
//        };
//        return "unexpected enum value";
//    }
//
//    public boolean isAt(Vector2d position){
//        return position.equals(this.position);
//    }
//
//    public boolean areDirectionsEquals(MapDirection direction){
//        return this.direction.equals(direction);
//    }
//
//    public boolean arePositionsEquals(Vector2d position){
//        return this.position.equals(position);
//    }
//
//    public Vector2d getPosition(){
//        return new Vector2d(this.position);
//    }
//
//    @Override
//    public String getImageName() {
////        System.out.println(direction);
//        switch (this.direction){
//            case NORTH:
//                return "up.png";
//            case SOUTH:
//                return "down.png";
//            case EAST:
//                return "right.png";
//            case WEST:
//                return "left.png";
//        }
//        return "error";
//    }
//
//    @Override
//    public MapDirection getDirection() {
//        return direction;
//    }
//
//    @Override
//    public int hashCode() {
//        return super.hashCode();
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        return super.equals(obj);
//    }
//}