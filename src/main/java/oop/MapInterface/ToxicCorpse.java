package oop.MapInterface;

import oop.ConfigParameters.WorldParamType;
import oop.MapInterface.MapObjects.Plant;
import oop.Vector2d;

import java.util.LinkedList;
import java.util.Random;

public class ToxicCorpse implements IPlant{
    PlantsHolder plants = new PlantsHolder();
    MapConstants mapConstants;
    LinkedList<Vector2d> prefFields = new LinkedList<>();
    LinkedList<Vector2d> otherFields = new LinkedList<>();
    
    private int mapHeight = mapConstants.get(WorldParamType.MAP_HEIGHT);
    private int mapWidth = mapConstants.get(WorldParamType.MAP_WIDTH);
    
    
    public ToxicCorpse(MapConstants mapConstants){
        this.mapConstants=mapConstants;

    }

    public void addPlant() {
        Vector2d position=new Vector2d(0,0);



        while (plants.isPlantAtPosition(position))
        {
            plants.add(new Plant(position));
        }
    }

    private boolean isInPreferredPosition(Vector2d position){
        
        return false;
    }
    
    public void removePlant(Plant plant){
        plants.removePlant(plant);
        Vector2d plantPosition = plant.getPosition();
        if(isInPreferredPosition(plantPosition)){
            prefFields.add(plantPosition);
        }
        else{
            otherFields.add(plantPosition);
        }
    }
}
