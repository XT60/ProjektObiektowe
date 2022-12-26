package oop.MapInterface;

import oop.ConfigParameters.WorldParamType;
import oop.MapInterface.MapObjects.Plant;
import oop.Vector2d;

import java.util.LinkedList;
import java.util.Random;

import static java.lang.Math.max;


public class PlantsEquator implements IPlant{

    PlantsHolder plants = new PlantsHolder();
    MapConstants mapConstants;
    LinkedList<Vector2d> prefFields = new LinkedList<>();
    LinkedList<Vector2d> otherFields = new LinkedList<>();

    private final int mapHeight;
    private final int mapWidth;

    int yUp, yDown;


    public PlantsEquator(MapConstants mapConstants){
        this.mapConstants=mapConstants;
        this.mapHeight = mapConstants.get(WorldParamType.MAP_HEIGHT);
        this.mapWidth = mapConstants.get(WorldParamType.MAP_WIDTH);
        setPreferredParameters();
        for(int i=0; i<mapWidth; i++){
            for(int j=0; j<mapHeight; j++){
                Vector2d newPosition = new Vector2d(i,j);
                if(isInPreferredPosition(newPosition)){
                    prefFields.add(newPosition);
                }
                else{
                    otherFields.add(newPosition);
                }
            }
        }
    }

    public void addPlant() {
        Random rand = new Random();
        int randomInt = rand.nextInt(10);
        if (randomInt<=7 && prefFields.size()>0){
            int positionIndex=rand.nextInt(prefFields.size());
            plants.add(new Plant(prefFields.get(positionIndex)));
            prefFields.remove(positionIndex);
        }
        else if(otherFields.size()>0){
            int positionIndex=rand.nextInt(otherFields.size());
            plants.add(new Plant(otherFields.get(positionIndex)));
            otherFields.remove(positionIndex);
        }
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

    public boolean isPlantAtPosition(Vector2d position){
        return plants.isPlantAtPosition(position);
    }

    private void setPreferredParameters(){
        int preferredHeight = max(1,(int) (this.mapHeight*0.2));
        this.yDown = (int) ((mapHeight-preferredHeight)/2);
        this.yUp = yDown + preferredHeight - 1;
    }

    private boolean isInPreferredPosition(Vector2d position){
        return (position.y>=yDown && position.y<=yUp);
    }
}