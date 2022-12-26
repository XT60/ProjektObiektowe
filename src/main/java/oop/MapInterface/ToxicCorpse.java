package oop.MapInterface;

import oop.ConfigParameters.WorldParamType;
import oop.MapInterface.MapObjects.Plant;
import oop.Vector2d;

import java.util.LinkedList;
import java.util.Random;

public class ToxicCorpse implements IPlant{
    PlantsHolder plants = new PlantsHolder();
    MapConstants mapConstants;

    DeadAnimalsHolder deadAnimalsHolder;

    private final int mapHeight;
    private final int mapWidth;
    
    public ToxicCorpse(MapConstants mapConstants, DeadAnimalsHolder deadAnimalsHolder){
        this.mapConstants=mapConstants;
        this.deadAnimalsHolder=deadAnimalsHolder;
        this.mapHeight = mapConstants.get(WorldParamType.MAP_HEIGHT);
        this.mapWidth = mapConstants.get(WorldParamType.MAP_WIDTH);
    }


    public void addPlant() {
        Random rand = new Random();
        int randomInt = rand.nextInt(10);
        Vector2d newPlantPosition;
        if (randomInt<=7) {
            newPlantPosition = deadAnimalsHolder.getPreferredField();
        }
        else{
            newPlantPosition = deadAnimalsHolder.getOtherField();
        }
        if(newPlantPosition != null){ plants.add(new Plant(newPlantPosition));}
    }

    public void removePlant(Plant plant){
        plants.removePlant(plant);
        deadAnimalsHolder.freePosition(plant.getPosition());
    }

    public boolean isPlantAtPosition(Vector2d position){
        return plants.isPlantAtPosition(position);
    }
}
