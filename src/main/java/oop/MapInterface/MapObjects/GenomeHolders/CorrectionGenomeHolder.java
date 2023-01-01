package oop.MapInterface.MapObjects.GenomeHolders;

import oop.ConfigParameters.AnimalVariant;

import java.util.Random;

public class CorrectionGenomeHolder extends AbstractGenomeHolder{

    /** used in animal procreation process */
    private CorrectionGenomeHolder(int[] genome, GenomeIndexChanger indexChanger,
                               int minMutationCount, int maxMutationCount){
        super(genome, indexChanger);
        mutateGenome(minMutationCount, maxMutationCount);
    }

    /** initializes genome with random values */
    public CorrectionGenomeHolder(AnimalVariant animalVariant, int genomeLength) {
        super(animalVariant, genomeLength);
    }

    @Override
    protected AbstractGenomeHolder createGenomeHolder(int[] genome, GenomeIndexChanger indexChanger,
                                                      int minMutationCount, int maxMutationCount) {
        return new CorrectionGenomeHolder(genome, indexChanger, minMutationCount, maxMutationCount);
    }
    @Override
    protected int mutateGen(int gen){
        Random rand = new Random();
        if (rand.nextBoolean()){
            return (gen + 1) % 8;
        }
        else{
            return Math.abs((gen - 1) % 8);
        }
    }
}
