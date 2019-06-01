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
 * Rotate Matrix: Given an image represented by an NxN matrix, where each pixel in the image is 4
 * bytes, write a method to rotate the image by 90 degrees. (an you do this in place?
 * Hints: #51, #100
 * - Try thinking about it layer by layer. Can you rotate a specific layer?
 * - Rotating a specific layer would just mean swapping the values in four arrays. Ifyou were
 * asked to swap the values in _02two arrays, could you do this? Can you then extend it to four
 * arrays?
 */
/**
 * 11_SortWithoutApi
 * Write a program to sort a string without using a Java API. I/P: "a390testai", O/P:"039aaiest"
 */

import java.util.Arrays;

public class SortWithoutApi {

    /**
     * This function takes as input a string, creates an integer array out the ASCII codes
     * of its elements, sorts the integer array using myQuicksort(int[]), parses the sorted
     * integer array and casting its values back to char and appending them to a StringBuilder,
     * and finally return the String representation of the StringBuilder.
     * @param str
     * @return
     */
    protected static String myQuicksort(String str) {
        int[] array = new int[str.length()];
        int i = 0;
        for (char c : str.toCharArray()) {
            array[i++] = (int) c;
        }
        myQuicksort(array);
        StringBuilder sb = new StringBuilder();
        for (int j : array) {
            sb.append((char) j);
        }
        return sb.toString();
    }

    /**
     * This function is used to create a user friendly API to call the quicksort algorithm on
     * a hole integer @array.
     * @param array
     */
    protected static void myQuicksort(int[] array) {
        myQuicksort(array, 0, array.length - 1);
    }

    /**
     * The recursive function of quicksort, takes place within this function. It sorts the array,
     * according to pivot, by exchanging places at elements, and then places back the pivot, to the
     * right index, so that everything on the left, is smaller than everything on the right.
     * @param array
     * @param start
     * @param end
     */
    private static void myQuicksort(int[] array, int start, int end) {
        // if index of start is greater than index of end, return (end of recursion)
        if (start >= end) return;

        // if start - end == -1, there are only two elements two compare and swap if necessary, and there is no recursion
        // needed to be done, after that.
        // the 'if' statement bellow executes a quick swap, to avoid wasting time to run the hole algorithm
        if (start - end == -1) {
            if (array[start] > array[end])
                mySwap(array, start, end);
            return;
        }

        // move the pivot to the end of the array
        int pivotIndex = (start + end) / 2;
        int pivot = array[pivotIndex];
        mySwap(array, pivotIndex, end);

        // while the left index is smaller than the pivot, increment it.
        int left = start;
        int right = end - 1;
        while (left < right) {
            if (array[left] > pivot) {
                //while the array[right] is greater than the pivot, decrement it.
                while (right > left && array[right] > pivot) {
                    right--;
                }
                // swap arr[left] and arr[right]
                mySwap(array, left, right);
            }
            if (left < right) {
                left++;
            }
        }

        // Bring pivot back to its original location
        // or increment the pivot's location by one, if the array[left] is smaller than it.
        if (array[end] < array[left]) {
            mySwap(array, left, end);
        } else {
            left++;
            mySwap(array, left, end);
        }

        // Call the recursive algorithm, into the two partitions created (left from pivot, right from pivot).
        myQuicksort(array, start, left - 1);
        myQuicksort(array, left + 1, end);
    }

    /**
     * This function swaps places at @x and @y indexes of the @array,
     * without using a temp value.
     * @param array
     * @param x
     * @param y
     */
    protected static void mySwap(int[] array, int x, int y) {
        if (x == y) return;
        array[x] = array[x] + array[y];
        array[y] = array[x] - array[y];
        array[x] = array[x] - array[y];
    }

    public static void main(String[] args) {
        int[] array = {6,1,8,2,-1,9,3,4,5,13};
        myQuicksort(array);
        System.out.println(Arrays.toString(array));

        int[] array1 = {98,452,34,847,19,85,632,169,865,23,18,94,61,53,2,324};
        myQuicksort(array1);
        System.out.println(Arrays.toString(array1));

        String str = "235this1234is989test0";
        System.out.println(myQuicksort(str));

        String str1 = "aopidutgp9y58675fy89es4,7t837f6m0";
        System.out.println(myQuicksort(str1));
    }

}