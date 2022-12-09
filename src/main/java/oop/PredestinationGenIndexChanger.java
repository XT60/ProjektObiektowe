package oop;

public class PredestinationGenIndexChanger implements GenIndexChanger {
    int genomeLength;
    public PredestinationGenIndexChanger(int genomeLength){
        this.genomeLength = genomeLength;
    }

    @Override
    public int nextGenIndex(int currGenIndex) {
        currGenIndex = (currGenIndex + 1) % genomeLength;
        return currGenIndex;
    }
}
