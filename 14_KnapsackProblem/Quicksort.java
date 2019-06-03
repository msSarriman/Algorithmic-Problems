package myknap;

import java.util.ArrayList;

/**
 * This parametrized quicksort, sorts a List<List<Integer>>>, where List<Integer> has two elements, <key> and <value>,
 * in ascending order, depending on <values>
 */
class Quicksort {

    static void quicksort(ArrayList<ArrayList<Integer>> list) {
        quicksort(list, 0, list.size() - 1);
    }

    private static void quicksort(ArrayList<ArrayList<Integer>> list, int start, int end) {
        // if index of start is greater than index of end, return (end of recursion)
        if (start >= end) return;

        // if start - end == -1, there are only two elements two compare and swap if necessary, and there is no recursion
        // needed to be done, after that.
        // the 'if' statement bellow executes a quick swap, to avoid wasting time to run the hole algorithm
        if (start - end == -1) {
            if (list.get(start).get(1) > list.get(end).get(1))
                mySwap(list, start, end);
            return;
        }

        // move the pivot to the end of the array
        int pivotIndex = (start + end) / 2;
        int pivot = list.get(pivotIndex).get(1);
        mySwap(list, pivotIndex, end);

        // while the left index is smaller than the pivot, increment it.
        int left = start;
        int right = end - 1;
        while (left < right) {
            if (list.get(left).get(1) > pivot) {
                //while the array[right] is greater than the pivot, decrement it.
                while (right > left && list.get(right).get(1) > pivot) {
                    right--;
                }
                // swap arr[left] and arr[right]
                mySwap(list, left, right);
            }
            if (left < right) {
                left++;
            }
        }

        // Bring pivot back to its original location
        // or increment the pivot's location by one, if the array[left] is smaller than it.
        if (list.get(end).get(1) < list.get(left).get(1)) {
            mySwap(list, left, end);
        } else {
            left++;
            mySwap(list, left, end);
        }

        // Call the recursive algorithm, into the two partitions created (left from pivot, right from pivot).
        quicksort(list, start, left - 1);
        quicksort(list, left + 1, end);
    }

    /**
     * This function swaps places at @x and @y indexes of the @list,
     * without using a temp value.
     * @param list
     * @param x
     * @param y
     */
    private static void mySwap(ArrayList<ArrayList<Integer>> list, int x, int y) {
        if (x == y) return;
        // swap x
        list.get(x).set(0, list.get(x).get(0) + list.get(y).get(0));
        list.get(y).set(0, list.get(x).get(0) - list.get(y).get(0));
        list.get(x).set(0, list.get(x).get(0) - list.get(y).get(0));
        // swap y
        list.get(x).set(1, list.get(x).get(1) + list.get(y).get(1));
        list.get(y).set(1, list.get(x).get(1) - list.get(y).get(1));
        list.get(x).set(1, list.get(x).get(1) - list.get(y).get(1));
    }
}
