package oop.MapInterface.MapObjects.GenomeHolders;

import oop.ConfigParameters.AnimalVariant;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public abstract class AbstractGenomeHolder {
        protected final int[] genome;
        GenomeIndexChanger indexChanger;

    /**
     * @param genome        genome array
     * @param indexChanger  index changer object
     */
    protected AbstractGenomeHolder(int[] genome, GenomeIndexChanger indexChanger){
        this.indexChanger = indexChanger;
        this.genome = genome;
    }

    /**
     * creates new GenomeHolder object of same class type (with same AnimalVariant and MutationVariant behaviours)
     * @param leftPart          left part of new genome
     * @param rightPart         right part of new genome
     * @param minMutationCount  minimal amount of gen mutations (inclusive)
     * @param maxMutationCount  maximal amount of gen mutations (inclusive)
     * @return                  genomeHolder of same class type (with same AnimalVariant and MutationVariant behaviours)
     */
    public AbstractGenomeHolder createGenomeHolder(int[] leftPart, int[] rightPart,
                                                   int minMutationCount, int maxMutationCount) {
        int newGenomeLen = leftPart.length + rightPart.length;

        int[] genome = new int [newGenomeLen];
        int i = 0;
        for(;i < leftPart.length; i++){
            genome[i] = leftPart[i];
        }
        for(; i < newGenomeLen; i++){
            int rIndex = i - rightPart.length;
            genome[i] = rightPart[rIndex];
        }
        GenomeIndexChanger indexChanger = this.indexChanger.createNewIndexChanger();

        return createGenomeHolder(genome, indexChanger, minMutationCount, maxMutationCount);
    }


    /**
     * creates object of child AbstractGenomeHolder class
     * @param genome            initial genome value
     * @param indexChanger      index changer object
     * @param minMutationCount  minimal amount of genes from genome to mutate
     * @param maxMutationCount  minimal amount of genes from genome to mutate
     * @return                  genomeHolder of same class type (with same AnimalVariant and MutationVariant behaviours)
     */
    abstract protected AbstractGenomeHolder createGenomeHolder(int [] genome, GenomeIndexChanger indexChanger,
                                                               int minMutationCount, int maxMutationCount);
    /**
     * creates GenomeHolder object with random genome of given length
     */
    public AbstractGenomeHolder(AnimalVariant animalVariant, int genomeLength){
        Random rand = new Random();
        genome = new int[genomeLength];
        for(int i = 0; i < genomeLength; i++){
            genome[i] = rand.nextInt(8);
        }
        initIndexChanger(animalVariant, genome.length);
    }

    /**
     * initializes indexChanger according to animalVariant value
     * @param animalVariant     variant of animal behaviour
     * @param genomeLength      length of genome
     */
    private void initIndexChanger(AnimalVariant animalVariant, int genomeLength){
        indexChanger = switch (animalVariant){
            case PREDESTINAION -> new PredistinationGenomeIndexChanger(genomeLength);
            case SOME_CRAZYNESS -> new CrazyGenomeIndexChanger(genomeLength);
        };
    }


    /**
     * @return int responding to next gen value
     */
    public int getNextGen(){
        return genome[this.indexChanger.getIndex()];
    }


    /**
     * @return copy of genome array
     */
    public int[] getGenome(){
        return Arrays.copyOfRange(genome, 0, genome.length);
    }


    /**
     * retrieves part of genome
     * @param count      count of genes to retrieve
     * @param fromLeft   side to start count from
     * @return           genomeSection
     */
    public int[] getGenomeSection(int count, Boolean fromLeft){
        if (fromLeft){
            return Arrays.copyOfRange(this.genome, 0, count);
        }
        else{
            int len = this.genome.length;
            return Arrays.copyOfRange(this.genome,len - count , len);
        }
    }


    /**
    * mutates x amount of genes from genome (where x is between minMutationCount and maxMutationCount)
    * @param minMutationCount   minimal mutation count (inclusive)
    * @param maxMutationCount   maximal mutation count (inclusive)
    */
    protected void mutateGenome(int minMutationCount, int maxMutationCount){
        Random rand = new Random();
        int mutationCount = rand.nextInt(minMutationCount, maxMutationCount + 1);
        Set<Integer> genesToModify = new HashSet<>();
        while (mutationCount > 0){
            int genIndex = rand.nextInt(0, genome.length);
            if (!genesToModify.contains(genIndex)){
                mutationCount -= 1;
                genesToModify.add(genIndex);
            }
        }
        for(Integer index : genesToModify){
            genome[index] = mutateGen(genome[index]);
        }
    }


    /**
     * makes mutation of given gen
     * @param gen   current gen value
     * @return      gen value after mutation
     */
    protected int mutateGen(int gen){
        // will it work if this method is protected and overwritten in child class ??
        // or should it be public
        return -1;
    }

}

