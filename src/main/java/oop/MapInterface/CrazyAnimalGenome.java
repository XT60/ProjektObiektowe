package oop.MapInterface;

import java.util.Random;

public class CrazyAnimalGenome extends AbstractAnimalGenome {

    /**
     * creates Genome object with given genome
     */
    public CrazyAnimalGenome(int[] genome){
        super(genome);
    }

    /**
     * creates Genome object with random genome of given length
     */
    public CrazyAnimalGenome(int genomeLength){
        super(genomeLength);
    }
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
