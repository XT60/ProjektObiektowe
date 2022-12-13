package oop.MapInterface;

public class PredestinationAnimalGenome extends AbstractAnimalGenome {

    /**
     * creates Genome object with given genome
     */
    public PredestinationAnimalGenome(int[] genome){
        super(genome);
    }

    /**
     * creates Genome object with random genome of given length
     */
    public PredestinationAnimalGenome(int genomeLength){
        super(genomeLength);
    }
    @Override
    public int getNextGen() {
        currIndex = (currIndex + 1) % currIndex;
        return genome[currIndex];
    }
}
