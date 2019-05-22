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

