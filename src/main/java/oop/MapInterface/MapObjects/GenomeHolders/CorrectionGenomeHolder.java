package oop.MapInterface.MapObjects.GenomeHolders;

import oop.ConfigParameters.AnimalVariant;

import java.util.Random;

public class CorrectionGenomeHolder extends AbstractGenomeHolder{


    private CorrectionGenomeHolder(int[] genome, GenomeIndexChanger indexChanger,
                               int minMutationCount, int maxMutationCount){
        super(genome, indexChanger);
        mutateGenome(minMutationCount, maxMutationCount);
    }



    public CorrectionGenomeHolder(AnimalVariant animalVariant, int genomeLength,
                                  int minMutationCount, int maxMutationCount) {
        super(animalVariant, genomeLength);
        mutateGenome(minMutationCount, maxMutationCount);
    }

    @Override
    protected AbstractGenomeHolder getNewGenomeHolder(int[] genome, GenomeIndexChanger indexChanger,
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
            return (gen - 1) % 8;
        }
    }
}
