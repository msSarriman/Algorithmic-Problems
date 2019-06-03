package myknap;

import java.util.ArrayList;
import java.util.Arrays;

public class TestCase {
    public static void main(String[] args) {
        ArrayList<Integer> l1 = new ArrayList<>(Arrays.asList(1,4));
        ArrayList<Integer> l2 = new ArrayList<>(Arrays.asList(2,6));
        ArrayList<Integer> l3 = new ArrayList<>(Arrays.asList(3,15));
        ArrayList<Integer> l4 = new ArrayList<>(Arrays.asList(4,10));
        ArrayList<Integer> l5 = new ArrayList<>(Arrays.asList(5,12));
        ArrayList<Integer> l6 = new ArrayList<>(Arrays.asList(6,8));
        ArrayList<Integer> l7 = new ArrayList<>(Arrays.asList(7,2));
        ArrayList<Integer> l8 = new ArrayList<>(Arrays.asList(8,4));

        ArrayList<Integer> a1 = new ArrayList<>(Arrays.asList(1,9));
        ArrayList<Integer> a2 = new ArrayList<>(Arrays.asList(2,14));
        ArrayList<Integer> a3 = new ArrayList<>(Arrays.asList(3,5));
        ArrayList<Integer> a4 = new ArrayList<>(Arrays.asList(4,18));
        ArrayList<Integer> a5 = new ArrayList<>(Arrays.asList(5,14));

        ArrayList<ArrayList<Integer>> list1 = new ArrayList<>();
        ArrayList<ArrayList<Integer>> list2 = new ArrayList<>();

        list1.add(l1);
        list1.add(l2);
        list1.add(l3);
        list1.add(l4);
        list1.add(l5);
        list1.add(l6);
        list1.add(l7);
        list1.add(l8);

        list2.add(a1);
        list2.add(a2);
        list2.add(a3);
        list2.add(a4);
        list2.add(a5);

        for (Pair p : Knapsack.startProcess(19, list1, list2)) {
            System.out.println(p);
        }
    }
}
