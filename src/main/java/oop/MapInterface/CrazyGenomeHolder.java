package oop.MapInterface;

import oop.ConfigParameters.MutationVariant;

import java.util.Random;

public class CrazyGenomeHolder extends AbstractGenomeHolder {

    /**
     * creates GenomeHolder object with given genome
     */
    public CrazyGenomeHolder(int[] genome){
        super(genome);
    }

    /**
     * creates GenomeHolder object with random genome of given length
     */
    public CrazyGenomeHolder(int genomeLength){
        super(genomeLength);
    }

//    public CrazyGenomeHolder(AbstractGenomeHolder genomeHolderA, AbstractGenomeHolder genomeHolderB, int shareA, boolean fromLeftA,
//                                      MutationVariant mutationVariant, int minMutationCount, int maxMutationCount){
//        super(genomeHolderA, genomeHolderB, shareA, fromLeftA, mutationVariant, minMutationCount, maxMutationCount);
//    }

    @Override
    public int getNextGen(){
        Random rand = new Random();
        int variate = rand.nextInt(10);
        if (variate < 8) {
            this.currIndex = (currIndex + 1) % genome.length;
        } else {
            currIndex = rand.nextInt() % genome.length;
        }
        return genome[currIndex];
    }
}
