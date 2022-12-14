package oop.MapInterface.MapObjects.GenomeHolders;

import oop.ConfigParameters.AnimalVariant;

import java.util.Random;

public class RandomGenomeHolder extends AbstractGenomeHolder{

    /** used in animal procreation process */
    private RandomGenomeHolder(int[] genome, GenomeIndexChanger indexChanger,
                              int minMutationCount, int maxMutationCount){
        super(genome, indexChanger);
        mutateGenome(minMutationCount, maxMutationCount);
    }

    /** initializes genome with random values */
    public RandomGenomeHolder(AnimalVariant animalVariant, int genomeLength) {
        super(animalVariant, genomeLength);
    }


    @Override
    protected AbstractGenomeHolder createGenomeHolder(int[] genome, GenomeIndexChanger indexChanger,
                                                      int minMutationCount, int maxMutationCount) {
        return new RandomGenomeHolder(genome, indexChanger, minMutationCount, maxMutationCount);
    }


    @Override
    protected int mutateGen(int gen){
        Random rand = new Random();
            return rand.nextInt(0, 7);
    }

}
