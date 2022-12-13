package oop.MapInterface;

import java.util.Arrays;
import java.util.Random;

abstract class AbstractAnimalGenome {
        protected final int[] genome;
        protected int currIndex;

    /**
     * creates Genome object with given genome
     */
    public AbstractAnimalGenome(int[] genome){
        Random rand = new Random();
        this.currIndex = rand.nextInt(genome.length);
        this.genome = genome;
    }

    /**
     * creates Genome object with random genome of given length
     */
    public AbstractAnimalGenome(int genomeLength){
            Random rand = new Random();
            this.currIndex = rand.nextInt(genomeLength);
            genome = new int[genomeLength];
            for(int i = 0; i < genomeLength; i++){
                genome[i] = rand.nextInt(8);
            }
        }

    /**
     * retrives part of genome
     * @param count      count of genes to retrieve
     * @param fromLeft   side to start count from
     * @return           genomeSection
     */
    public int[] getSection(int count, Boolean fromLeft){
        if (fromLeft){
            return Arrays.copyOfRange(this.genome, 0, count);
        }
        else{
            int len = this.genome.length;
            return Arrays.copyOfRange(this.genome,len - count , len);
        }
    }

    /**
     *
     * @return int responding to next gen
     */
    abstract int getNextGen();



}
