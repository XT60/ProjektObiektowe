//
//abstract class AbstractWorldMap implements {
//    Map<Vector2d, IMapElement> elementList = new HashMap<Vector2d, IMapElement>();
//    MapVisualizer visualizer;
//    public AbstractWorldMap(){
//        visualizer = new MapVisualizer(this);
//    }
//
//    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
//        IMapElement movedElement = elementList.get(oldPosition);
//        elementList.remove(oldPosition);
//        elementList.put(newPosition, movedElement);
//    }
//
//    public boolean place(Animal animal) throws IllegalArgumentException{
//        Vector2d currPosition = animal.getPosition();
//        if (canMoveTo(currPosition)){
//            elementList.put(animal.getPosition(), animal);
//            return true;
//        }
//        throw new IllegalArgumentException( currPosition + " is not appropriate position for animal to place");
//    }
//
//    public boolean isOccupied(Vector2d position) {
//        if(objectAt(position) == null){
//            return false;
//        }
//        return true;
//    }
//
//    public Object objectAt(Vector2d position) {
//        return elementList.get(position);
//    }
//
//    protected abstract Vector2d upperRightMapCorner();
//
//    protected abstract Vector2d lowerLeftMapCorner();
//
//    public String toString() {
//        Vector2d lowerLeft = lowerLeftMapCorner();
//        Vector2d upperRight = upperRightMapCorner();
//        return visualizer.draw(lowerLeft, upperRight);
//    }
//}
