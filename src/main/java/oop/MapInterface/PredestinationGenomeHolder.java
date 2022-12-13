package oop.MapInterface;

import oop.ConfigParameters.MutationVariant;

public class PredestinationGenomeHolder extends AbstractGenomeHolder {

    /**
     * creates GenomeHolder object with given genome
     */
    public PredestinationGenomeHolder(int[] genome){
        super(genome);
    }

    /**
     * creates GenomeHolder object with random genome of given length
     */
    public PredestinationGenomeHolder(int genomeLength){
        super(genomeLength);
    }
    @Override
    public int getNextGen() {
        currIndex = (currIndex + 1) % currIndex;
        return genome[currIndex];
    }

//    public PredestinationGenomeHolder(AbstractGenomeHolder genomeHolderA, AbstractGenomeHolder genomeHolderB, int shareA, boolean fromLeftA,
//                                      MutationVariant mutationVariant, int minMutationCount, int maxMutationCount){
//        super(genomeHolderA, genomeHolderB, shareA, fromLeftA, mutationVariant, minMutationCount, maxMutationCount);
//    }
}
