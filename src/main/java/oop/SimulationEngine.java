package oop;

import javafx.application.Platform;
import oop.ConfigParameters.AnimalVariant;
import oop.ConfigParameters.MutationVariant;
import oop.ConfigParameters.WorldParamType;
import oop.MapInterface.MapConstants;
import oop.MapInterface.MapObjects.AnimalConstants;
import oop.gui.SimulationWindow;
import oop.MapInterface.MapBorders.IMap;
import oop.MapInterface.MapObjects.Animal;
import oop.MapInterface.PlantsOnMap.IPlant;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import static java.lang.Thread.sleep;


public class SimulationEngine implements Runnable{

    int epochCount;
    double epochDuration;
    private final IMap map;
    private final IPlant plantMap;
    private final List<Animal> animalList = new LinkedList<>();

    private final SimulationWindow simulationWindow;

    public SimulationEngine(int numberOfAnimals, IMap map, IPlant plantMap, AnimalVariant animalVariant,
                            MutationVariant mutationVariant, AnimalConstants animalConstants,
                            SimulationWindow simulationWindow, int epochCount, double epochDuration, String csvFilePath) throws FileNotFoundException {
        this(numberOfAnimals, map, plantMap, animalVariant,
                mutationVariant, animalConstants, simulationWindow, epochCount, epochDuration);
        // csvFilePath
    }

    public SimulationEngine(int numberOfAnimals, IMap map, IPlant plantMap, AnimalVariant animalVariant,
                            MutationVariant mutationVariant, AnimalConstants animalConstants,
                            SimulationWindow simulationWindow, int epochCount, double epochDuration) throws FileNotFoundException {

        this.map = map;
        this.plantMap = plantMap;
        int height = map.getHeight();
        int width = map.getWidth();
        this.epochCount=epochCount;
        this.epochDuration=epochDuration;
        this.simulationWindow = simulationWindow;
        Random rand = new Random();
        for(int i=0; i<numberOfAnimals; i++){
            int x=rand.nextInt(width);
            int y=rand.nextInt(height);
            Vector2d animalPosition = new Vector2d(x,y);
            Animal animal = new Animal(animalConstants,animalVariant,mutationVariant,animalPosition);
            this.map.addAnimal(animal,animalPosition);
            animalList.add(animal);
        }

        for(int i = 0; i<map.getMapConstants().get(WorldParamType.INIT_PLANT_COUNT); i++){
            this.plantMap.addPlant();
        }

//        simulationWindow.launchSimulationWindow(this.map,this.plantMap);
//        Thread engineThread = new Thread(this::run);
//        engineThread.start();
            run();
    }

    public void run() {
        System.out.println("TAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        try {
            this.simulationWindow.launchSimulationWindow(this.map,this.plantMap);
            System.out.println("NIEEEEEEEEEEEEEEEEEEEE");

        } catch (FileNotFoundException e) {
            System.out.println("TUTAJ SIE WYWALA");
        }
//        Platform.runLater( () -> {
//            try {
//                this.simulationWindow.createMap(this.map,this.plantMap);
//                sleep(0);
//            } catch (FileNotFoundException | InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        });
        System.out.println("KURWAAAAAAAAAAAAAAAAAA");


//        for(Animal animal : animalList) {
//            // remove dead animals
//            if (animal.isDead()) {
//                map.removeAnimal(animal);
//                animalList.remove(animal);
//            }
//            // move all animals on map
//
//            else {
//                Vector2d newPosition = animal.turn(); // newPositon musi zwrócić Vector2d na jaki zwierze chicałoby wejść
//                if (map.canMoveTo(newPosition)) {
//                    animal.move(map.changePosition(animal,newPosition));
//                } else {
//                    animal.reverse();
//                }
//            }
//        }
////
//        // feed all animals
//        this.map.feedAnimals(this.plantMap);
//
//        //procreate animals
//
//        // growing all new plants
//        int plantGrowthPerDay = this.map.getMapConstants().get(WorldParamType.PLANT_GROWTH_RATE);
//        for(int i=0; i<plantGrowthPerDay; i++){
//            plantMap.addPlant();
//        }
    }
}


