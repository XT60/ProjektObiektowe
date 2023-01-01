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
import java.util.*;

import static java.lang.Thread.enumerate;
import static java.lang.Thread.sleep;


public class SimulationEngine implements Runnable {

    int epochCount;
    double epochDuration;
    private final IMap map;
    private final IPlant plantMap;
    private final List<Animal> animalList = new LinkedList<>();

    private boolean pause = false;
    private boolean currentyTrackingAnimal = false;
    Animal trackedAnimal;

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
        this.epochCount = epochCount;
        this.epochDuration = epochDuration;
        this.simulationWindow = simulationWindow;
        Random rand = new Random();
        for (int i = 0; i < numberOfAnimals; i++) {
            int x = rand.nextInt(width);
            int y = rand.nextInt(height);
            Vector2d animalPosition = new Vector2d(x, y);
            Animal animal = new Animal(animalConstants, animalVariant, mutationVariant, animalPosition);
            this.map.addAnimal(animal, animalPosition);
            animalList.add(animal);
        }

        for (int i = 0; i < map.getMapConstants().get(WorldParamType.INIT_PLANT_COUNT); i++) {
            this.plantMap.addPlant();
        }

    }

    public void stopOrResume() {
        pause =! pause;
    }

    public boolean isAnimalTracked(){
        return currentyTrackingAnimal;
    }

    public Animal getTrackedAnimal(){
        return trackedAnimal;
    }

    public void trackAnimal(int x, int y){
        currentyTrackingAnimal = true;
        if(trackedAnimal != null){
            trackedAnimal.endTracking();
        }
        trackedAnimal = this.map.animalAt(new Vector2d(x,y));
        if( trackedAnimal != null){
        trackedAnimal.startTracking();
        }
    }

    public void run() {

        int countOfAnimals;
        int sumOfEnergy = plantMap.getPlantsEnergy() * animalList.size();
        int averageEnergy = sumOfEnergy;

        int sumOfAge = 0;
        int numberOfDeathAnimals = 0;
        int averageAge = 0;
        Platform.runLater(() -> this.simulationWindow.launchSimulationWindow(this.map));

        for (int day = 1; day <= epochCount; day++) {

            while (pause) {
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            countOfAnimals = animalList.size();
            int finalCountOfAnimals = countOfAnimals;

            if (numberOfDeathAnimals != 0) {
                averageAge = sumOfAge / numberOfDeathAnimals;
            }
            int finalAverageAge = averageAge;

            if (countOfAnimals != 0) {
                averageEnergy = sumOfEnergy / countOfAnimals;
            }
            int finalAverageEnergy = averageEnergy;

            Platform.runLater(() -> this.simulationWindow.createMap(this.map, this.plantMap, finalCountOfAnimals, finalAverageEnergy, finalAverageAge));

            try {
                sleep((int) (epochDuration * 1000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            Iterator<Animal> iterator = animalList.iterator();
            sumOfEnergy = 0;
            while (iterator.hasNext()) {

                // remove dead animals
                Animal animal = iterator.next();
                if (animal.isDead()) {
                    sumOfAge += animal.getAge();
                    numberOfDeathAnimals++;
                    map.removeAnimal(animal);
                    iterator.remove();
                }

                // move all animals on map
                else {
                    Vector2d newPosition = animal.turn();
                    if (map.canMoveTo(newPosition)) {
                        animal.move(map.changePosition(animal, newPosition));
                    } else {
                        animal.reverse();
                    }
                }
            }


            // feed all animals
            this.map.feedAnimals(this.plantMap);

//            // procreate animals
            LinkedList<Animal> children = this.map.procreateAnimals();
            animalList.addAll(children);
            children.clear();

            for (Animal animal : animalList) {
                sumOfEnergy += animal.getEnergy();
            }

            // grow all new plants
            int plantGrowthPerDay = this.map.getMapConstants().get(WorldParamType.PLANT_GROWTH_RATE);
            for (int i = 0; i < plantGrowthPerDay; i++) {
                plantMap.addPlant();
            }
            Platform.runLater(() -> this.simulationWindow.createMap(this.map, this.plantMap, finalCountOfAnimals, finalAverageEnergy, finalAverageAge));
        }

    }

}




