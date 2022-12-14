package oop.MapInterface.MapObjects.GenomeHolders;

public interface GenomeIndexChanger {
    /**
     * changes gen that genome is currently
     * @return  new current genome index
     */
    int next();

    /**
     * @return current genome index
     */
    int getIndex();

    /**
     * creates new GenomeIndexChanger instance of the same type (with same AnimalVariant behaviours)
     * @return GenomeIndexChanger instance of the same type
     */
    GenomeIndexChanger createNewIndexChanger();
}
