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
/*
Given two integer arrays, find the longest common subsequence. e.g. a = [1, 5, 2, 6, 3, 7], b = [5, 6, 7, 1, 2, 3], return [1, 2, 3] or [5, 6, 7]
*/
import java.util.Arrays;
import java.util.LinkedList;

public class FindLongestSubsequence {

    public static int[] findLongestSubsequence(int[] a, int[] b) {
        Stats statsFromA = parseArray(a, "a");
        Stats statsFromB = parseArray(b, "b");
        int[] result = statsFromA.size > statsFromB.size ? 
		Arrays.copyOfRange(a, statsFromA.startingIndex, statsFromA.endingIndex) :
                Arrays.copyOfRange(b, statsFromB.startingIndex, statsFromB.endingIndex);
        return result;
    }

    private static Stats parseArray(int[] a, String arrayName) {
        Stats biggest = new Stats(0,0,0,"N/A");
        int tempPrevious = Integer.MIN_VALUE;
        int indexStart = 0, indexEnd = 0, i;
        boolean flag = false;
        for (i = 0; i < a.length; i++) {
            if (i == 0) {
                tempPrevious = a[i];
                indexStart = i;
                indexEnd = i;
            }
            else {
                flag = true;
                if (a[i] != tempPrevious + 1) {
                    int size = indexEnd - indexStart + 1;
                    if (biggest.size < size) {
                        biggest = new Stats(indexStart, indexEnd, size, arrayName);
                    }
                    indexStart = i;
                    flag = false;
                }
                indexEnd = i;
                tempPrevious = a[i];
            }
        }
        if (flag) {
            int size = indexEnd - indexStart + 1;
            if (biggest.size < size) {
                biggest = new Stats(indexStart, indexEnd, size, arrayName);
            }
        }
        return biggest;
    }

    public static void main(String[] args) {
        int[] a = {1, 4, 7, 9, 15};
        int[] b = {1, 2, 3, 8, 9, 10, 11, 12};
        System.out.println("The result is " + Arrays.toString(findLongestSubsequence(a, b)));
    }
}

class Stats {
    int startingIndex;
    int endingIndex;
    int size;
    String array;
    Stats(int start, int end, int size, String array) {
        this.startingIndex = start;
        this.endingIndex = end;
        this.size = size;
        this.array = array;
    }
}