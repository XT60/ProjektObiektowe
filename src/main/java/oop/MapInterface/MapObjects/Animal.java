package oop.MapInterface.MapObjects;

import oop.ConfigParameters.AnimalVariant;
import oop.ConfigParameters.MutationVariant;
import oop.ConfigParameters.WorldParamType;
import oop.MapInterface.MapDirection;
import oop.MapInterface.MapObjects.GenomeHolders.AbstractGenomeHolder;
import oop.MapInterface.MapObjects.GenomeHolders.CorrectionGenomeHolder;
import oop.MapInterface.MapObjects.GenomeHolders.RandomGenomeHolder;
import oop.Vector2d;

import java.util.Random;


public class Animal {
    private MapDirection direction;
    protected AbstractGenomeHolder genomeHolder;
    private int energy;
    private int age = 0;
    protected int childrenCount = 0;
    private Vector2d position;

    AnimalConstants constants;


    /**
     * creates animal with random genome
     * @param constants         animal config constants
     * @param animalVariant     animal move variant
     * @param mutationVariant   mutation variant
     */
    public Animal(AnimalConstants constants,
                  AnimalVariant animalVariant, MutationVariant mutationVariant, Vector2d position){
        this(constants, position);
        this.energy = constants.get(WorldParamType.INIT_ANIMAL_ENERGY);

        int genomeLength = constants.get(WorldParamType.ANIMAL_GENOME_LENGTH);
        int minMutationCount = constants.get(WorldParamType.MIN_MUTATION_COUNT);
        int maxMutationCount = constants.get(WorldParamType.MIN_MUTATION_COUNT);
        this.genomeHolder = switch (mutationVariant){
            case SLIGHT_CORRECTION -> new CorrectionGenomeHolder(animalVariant, genomeLength);
            case FULL_RANDOMNESS -> new RandomGenomeHolder(animalVariant, genomeLength);
        };
    }

    /**
     * creates animal with provided genome and energy equal to 2 * REPRODUCTION_ENERGY_THRESHOLD
     * @param constants     animal config constants
     * @param genomeHolder        genome
     */
    private Animal(AnimalConstants constants, AbstractGenomeHolder genomeHolder, Vector2d position){
        this(constants, position);
        this.genomeHolder = genomeHolder;
        this.energy = 2 * constants.get(WorldParamType.REPRODUCTION_ENERGY_THRESHOLD);
    }

    private Animal (AnimalConstants constants, Vector2d position){
        Random rand = new Random();
        this.direction = MapDirection.values()[rand.nextInt(8)];
        this.constants = constants;
        this.position = new Vector2d(position);
    }


    // should be called before movement to get new direction
    /**
     * turns animal according to new gen (changes current gen in genome)
     * @return      direction which animal turned
     */
    public MapDirection turn(){
        int gen = genomeHolder.getNextGen();
        direction = direction.turn(gen);
        energy -= 1;
        age += 1;
        return direction;
    }
    /**
     * handles animal movement
     */
    public void move(Vector2d position){
        this.position = position;
    }


    /**
     * change animal direction 180 degrees
     */
    public void reverse(){
        this.direction = this.direction.turn(4);
    }


    /**
     * teleports animal to given position, subtracts same amount of energy as for procreation
     * (used with hell portal map variant)
     * @param newPosition   new animal position
     */
    public void teleport(Vector2d newPosition){
        this.energy -= constants.get(WorldParamType.REPRODUCTION_COST) - 1;
        this.position = new Vector2d(newPosition);
    }


    /**
     * increases animal energy of PLANT_ENERGY constant value
     */
    public void feed(){
        this.energy += constants.get(WorldParamType.PLANT_ENERGY);
    }


    public boolean isDead(){
        return energy > 0;
    }


    private boolean canProcreate(){
        return constants.get(WorldParamType.REPRODUCTION_ENERGY_THRESHOLD) <= this.energy;
    }

    /**
     * creates new Animal instance of the same type (with same AnimalVariant and MutationVariant behaviours)
     * if both parents are capable to procreate -> creates new Animal (child)
     * otherwise                                -> null is returned
     * @param partner   other potential parent of new child
     * @return          child Animal | null
     */
    public Animal procreate(Animal partner){
        if (!canProcreate() || !partner.canProcreate()){
            return null;
        }
        Random rand = new Random();
        Animal stronger, weaker;
        if (this.getEnergy() > partner.getEnergy()){
            stronger = this;
            weaker = partner;
        }
        else{
            stronger = partner;
            weaker = this;
        }
        boolean fromTheLeft = rand.nextBoolean();
        int genomeLength = constants.get(WorldParamType.ANIMAL_GENOME_LENGTH);
        int strongerShare = (int)Math.floor(
                (double)stronger.getEnergy() / (stronger.getEnergy() + weaker.getEnergy()) * genomeLength
        );
        int[] strongerPart = stronger.genomeHolder.getGenomeSection(strongerShare, fromTheLeft);
        int[] weakerPart = weaker.genomeHolder.getGenomeSection(genomeLength - strongerShare, !fromTheLeft);

        int minMutationCount = constants.get(WorldParamType.MIN_MUTATION_COUNT);
        int maxMutationCount = constants.get(WorldParamType.MAX_MUTATION_COUNT);

        int[] leftPart, rightPart;
        if (fromTheLeft){
            leftPart = strongerPart;
            rightPart = weakerPart;
        }
        else{
            leftPart = weakerPart;
            rightPart = strongerPart;
        }
        AbstractGenomeHolder newGenomeHolder = this.genomeHolder.createGenomeHolder(leftPart, rightPart,
                minMutationCount, maxMutationCount);

        setAsParent();
        return new Animal(constants, newGenomeHolder, this.getPosition());
    }


    /**
     * Compares priority of 2 animals (used when competing for same plant)
     * @param otherAnimal       other animal
     * @return                  compare result (-1|0|1)
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

    /**
     * updates object variables after procreation process
     */
    private void setAsParent(){
        childrenCount += 1;
        this.energy -= this.constants.get(WorldParamType.REPRODUCTION_COST);
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

    public Vector2d getPosition() {
        return new Vector2d(position);
    }

}
