package oop;

import java.util.Random;

public class CrazyGenIndexChanger implements GenIndexChanger {
    int genomeLength;
    public CrazyGenIndexChanger(int genomeLength){
        this.genomeLength = genomeLength;
    }

    @Override
    public int nextGenIndex(int currGenIndex) {
        Random rand = new Random();
        int variate = rand.nextInt(10);
        if (variate < 8) {
            currGenIndex = (currGenIndex + 1) % genomeLength;
        } else {
            currGenIndex = rand.nextInt() % genomeLength;
        }
        return currGenIndex;
    }
}
