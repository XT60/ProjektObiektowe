package oop.MapInterface.MapObjects.GenomeHolders;

import oop.ConfigParameters.AnimalVariant;

import java.util.Random;

public class RandomGenomeHolder extends AbstractGenomeHolder{

    private RandomGenomeHolder(int[] genome, GenomeIndexChanger indexChanger,
                              int minMutationCount, int maxMutationCount){
        super(genome, indexChanger);
        mutateGenome(minMutationCount, maxMutationCount);
    }


    public RandomGenomeHolder(AnimalVariant animalVariant, int genomeLength,
                              int minMutationCount, int maxMutationCount) {
        super(animalVariant, genomeLength);
        mutateGenome(minMutationCount, maxMutationCount);
    }


    @Override
    protected AbstractGenomeHolder getNewGenomeHolder(int[] genome, GenomeIndexChanger indexChanger,
                                                      int minMutationCount, int maxMutationCount) {
        return new RandomGenomeHolder(genome, indexChanger, minMutationCount, maxMutationCount);
    }

    @Override
    protected int mutateGen(int gen){
        Random rand = new Random();
            return rand.nextInt(0, 7);
    }

}
