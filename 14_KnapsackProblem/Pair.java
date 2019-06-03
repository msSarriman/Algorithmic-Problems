package myknap;

import java.util.ArrayList;

/**
 * This class's instances hold the result's format in a structured way.
 */
public class Pair {
    private ArrayList pairList1;
    private ArrayList pairList2;
    Pair(ArrayList<Integer> list1, ArrayList<Integer> list2) {
        this.pairList1 = new ArrayList<>();
        this.pairList2 = new ArrayList<>();
        this.pairList1 = (ArrayList) list1.clone();
        this.pairList2 = (ArrayList) list2.clone();
    }

    public String toString() {
        return String.format("Pair1:<%s>, Pair2:<%s>\n", pairList1.toString(), pairList2.toString());
    }
}