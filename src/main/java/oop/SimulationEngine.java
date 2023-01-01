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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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

    private final int[] popularGenoms = new int[8];

    int popularGen=0;


    private int day = 1;
    private final SimulationWindow simulationWindow;

    private String csvFilePath = null;

    public SimulationEngine(int numberOfAnimals, IMap map, IPlant plantMap, AnimalVariant animalVariant,
                            MutationVariant mutationVariant, AnimalConstants animalConstants,
                            SimulationWindow simulationWindow, int epochCount, double epochDuration, String csvFilePath) throws FileNotFoundException {
        this(numberOfAnimals, map, plantMap, animalVariant,
                mutationVariant, animalConstants, simulationWindow, epochCount, epochDuration);
        this.csvFilePath = csvFilePath;
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
        trackedAnimal = this.map.animalAt(new Vector2d(x,y), true);
        if( trackedAnimal != null){
        trackedAnimal.startTracking();
        }
    }

    public int getPopularGen(){
        return this.popularGen;
    }

    public void run() {

        int countOfAnimals = 0;
        int sumOfEnergy = plantMap.getPlantsEnergy() * animalList.size();
        int averageEnergy = sumOfEnergy;

        int sumOfAge = 0;
        int numberOfDeathAnimals = 0;
        int averageAge = 0;
        Platform.runLater(() -> this.simulationWindow.launchSimulationWindow(this.map));

        for (; day <= epochCount; day++) {
            for(int i =0 ; i<8; i++)
                popularGenoms[i]=0;
            if(pause){
                Platform.runLater(() -> this.simulationWindow.createPauseMap(this.map,popularGen));
            }

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

            Iterator<Animal> iterator = animalList.iterator();
            sumOfEnergy = 0;
            while (iterator.hasNext()) {

                // remove dead animals
                Animal animal = iterator.next();
                if (animal.isDead()) {
                    sumOfAge += animal.getAge();
                    numberOfDeathAnimals++;
                    map.removeAnimal(animal);
                    animal.setDateOfDeath(day);
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
                    popularGenoms[animal.getCurrentGen()]++;
                }
            }
            int tmp =0;
            for(int i=0; i<8; i++){
                if (tmp<popularGenoms[i]){
                        tmp=popularGenoms[i];
                        popularGen=i;
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

            try {
                sleep((int) (epochDuration * 1000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        if (this.csvFilePath != null){
            saveToCsv(this.csvFilePath, countOfAnimals, plantMap.getNumberOfPlants(), simulationWindow.getFreeSpaces(),
                    popularGen, averageEnergy, averageAge);
        }
    }

    private void saveToCsv(String csvFilePath, int countOfAnimals, int countOfPlants, int freePlacesCount,
                           int mostPopularGenome, int avgEnergy, int avgLifeSpan){
        File csvFile = new File(csvFilePath);
        if (csvFile.exists()){
            throw new IllegalArgumentException("There already is file: " + csvFilePath);
        }

        // Create a String with the contents of the CSV file
        String csv =
            "liczba wszystkich zwierząt," + Integer.toString(countOfAnimals) + '\n' +
            "liczby wszystkich roślin," + Integer.toString(countOfPlants) + '\n' +
            "liczba wolnych pól," +  Integer.toString(freePlacesCount) + '\n' +
            "najpopularniejszy genotyp, " + Integer.toString(mostPopularGenome) + '\n' +
            "średniego poziomu energii dla żyjących zwierząt, " + Integer.toString(avgEnergy) + '\n' +
            "średniej długości życia zwierząt dla martwych zwierząt," + Integer.toString(avgLifeSpan);

        try {
            // Create a FileWriter and specify the file path and name
            FileWriter writer = new FileWriter(csvFile);

            // Use the write() method to write the contents of the CSV file to the file
            writer.write(csv);

            // Close the FileWriter
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void stop(){
        day = epochCount;
    }

}




