package oop.MapInterface;

import oop.ConfigParameters.AnimalVariant;

import java.util.Arrays;
import java.util.Random;

//
//import javafx.scene.layout.VBox;
//
//import java.util.ArrayList;
//import java.util.List;
//
public class Animal{
    private MapDirection direction;
    private int[] genome;
    private int currGenIndex;
    private GenIndexChanger genIndexChanger;
    private int energy;
    private int age = 0;
    private int childrenCount = 0;

    public Animal(AnimalVariant animalVariant, int genomeLength, int energy){
        this.initAllButGenome(animalVariant, genomeLength, energy);
        this.initRandomGenome(genomeLength);
    }

    public Animal(AnimalVariant animalVariant, int genomeLength, int energy,  int[] genome){
        initAllButGenome(animalVariant, genomeLength, energy);
        this.genome = genome;
    }

    private void initAllButGenome(AnimalVariant animalVariant, int genomeLength, int energy){
        Random rand = new Random();
        this.genIndexChanger = switch (animalVariant){
            case SOME_CRAZYNESS -> new CrazyGenIndexChanger(genomeLength);
            case PREDESTINAION -> new PredestinationGenIndexChanger(genomeLength);
        };
        this.energy = energy;
        this.direction = MapDirection.values()[rand.nextInt(8)];
        this.currGenIndex = rand.nextInt(genomeLength);
    }

    public void update(){
        this.currGenIndex = genIndexChanger.nextGenIndex(currGenIndex);
    }

    /**
     * initializes genome as random ints from in range [1,7] (inclusive)
     * @param genomeLength
     */
    private void initRandomGenome(int genomeLength){
        this.genome = new int[genomeLength];
        Random rand = new Random();
        for(int i = 0; i < genomeLength; i++){
            genome[i] = rand.nextInt(8);
        }
    }

    /**
     * Compares priority of 2 animals (used when competeng for same plant)
     * @param otherAnimal
     * @return cmpResult -1/0/1
     */
    public int compareTo(Animal otherAnimal){
        int[] otherParams = {otherAnimal.getEnergy(), otherAnimal.getAge(), otherAnimal.getChildrenCount()};
        int[] myParams = {this.energy, this.age, this.childrenCount};
        for(int i = 0; i < 3; i++){
            int cmpRes = cmp(myParams[i], otherParams[i]);
            if (cmpRes != 0){
                return cmpRes;
            }
        }
        return 1;
    }

    /**
     * retrives part of genome (used in animal reproduction process)
     * @param count     - count of genes to retrive
     * @param fromLeft  - side to start count from
     * @return genomeSection
     */
    private int[] getGenomeSection(int count, Boolean fromLeft){
        if (fromLeft){
            return Arrays.copyOfRange(this.genome, 0, count);
        }
        else{
            int len = this.genome.length;
            return Arrays.copyOfRange(this.genome,len - count , len);
        }
    }

    private int cmp(int a, int b){
        if (a < b){
            return 1;
        }
        else if (a > b) {
            return -1;
        }
        return 0;
    }

    public int getEnergy() {
        return energy;
    }

    public int getAge() {
        return age;
    }

    public int getChildrenCount() {
        return childrenCount;
    }
}
