package oop.MapInterface;

import oop.ConfigParameters.AnimalVariant;

import java.util.Random;

//
//import javafx.scene.layout.VBox;
//
//import java.util.ArrayList;
//import java.util.List;
//
public abstract class AbstractAnimal {
    private MapDirection direction;
    private AbstractAnimalGenome genome;
    private int energy;
    private int age = 0;
    private int childrenCount = 0;


    /**
     * creates animal with random genome
     * @param animalVariant     animal move variant
     * @param genomeLength      genome length
     * @param energy            intial animal energy
     */
    public AbstractAnimal(AnimalVariant animalVariant, int genomeLength, int energy){
        Random rand = new Random();
        this.genome = switch (animalVariant){
            case SOME_CRAZYNESS -> new CrazyAnimalGenome(genomeLength);
            case PREDESTINAION -> new PredestinationAnimalGenome(genomeLength);
        };
        this.energy = energy;
        this.direction = MapDirection.values()[rand.nextInt(8)];
    }

    /**
     * creates animal with provided genome
     * @param energy        initial energy
     * @param genome        genome
     */
    public AbstractAnimal(int energy, AbstractAnimalGenome genome){
        Random rand = new Random();
        this.genome = genome;
        this.energy = energy;
        this.direction = MapDirection.values()[rand.nextInt(8)];
    }


    // should be called before movement to get new direction
    /**
     * turns animal according to new gen (changes current gen in genome)
     * @return      direction which animal turned
     */
    public MapDirection turn(){
        int gen = genome.getNextGen();
        direction = direction.turn(gen);
        return direction;
    }

    // should be called on movement or just after
    /**
     * handles current movement and prepares animal for next one (changes gen index)
     */
    public void move(){
        energy -= 1;
        age += 1;
    }

    public void feed(int plantEnergy){
        this.energy += plantEnergy;
    }

    public boolean isDead(){
        return energy > 0;
    }


    /**
     * Compares priority of 2 animals (used when competing for same plant)
     * @param otherAnimal
     * @return cmpResult -1/0/1
     */
    public int compareTo(AbstractAnimal otherAnimal){
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

    public MapDirection getDirection() {
        return direction;
    }
}
