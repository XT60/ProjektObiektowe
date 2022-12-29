package oop.MapInterface.MapObjects.GenomeHolders;

import java.util.Random;

public class CrazyGenomeIndexChanger implements GenomeIndexChanger{
    int currIndex;
    int genomeLength;

    public CrazyGenomeIndexChanger(int genomeLength){
        Random rand = new Random();
        this.genomeLength = genomeLength;
        currIndex = rand.nextInt(genomeLength);
    }

    public int next(){
        Random rand = new Random();
        int variate = rand.nextInt(10);
        if (variate < 8) {
            currIndex = (currIndex + 1) % genomeLength;
        } else {
            currIndex = rand.nextInt(genomeLength);
        }
        return currIndex;
    }


    @Override
    public int getIndex() {
        return currIndex;
    }


    @Override
    public GenomeIndexChanger createNewIndexChanger() {
        return new CrazyGenomeIndexChanger(genomeLength);
    }
}
