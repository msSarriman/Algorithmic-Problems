package Test;

import java.util.LinkedList;

public class Solution {

    public static String findThreeSmallest(int[] array) {
        LinkedList<Integer> myList = new LinkedList<>();;
        for (int i = 0; i < array.length; i++) {
            findThreeSmallest(myList, array[i]);
        }
        return myList.toString();
    }

    private static void findThreeSmallest(LinkedList<Integer> myList, 
int number) {
        int i;
        boolean flag = false;
        for (i = 0; i <myList.size(); i++) {
            if (myList.get(i) >= number) {
                myList.add(i, number);
                flag = true;
                break;
            }
        }
        if (i < 3 && !flag) {
            myList.add(i, number);
        } else if (myList.size() == 4) {
            myList.remove(3);
        }
    }

    public static void main(String args[]) {
        int[] array = {16, -2, 2, 89, 24, -12, 108};
        System.out.printf("The results are: <%s>", 
findThreeSmallest(array));
    }
}

