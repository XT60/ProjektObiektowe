package oop.MapInterface;

import oop.ConfigParameters.MutationVariant;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

abstract class AbstractGenomeHolder {
        protected final int[] genome;
        protected int currIndex;

    /**
     * creates GenomeHolder object with given genome
     */
    public AbstractGenomeHolder(int[] genome){
        currIndex = getInitCurrIndexValue(genome.length);
        this.genome = genome;
    }

    /**
     * creates GenomeHolder object with random genome of given length
     */
    public AbstractGenomeHolder(int genomeLength){
        Random rand = new Random();
        this.currIndex = rand.nextInt(genomeLength);
        genome = new int[genomeLength];
        for(int i = 0; i < genomeLength; i++){
            genome[i] = rand.nextInt(8);
        }
    }

//    public AbstractGenomeHolder(AbstractGenomeHolder genomeHolderA, AbstractGenomeHolder genomeHolderB,
//                                int shareA, boolean fromLeftA,
//                                MutationVariant mutationVariant, int minMutationCount, int maxMutationCount){
//        genome = createNewGenome(genomeHolderA, genomeHolderB, shareA, fromLeftA);
//        mutateGenome(mutationVariant, minMutationCount, maxMutationCount);
//        currIndex = getInitCurrIndexValue(genome.length);
//    }

    private int getInitCurrIndexValue(int genomeLength){
        Random rand = new Random();
        return rand.nextInt(genomeLength);
    }

    /**
     * creates new genome out of given GenomeHolders
     * @param genomeHolderA     genome of other animal A
     * @param genomeHolderB     genome of other animal B
     * @param shareA            count of genes that other animal A will give to new genome
     * @param fromLeftA         determines if other genome A will give left or right part to new genome
     */
    private int[] createNewGenome(AbstractGenomeHolder genomeHolderA, AbstractGenomeHolder genomeHolderB,
                                    int shareA, boolean fromLeftA){
        int[] genomeA = genomeHolderA.getGenome();
        int[] genomeB = genomeHolderB.getGenome();
        int genomeLen = genomeA.length;
        int[] newGenome = new int[genomeLen];

        int[][] inOrder;
        int firstPart;
        if(fromLeftA){
            inOrder = new int[][]{genomeA, genomeB};
            firstPart = shareA;
        }
        else{
            inOrder = new int[][]{genomeB, genomeA};
            firstPart = genomeLen - shareA;
        }

        int j = 0;
        for(; j < firstPart; j++){
            newGenome[j] = inOrder[0][j];
        }
        for(; j < genomeLen; j++){
            newGenome[j] = inOrder[1][j];
        }
        return newGenome;
    }

    /**
     * mutates genome
     * @param mutationVariant   variant of mutation strategy
     * @param minMutationCount  minimal mutation count
     * @param maxMutationCount  maximal mutation count
     */
    private void mutateGenome(MutationVariant mutationVariant, int minMutationCount, int maxMutationCount){
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
        switch(mutationVariant){
            case FULL_RANDOMNESS:
                for(Integer index : genesToModify){
                    genome[index] = rand.nextInt(0, 7);
                }
                break;
            case SLIGHT_CORRECTION:
                for(Integer index : genesToModify){
                    if(rand.nextBoolean()){
                        genome[index] = (genome[index] + 1) % 8;
                    }
                    else{
                        genome[index] = (genome[index] - 1) % 8;
                    }
                }
                break;
        }
    }

//    /**
//     * retrives part of genome
//     * @param count      count of genes to retrieve
//     * @param fromLeft   side to start count from
//     * @return           genomeSection
//     */
//    public int[] getGenomeSection(int count, Boolean fromLeft){
//        if (fromLeft){
//            return Arrays.copyOfRange(this.genome, 0, count);
//        }
//        else{
//            int len = this.genome.length;
//            return Arrays.copyOfRange(this.genome,len - count , len);
//        }
//    }

    /**
     *
     * @return int responding to next gen
     */
    abstract int getNextGen();


    public int[] getGenome(){
        return Arrays.copyOfRange(genome, 0, genome.length);
    }
}
