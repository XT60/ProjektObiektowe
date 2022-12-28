package oop;

import oop.ConfigParameters.AnimalVariant;
import oop.ConfigParameters.MutationVariant;
import oop.ConfigParameters.WorldParamType;
import oop.MapInterface.MapConstants;
import oop.MapInterface.MapObjects.AnimalConstants;
import oop.gui.SimulationWindow;
import oop.MapInterface.MapBorders.IMap;
import oop.MapInterface.MapObjects.Animal;
import oop.MapInterface.PlantsOnMap.IPlant;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class SimulationEngine {

    private final IMap map;
    private final IPlant plantMap;
    private final List<Animal> animalList = new LinkedList<>();

    private final SimulationWindow simulationWindow;



    public SimulationEngine(int numberOfAnimals, IMap map, IPlant plantMap, AnimalVariant animalVariant,
                            MutationVariant mutationVariant, AnimalConstants animalConstants,
                            MapConstants init, SimulationWindow simulationWindow){

        this.map = map;
        this.plantMap = plantMap;
        int height = map.getHeight();
        int width = map.getWidth();
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
    }


    public void run(){
        for(Animal animal : animalList) {
            // remove dead animals
            if (animal.isDead()) {
                map.removeAnimal(animal);
                animalList.remove(animal);
            }
            // move all animals on map
            
//            else {
//                Vector2d newPosition = animal.turn(); // newPositon musi zwrócić Vector2d na jaki zwierze chicałoby wejść
//                if (map.canMoveTo(newPosition)) {
//                    animal.move(map.changePosition(newPosition));
//                } else {
//                    animal.reverse();
//                }
//            }
        }

        // feed all animals
        this.map.feedAnimals(this.plantMap);

        //procreate animals

        // growing all new plants
        int plantGrowthPerDay = this.map.getMapConstants().get(WorldParamType.PLANT_GROWTH_RATE);
        for(int i=0; i<plantGrowthPerDay; i++){
            plantMap.addPlant();
        }
    }
}






// tworzenie granic mapy:
    // IMap map = new GlobeMap(MapConstants)    lub     new PortalMap(MapConstants)
// tworzenie mapy roślin
    // IPlant plants = new ToxicCorpse(MapConstants, AbstractMap.getDeadAnimalHolder)   lub  PlantsEquator(MapConstants)


