package oop.MapInterface.MapObjects.GenomeHolders;

public interface GenomeIndexChanger {

    public int next();

    public int getIndex();

    public GenomeIndexChanger createNewIndexChanger();
}
