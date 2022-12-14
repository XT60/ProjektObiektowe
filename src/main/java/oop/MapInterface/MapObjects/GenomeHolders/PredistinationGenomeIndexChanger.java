package oop.MapInterface.MapObjects.GenomeHolders;

import java.util.Random;

public class PredistinationGenomeIndexChanger implements GenomeIndexChanger{
    int currIndex;
    int genomeLength;

    public PredistinationGenomeIndexChanger(int genomeLength){
        Random rand = new Random();
        this.genomeLength = genomeLength;
        currIndex = rand.nextInt() % genomeLength;
    }


    @Override
    public int next(){
        currIndex = (currIndex + 1) % genomeLength;
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
