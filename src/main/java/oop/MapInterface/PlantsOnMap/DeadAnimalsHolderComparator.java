package oop.MapInterface.PlantsOnMap;

public class DeadAnimalsHolderComparator implements java.util.Comparator<DeadAnimalsHolderElement> {

    // increasing order
    @Override
    public int compare(DeadAnimalsHolderElement o1, DeadAnimalsHolderElement o2) {
        if (o1.deadAnimalsCount > o2.deadAnimalsCount)
            return 1;
        else if (o1.deadAnimalsCount < o2.deadAnimalsCount)
            return -1;
        if (o1.equals(o2)) {
            return 0;
        }
        return 1;
    }
}