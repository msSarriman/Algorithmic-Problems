/*
MIT License

Copyright (c) 2019 Emmanouil Sarris

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
/**
 * ------------ Knapsack Problem --------------
 * -------------------------------------- a  (     b       )-------------------
 * Given two Lists of Lists of numbers (List<List<Integer>>>), and an integer X,
 * where b List<Integer> has two elements, key @index 0 and value @index 1,
 * find the best b pairs (can be more than one and elements of pairs must belong to different lists),
 * which are their value summary is closest to X (but not bigger than X).
 */
package myknap;

import java.util.ArrayList;
import java.util.LinkedList;

public class Knapsack {

    /**
     * Runtime of algorithm is O(2NlogN + N) => O(NlogN), where NlogN is the time required to sort the @(list1,list2).
     * This function creates a unified sorted list, that comes out of @list1 and @list2, among with a boolean array
     * that categorizes the origin of the elements of the unified list.
     * Then by creating a pointer at the start of the list and a pointer at the end of the list, we are trying to find
     * those sums, that provide the bestPossibleGap so far, or a Gap equal to the gap so far, and updates the result
     * variable accordingly. The result value is update only if the sum comes out of two elements that belong to
     * different arrays.
     *
     * @param max
     * @param list1
     * @param list2
     * @return
     */
    static LinkedList<Pair> startProcess(int max, ArrayList<ArrayList<Integer>> list1, ArrayList<ArrayList<Integer>> list2) {
        // Use quicksort algorithm to sort the @list1 and @list2
        Quicksort.quicksort(list1);
        Quicksort.quicksort(list2);

        //Create a boolean array, and given it as input to createUnifiedList method, to update it with the values
        //origin, at @unifiedList
        boolean[] belongsAt = new boolean[list1.size() + list2.size()];
        ArrayList<ArrayList<Integer>> unifiedList = createUnifiedList(list1, list2, belongsAt);

        //The variable @result, will store all the results that will occur for the solution to the problem.
        LinkedList<Pair> result = new LinkedList<>();

        //Beggining of the unifiedLists's traverse, requires a left (i) and a right (j) pointer.
        int i = 0;                       // starting index
        int j = unifiedList.size() - 1;  // ending index
        ArrayList<Integer> pointerLeft = unifiedList.get(i);
        ArrayList<Integer> pointerRight = unifiedList.get(j);
        int bestGapSoFar = Integer.MAX_VALUE;
        while(i != j) {
            int currentSum = pointerLeft.get(1) + pointerRight.get(1);
            int currentGap = max - currentSum;
            //If currenstSum is greater than max, then we need to lower the right pointer to find a smallest element.
            if (currentSum > max) {
                pointerRight = unifiedList.get(--j);
            } else {
                if (bestGapSoFar > currentGap && (belongsAt[i] != belongsAt[j])) {
                    bestGapSoFar = currentGap;
                    result.clear();
                    result.add(new Pair(pointerLeft, pointerRight));
                    pointerLeft = unifiedList.get(++i);
                } else if (bestGapSoFar == currentGap && (belongsAt[i] != belongsAt[j])) {
                    result.add(new Pair(pointerLeft, pointerRight));
                    pointerLeft = unifiedList.get(++i);
                } else {
                    pointerRight = unifiedList.get(--j);
                }
            }
        }
        return result;
    }

    /**
     * This method creates a unified list, out of the ordered lists @list1 and @list2, and updates the values
     * at @belongs, correspondingly.
     * @param list1
     * @param list2
     * @param belongs
     * @return
     */
    private static ArrayList<ArrayList<Integer>> createUnifiedList(ArrayList<ArrayList<Integer>> list1,
                                                                   ArrayList<ArrayList<Integer>> list2,
                                                                   boolean[] belongs) {
        if (list1.size() == 0 || list2.size() == 0) {
            System.out.println("Error in sizes of arrays");
            return null;
        }
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        int i = 0;
        int j = 0;
        int k = 0; // belongs counter // list1 == true, list2 == false
        while (true) {
            if (i < list1.size() && j < list2.size()) {
                if (list1.get(i).get(1) < list2.get(j).get(1)) {
                    belongs[k++] = true;
                    result.add(list1.get(i++));
                } else {
                    belongs[k++] = false;
                    result.add(list2.get(j++));
                }
            } else if (i == list1.size() && j < list2.size()) {
                belongs[k++] = false;
                result.add(list2.get(j++));
            } else if (i < list1.size() && j == list2.size()) {
                belongs[k++] = true;
                result.add(list1.get(i++));
            } else {
                break;
            }
        }
        return result;
    }
}