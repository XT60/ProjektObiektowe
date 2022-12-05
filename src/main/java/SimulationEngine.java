//
//
//public class SimulationEngine implements IEngine, Runnable{
//    protected MoveDirection[] directions;
//    private IWorldMap map;
//    private Vector2d[] initialPositions;
//    public List<Animal> animalList = new ArrayList<Animal>();
//    private IFrameChangeObserver gui;
//    public int moveDelay;
//
//    public SimulationEngine(MoveDirection[] directions, AbstractWorldMap map, Vector2d[] initialPositions){
//        moveDelay = 300;
//        this.directions = directions;
//        this.map = map;
//        this.initialPositions = initialPositions;
//        for (int i = 0; i < initialPositions.length; i ++){
//            Vector2d initialPos = initialPositions[i];
//            Animal animal = new Animal(map, initialPos);
//            animal.addObserver(map);
//            this.animalList.add(animal);
//        }
//    }
//
//    public void setObserver(IFrameChangeObserver observer){
//        gui = observer;
//    }
//
//    public List<Animal> getAnimalList(){
//        return animalList;
//    }
//
//    @Override
//    public void run() {
//        int i = 0;
//        if (i >= directions.length){
//            return;
//        }
//        while(this.animalList.size() > 0){
//            for(int a = 0; a < this.animalList.size(); a++){
//                try{
////                    System.out.println((moveDelay));
//                    Thread.sleep(moveDelay);
//                }
//                catch (InterruptedException e){
//                    System.out.println(e.toString());
//                }
//                Animal animal = this.animalList.get(a);
//                animal.move(directions[i]);
//                i++;
//                Platform.runLater(() -> { gui.newFrame(animal); });
//                if (i >= directions.length){
//                    return;
//                }
//            }
//        }
//    }
//}
